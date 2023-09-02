package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.modelo.Usuario;
import com.example.demo.security.JwtUtils;
import com.example.demo.service.to.DatosTO;

@RestController
@CrossOrigin
@RequestMapping("/tokens")
public class TokenControllerRestFul {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	private static final Logger LOG=LoggerFactory.getLogger(TokenControllerRestFul.class);
	
//	@GetMapping(path = "/obtener")
//	public String obtenerToken(@RequestBody Usuario usuario) {
//		
//		LOG.info(usuario.getNombre()+" "+usuario.getPassword());
//		this.authenticated(usuario.getNombre(), usuario.getPassword());
//		return this.jwtUtils.generateJwtToken(usuario.getNombre());
//	}
	
	@GetMapping(path = "/obtener")
	public String obtenerToken(@RequestBody DatosTO datos) {
		this.authenticated(datos.getNombre(), datos.getPassword());
		return this.jwtUtils.generateJwtToken(datos.getNombre(), datos);
	}
	
	@GetMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public Usuario obtenerUsuario(@PathVariable String usuario) {
		return this.usuarioRepository.consultarUsuario(usuario);
	}
	
    private void authenticated(String usuario,String password){
        Authentication authentication=this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
	

}
