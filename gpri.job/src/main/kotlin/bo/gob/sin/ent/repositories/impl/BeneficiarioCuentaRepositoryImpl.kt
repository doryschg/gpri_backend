package bo.gob.sin.ent.repositories.impl

import bo.gob.sin.ent.repositories.BeneficiarioCuentaRepository
import bo.gob.sin.ent.repositories.model.BeneficiarioCuenta
import bo.gob.sin.ent.repositories.model.BeneficiarioPago
import bo.gob.sin.ent.repositories.model.enums.EnumEstadoCuenta
import bo.gob.sin.ent.repositories.model.enums.EnumTipoUsuario
import com.google.gson.Gson

import org.apache.commons.dbutils.DbUtils
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler
import org.slf4j.LoggerFactory

import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class BeneficiarioCuentaRepositoryImpl: BeneficiarioCuentaRepository {
    private val LOG = LoggerFactory.getLogger(BeneficiarioCuentaRepositoryImpl::class.java)
    val resourceBundle = ResourceBundle.getBundle("job")
    val env: (String) -> String = { str ->
        val param = resourceBundle.getString(str)
        val match = Regex("\\$\\{(\\w+)\\}$").find(param)
        val valor = match?.destructured?.component1()
        valor?.let { System.getenv(valor)?:valor }?:param
    }
    val db: () -> Connection = {
        Class.forName("org.postgresql.Driver")
        val url = this.env("db.url")
        val props = Properties()
        props.setProperty("user", this.env("db.username"))
        props.setProperty("password", this.env("db.password"))
        props.setProperty("ssl", "false")
        DriverManager.getConnection(url, props)
    }
    val horasDiferencia = 2
    override fun findBeneficiarios(estado: String, limit: Long, offset: Long): List<BeneficiarioCuenta> {
        val sql = "select b.seq_beneficiario_nb as id,\n" +
                "       b.persona_id_nb as personaid,\n" +
                "       b.entidad_financiera_riv_id_vc as banco,\n" +
                "       b.cuenta_banco_vc as cuenta,\n" +
                "       b.fecha_registro_ts as fecharegistro,\n" +
                "       b.id_beneficiario_sigep_nb as beneficiariosigepid,\n" +
                "       a.usuario_id as usuarioid\n" +
                "from  sfe_facturacion.tsfe_riv_beneficiarios b, sau_usuarios.sau_adm_usuarios  a\n" +
                "where b.id_beneficiario_sigep_nb is not null \n" +
                "  and b.estado_cuenta_bancaria_id_vc = '$estado' \n" +
                "  and b.estado_id_vc = 'AC'\n" +
                "  and b.fecha_registro_ts < (now() - interval '$horasDiferencia hours')\n" +
                "  and b.persona_id_nb = a.persona_id\n" +
                "  and a.tipo_usuario_id= ${EnumTipoUsuario.BENEFICIARIO.value}\n" +
                "ORDER BY fecha_registro_ts ASC\n"
        val sqlfinal = sql + "LIMIT $limit OFFSET $offset"
        val connection = db()
        val runner = QueryRunner()

        val beanListHandler = BeanListHandler(BeneficiarioCuenta::class.java)
        val results = runner.query(connection, sqlfinal, beanListHandler)
        DbUtils.closeQuietly(connection)
        return results
    }

    override fun findBeneficiariosPagos(limit: Long, offset: Long): List<BeneficiarioPago> {
        val sql = "select ben.seq_beneficiario_nb as id,\n" +
                "res.gestion_nb as gestion,\n" +
                "res.periodo_nb as periodo,\n" +
                "ben.id_beneficiario_sigep_nb as beneficiariosigepid,\n" +
                "dc.estado_proceso_pago_id_vc as estado,-- actualizar el estado \n" +
                "dc.monto_total_a_pagar_nb as monto, 'SIN' as origen\n" +
                "from sfe_facturacion.tsfe_riv_resumen_conciliacion res,\n" +
                "sfe_facturacion.tsfe_riv_detalle_conciliacion dc, \n" +
                "sfe_facturacion.tsfe_riv_beneficiarios ben\n" +
                "where res.seq_resumen_conciliacion_nb = dc.seq_resumen_conciliacion_nb\n" +
                "and res.estado_id_vc ='AC' \n" +
                "and res.estado_conciliacion_id_vc ='GEN'\n" +
                "and ben.seq_beneficiario_nb = dc.seq_beneficiario_nb \n" +
                "and ben.estado_id_vc  = 'AC' \n" +
                "and dc.estado_id_vc = 'AC' \n" +
                "and ben.estado_beneficiario_id_vc ='H' \n" +
                "and ben.fecha_hasta_dt is null \n" +
                "and dc.estado_proceso_pago_id_vc = 'PEN'\n" +
                "and ben.estado_cuenta_bancaria_id_vc = 'VAL'\n" // TODO: Añadir un 'order by' fecha registro

        val sqlfinal = sql + "LIMIT $limit OFFSET $offset"
        val connection = db()
        val runner = QueryRunner()

        val beanListHandler = BeanListHandler(BeneficiarioPago::class.java)
        val results = runner.query(connection, sqlfinal, beanListHandler)
        DbUtils.closeQuietly(connection)
        return results
    }

    override fun findBeneficiariosPagosConsulta(limit: Long, offset: Long): List<BeneficiarioPago> {
        val sql = "select seq_detalle_conciliacion_nb as id,\n" +
                "id_pago_bono_nb as pagobonoid,\n" +
				"monto_total_a_pagar_nb as monto\n" +
                "from sfe_facturacion.tsfe_riv_detalle_conciliacion\n" +
                "where id_pago_bono_nb is not null\n" +
                "and id_pago_bono_nb <> 0\n" +
                "and estado_id_vc = 'AC'\n" +
                "and estado_proceso_pago_id_vc = 'PEN'\n" +
                "order by fecha_registro_ts ASC\n"
        val sqlfinal = sql + "LIMIT $limit OFFSET $offset"
        val connection = db()
        val runner = QueryRunner()

        val beanListHandler = BeanListHandler(BeneficiarioPago::class.java)
        val results = runner.query(connection, sqlfinal, beanListHandler)
        DbUtils.closeQuietly(connection)
        return results
    }

    override fun updateCuentasValidas(beneficiarios: List<BeneficiarioCuenta>, actuaizarRoles: (Connection?, List<Long>) -> Unit) {
        if (beneficiarios.isEmpty()) return

        val lst = mutableListOf<Array<Any>>()
        val lstUsuarios = mutableListOf<Long>()
        val sql = "UPDATE sfe_facturacion.tsfe_riv_beneficiarios SET estado_cuenta_bancaria_id_vc = ? WHERE seq_beneficiario_nb= ?"
        for(beneficiario in beneficiarios) {
            lst += arrayOf(EnumEstadoCuenta.VALIDA.estado, beneficiario.id)
            lstUsuarios += beneficiario.usuarioId
        }
        val connection = db()
        try {
            val runner = QueryRunner()
            runner.batch(connection, sql, lst.toTypedArray())
            // TODO: Utilizar procedimiento almacenado si el proceso es lento
            actuaizarRoles(connection, lstUsuarios)
        } catch (e: Exception) {
            LOG.error(e.cause.toString(), e);
        }
        DbUtils.closeQuietly(connection)
    }

    override fun updateCuentasError(beneficiarios: List<Pair<Long, String>>) {
        if (beneficiarios.isEmpty()) return
        val connection = db()
        val runner = QueryRunner()
        val lst = mutableListOf<Array<Any>>()
        for(beneficiario in beneficiarios) {
            lst += arrayOf(beneficiario.second, beneficiario.first)
        }
        val sql = "UPDATE sfe_facturacion.tsfe_riv_beneficiarios SET estado_cuenta_bancaria_id_vc = ? WHERE seq_beneficiario_nb= ?"
        try {
            runner.batch(connection, sql, lst.toTypedArray())
        } catch (e: Exception) {
            LOG.error(e.cause.toString(), e);
        }

        DbUtils.closeQuietly(connection)
    }

    override fun updateIdPagoBonos(beneficiariosPagados: List<BeneficiarioPago>) {
        if (beneficiariosPagados.isEmpty()) return

        val lst = mutableListOf<Array<Any>>()
        val sql = "UPDATE sfe_facturacion.tsfe_riv_detalle_conciliacion SET id_pago_bono_nb=? WHERE id_beneficiario_sigep_nb=? AND gestion_nb=? AND periodo_nb=?"
        for (beneficiario in beneficiariosPagados) {
            lst += arrayOf(beneficiario.pagoBonoId, beneficiario.beneficiarioSigepId, beneficiario.gestion, beneficiario.periodo)
        }
        val connection = db()
        try {
            val runner = QueryRunner()
            runner.batch(connection, sql, lst.toTypedArray())
        } catch (e: Exception) {
            LOG.error(e.cause.toString(), e);
        }
        DbUtils.closeQuietly(connection)
    }

    override fun updateEstadoPagoBono(beneficiarios: List<BeneficiarioPago>) {
        if (beneficiarios.isEmpty()) return
        val lst = mutableListOf<Array<Any>>()
        val lstMovimientos = mutableListOf<Array<Any>>()
		// TODO: actualizar monto total pagado
        val sql = "UPDATE sfe_facturacion.tsfe_riv_detalle_conciliacion SET estado_proceso_pago_id_vc=?, usuario_ultima_modificacion_nb=?, fecha_ultima_modificacion_ts=now() WHERE seq_detalle_conciliacion_nb=?"
        val sql2 = "UPDATE sfe_facturacion.tsfe_riv_movimientos SET estado_movimiento_id_vc=?, usuario_ultima_modificacion_nb=?, fecha_ultima_modificacion_ts=now() WHERE seq_detalle_conciliacion_nb=? AND tipo_movimiento_riv_id_vc=? AND estado_id_vc=?"
        for (beneficiario in beneficiarios) {
            lst += arrayOf("PAG", 1000, beneficiario.id)
            lstMovimientos += arrayOf("CON",1000, beneficiario.id, "CRE", "AC")
        }
        val connection = db()
        try {
            val runner = QueryRunner()
            runner.batch(connection, sql, lst.toTypedArray())
            runner.batch(connection, sql2, lstMovimientos.toTypedArray())
        } catch (e: Exception) {
            // TODO: Eliminar la siguiente linea cuando todo este probado y funcione
            e.printStackTrace()
            LOG.error(e.cause.toString(), e);
        }
        DbUtils.closeQuietly(connection)
    }
}
