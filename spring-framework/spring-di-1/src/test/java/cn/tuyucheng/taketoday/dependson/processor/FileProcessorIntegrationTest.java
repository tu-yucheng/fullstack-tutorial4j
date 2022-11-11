package cn.tuyucheng.taketoday.dependson.processor;

import cn.tuyucheng.taketoday.dependson.config.TestConfig;
import cn.tuyucheng.taketoday.dependson.shared.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class FileProcessorIntegrationTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	File file;

	@Test
	void whenAllBeansCreated_FileTextEndsWithProcessed() {
		context.getBean("fileProcessor");
		assertTrue(file.getText().endsWith("processed"));
	}

	@Test
	void whenDependentBeanNotAvailable_ThrowsNoSuchBeanDefinitionException() {
		assertThrows(BeanCreationException.class, () -> context.getBean("dummyFileProcessor"));
	}

	@Test
	void whenCircularDependency_ThrowsBeanCreationException() {
		assertThrows(BeanCreationException.class, () -> context.getBean("dummyFileReaderCircular"));
	}
}