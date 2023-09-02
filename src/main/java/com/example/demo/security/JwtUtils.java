package com.example.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.service.to.DatosTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
	private static final Logger LOG=LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${app.jwt.secret}")
	private String jwtSecret;
	    
	@Value("${app.jwt.expiration.ms}")
	private int jwtExpiration;
	
//	public String generateJwtToken(String nombre) {
//		
//		LOG.info("Semilla: "+jwtSecret+" Tiempo: "+jwtExpiration);
//		
//		return Jwts.builder().setSubject(nombre)
//				.setIssuedAt(new Date())
//				.setExpiration(new Date(System.currentTimeMillis()+this.jwtExpiration))
//				.signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
//	}
	
	public String generateJwtToken(String nombre, DatosTO datos) {
	
	LOG.info("Semilla: "+datos.getSemilla()+" Tiempo: "+datos.getTiempo());
	jwtSecret=datos.getSemilla();
	jwtExpiration=datos.getTiempo();
	
	return Jwts.builder().setSubject(nombre)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis()+this.jwtExpiration))
			.signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
}
	


}
