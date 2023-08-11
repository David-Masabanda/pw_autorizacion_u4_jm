package com.example.demo.service;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IUsuarioRepository;
import com.example.demo.repository.modelo.Usuario;
import com.example.demo.service.to.UsuarioTO;

@Service
public class UsuarioServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private UsuarioTO convertir(Usuario usuario) {
		UsuarioTO us=new UsuarioTO();
		us.setNombre(usuario.getNombre());
		us.setPassword(usuario.getPassword());
		return us;
	
	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=this.usuarioRepository.consultarUsuario(username);
		
		return new User(usuario.getNombre(), usuario.getPassword(), emptyList());
	}

}
