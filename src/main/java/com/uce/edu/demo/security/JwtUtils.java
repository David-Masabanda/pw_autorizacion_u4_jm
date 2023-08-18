package com.uce.edu.demo.security;

import java.util.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
	
    private static final Logger LOG= LoggerFactory.getLogger(JwtUtils.class);
    @Value("${app.jwt.secret}")

    private String jwtSecret;
    @Value("${app.jwt.expiration.ms}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication, String name) {
    	LOG.info("Semilla: "+jwtSecret);
        LOG.info("Tiempo: "+jwtExpiration);

        return Jwts.builder().setSubject(name).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + this.jwtExpiration)).
                signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
    }
    
    
    public String generateJwtToken(String name) {
        LOG.info("Semilla: "+jwtSecret);
        LOG.info("Tiempo: "+jwtExpiration);

        return Jwts.builder().setSubject(name).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis() + this.jwtExpiration)).
                signWith(SignatureAlgorithm.HS512, this.jwtSecret).compact();
    }
    
}
