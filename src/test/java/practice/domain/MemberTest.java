package practice.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {
	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void testInsert() {
		for (int i = 0; i <= 100; i++) {
			Member member = new Member();
			member.setUname("name" + i);
			member.setUpw("pw" + i);
			member.setEmail("email@" + i);

			MemberRole role = new MemberRole();
			if (i < 80) {
				role.setRoleName("BASIC");
			} else if (i <= 90) {
				role.setRoleName("MANAGER");
			} else {
				role.setRoleName("ADMIN");
			}
			member.setRoles(Arrays.asList(role));

			memberRepository.save(member);
		}
	}
}
