package cn.tuyucheng.boot.bootstrapmode;

import cn.tuyucheng.boot.bootstrapmode.domain.Todo;
import cn.tuyucheng.boot.bootstrapmode.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BootstrapModeDefaultIntegrationTest {

	@Autowired
	private TodoRepository todoRepository;

	@Test
	void givenBootstrapModeValueIsDefault_whenCreatingTodo_shouldSuccess() {
		Todo todo = new Todo("Something to be done");

		assertThat(todoRepository.save(todo)).hasNoNullFieldsOrProperties();
	}
}