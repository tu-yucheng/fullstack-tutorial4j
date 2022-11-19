package cn.tuyucheng.taketoday.boot.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cn.tuyucheng.taketoday.boot.domain.User;
import cn.tuyucheng.taketoday.boot.repository.UserRepository;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("multiplesqlfiles")
class UserRepositoryMultipleSqlFilesIntTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void givenTwoImportFilesWhenFindAllShouldReturnSixUsers() {
		Collection<User> users = userRepository.findAll();

		assertThat(users.size()).isEqualTo(6);
	}
}