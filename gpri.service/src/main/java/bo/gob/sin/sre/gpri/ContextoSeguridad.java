package bo.gob.sin.sre.gpri;

import bo.gob.sin.str.caut.security.jwt.model.JwtUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class ContextoSeguridad {

	public JwtUserDetails obtener()
	{
		JwtUserDetails detalleUsuario = new JwtUserDetails();
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication authorization = ctx.getAuthentication();
		if (authorization != null && !authorization.getPrincipal().equals("anonymousUser")) {
			detalleUsuario = (JwtUserDetails) authorization.getPrincipal();
		} 
		return detalleUsuario;
	}
}
