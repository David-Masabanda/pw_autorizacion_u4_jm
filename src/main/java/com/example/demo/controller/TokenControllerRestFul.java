package com.example.demo.controller;

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
import com.example.demo.service.to.UsuarioTO;

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
	
	@GetMapping(path = "/obtener")
	public String obtenerToken(@RequestBody Usuario usuaio) {
		this.authenticated(usuaio.getNombre(), usuaio.getPassword());
		return this.jwtUtils.generateJwtToken(usuaio.getNombre());
		//return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkRhdmlkIE1hcmtzIiwiaWF0IjoxNTE2MjM5MDIyfQ.SjwhHLt3xMv3-nu7vszHf8YUJX1DA2_5UZ5MMwQBIM0";
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
