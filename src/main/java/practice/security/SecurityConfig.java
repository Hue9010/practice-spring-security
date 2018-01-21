package practice.security;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import practice.service.SecurityUsersService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	@Resource(name="securityUsersService")
	private SecurityUsersService securityUsersService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config....");
		http.csrf().disable();
		
		http
			.authorizeRequests()
			.antMatchers("/guest/**", "/h2-console/**").permitAll()
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		http.formLogin().loginPage("/login");
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
		http.userDetailsService(securityUsersService);
	}
	
	
	//현재 securityUsersService로 구현 중
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//		log.info("build Auth global......");
//		
//		auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("1111")
//		.roles("MANAGER");
//	}
}
