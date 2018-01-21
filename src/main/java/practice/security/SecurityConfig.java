package practice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config....");
		http.csrf().disable();
		
		http
			.authorizeRequests()
			.antMatchers("/guest/**").permitAll()
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		http.formLogin().loginPage("/login");
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		log.info("build Auth global......");
		
		auth.inMemoryAuthentication()
		.withUser("manager")
		.password("1111")
		.roles("MANAGER");
	}
}
