package com.uce.edu.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edu.demo.security.JwtUtils;
import com.uce.edu.demo.service.to.UsuarioTO;

@RestController
@CrossOrigin
@RequestMapping("/tokens")
public class TokenControllerRestFull {
	
	private static final Logger LOG=LoggerFactory.getLogger(TokenControllerRestFull.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private JwtUtils jwtUtils;


	    @GetMapping("/obtener")
	    public String getToken(@RequestBody UsuarioTO usuario){
	
	    	
	        this.authenticated(usuario.getUsername(), usuario.getPassword());
	        return this.jwtUtils.generateJwtToken(usuario.getUsername());
	    }

	

    private void authenticated(String usuario,String password){
        Authentication authentication=this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
