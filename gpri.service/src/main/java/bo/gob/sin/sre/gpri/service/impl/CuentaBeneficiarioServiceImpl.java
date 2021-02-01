package bo.gob.sin.sre.gpri.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bo.gob.sin.sre.gpri.client.IPersonaNaturalRestclient;
import bo.gob.sin.sre.gpri.domain.ICuentaBeneficiarioHistoricoDomain;
import bo.gob.sin.sre.gpri.dto.BeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.CuentaBeneficiarioDto;
import bo.gob.sin.sre.gpri.dto.ResultadoGenericoDto;
import bo.gob.sin.sre.gpri.service.ICuentaBeneficiarioService;
import bo.gob.sin.str.cmsj.mapl.domain.IMensajeAplicacionDomain;

@Service
@Transactional("transactionManager")
public class CuentaBeneficiarioServiceImpl implements ICuentaBeneficiarioService {
    private static final Logger LOG = LoggerFactory.getLogger(CuentaBeneficiarioServiceImpl.class);
    @Autowired
    private ICuentaBeneficiarioHistoricoDomain beneficiarioDomain;

    @Autowired
    private IMensajeAplicacionDomain mensajesDomain;

    @Autowired
    private IPersonaNaturalRestclient personaNaturalRestclient;


    @Override
    public ResultadoGenericoDto<BeneficiarioDto> insertarCuentaBeneficiario(CuentaBeneficiarioDto pBeneficiario) {
        return null;
    }

    @Override
    public ResultadoGenericoDto<String> updateCuentaBeneficiario(CuentaBeneficiarioDto pBeneficiario) {
        return null;
    }
}
