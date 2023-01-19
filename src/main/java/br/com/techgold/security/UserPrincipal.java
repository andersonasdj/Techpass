package br.com.techgold.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.techgold.model.Usuario;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public UserPrincipal(Usuario user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	public static UserPrincipal create(Usuario user) {
		return new UserPrincipal(user);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
