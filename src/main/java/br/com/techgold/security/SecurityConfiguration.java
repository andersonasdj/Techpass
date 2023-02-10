package br.com.techgold.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDatailsService userDatailsService;
	
	@Autowired
	SecurityFilter securityFilter;
	
	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDatailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/create").permitAll()
		.antMatchers("/usuario/create").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario/create").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated()
		.and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
		.httpBasic()
		.and().formLogin().loginPage("/login")
		.defaultSuccessUrl("/home")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login")
		.logoutUrl("/logout")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID");
    }
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/assets/**");
	}

}
