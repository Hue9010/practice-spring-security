package practice.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityUsersService")
public class SecurityUsersService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(SecurityUsersService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User sampleUser = new User(username, "1111", 
				Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));
		return sampleUser;
	}

}
