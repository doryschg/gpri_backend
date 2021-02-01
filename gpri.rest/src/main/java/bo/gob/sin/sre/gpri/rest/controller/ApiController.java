package bo.gob.sin.sre.gpri.rest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import bo.gob.sin.str.caut.security.jwt.model.JwtUserDetails;

public class ApiController {

	public ApiController() {
	}

	public Long usuarioId() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication authorization = ctx.getAuthentication();
		JwtUserDetails detalleUsuario = (JwtUserDetails) authorization.getPrincipal();
		return detalleUsuario.getId();
	}

	public String token() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication authorization = ctx.getAuthentication();
		JwtUserDetails detalleUsuario = (JwtUserDetails) authorization.getPrincipal();
		return detalleUsuario.getToken();
	}

}
