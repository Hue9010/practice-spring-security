package practice.service;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import practice.domain.Member;
import practice.domain.MemberRepository;
import practice.domain.SecurityMember;

@Service("securityUsersService")
public class SecurityUsersService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(SecurityUsersService.class);

	@Resource(name="memberRepository")
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("try username : {}", username);

		Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("이메일 혹은 비밀번호가 잘 못 됐습니다."));
		log.debug("try login member : {}", member);
		return new SecurityMember(member);
	}

}
