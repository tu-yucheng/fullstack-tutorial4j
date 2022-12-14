package cn.tuyucheng.boot.bootstrapmode;

import cn.tuyucheng.boot.bootstrapmode.domain.Todo;
import cn.tuyucheng.boot.bootstrapmode.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(bootstrapMode = BootstrapMode.DEFERRED)
class BootstrapModeDeferredIntegrationTest {

	@Autowired
	private TodoRepository todoRepository;

	@Test
	void givenBootstrapModeValueIsDeferred_whenCreatingTodo_shouldSuccess() {
		Todo todo = new Todo("Something to be done");

		assertThat(todoRepository.save(todo)).hasNoNullFieldsOrProperties();
	}
}