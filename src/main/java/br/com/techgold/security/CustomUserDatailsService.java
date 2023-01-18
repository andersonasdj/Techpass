package br.com.techgold.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.techgold.model.Usuario;
import br.com.techgold.repository.UsuarioRepository;

@Service
public class CustomUserDatailsService implements UserDetailsService{

	@Autowired
	UsuarioRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = repository.findByUsername(username);
		System.out.println(user.getName());
		
		if(user == null) {
			throw new Error("User does not exists!");
		}
		
		return UserPrincipal.create(user);
		
	}
	
	 

}
