package com.agrosense.fiap.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agrosense.fiap.model.UsuarioModel;
import com.agrosense.fiap.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String nmEmail) throws UsernameNotFoundException {
		UsuarioModel usuario = this.usuarioRepository.findByNmEmail(nmEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(usuario.getNmEmail(), usuario.getNm_senha(), new ArrayList<>());
	}

}
