package com.uce.edu.demo.service;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IUsuarioRepository;
import com.uce.edu.demo.repository.modelo.Usuario;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	//Usamos UserDetailsService para no usar la interfaz del service
	
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario=this.iUsuarioRepository.consultarPorUsername(username);
		return new User(usuario.getUsername(), usuario.getPassword(), emptyList());
	}

	
}
