package cn.tuyucheng.taketoday.wiring.configuration.resource;

import cn.tuyucheng.taketoday.wiring.configuration.ApplicationContextTestResourceNameType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class,
		classes = ApplicationContextTestResourceNameType.class
)
class NamedResourceIntegrationTest {

	@Resource(name = "namedFile")
	private File testFile;

	@Test
	void givenResourceAnnotation_WhenOnField_THEN_DEPENDENCY_Found() {
		assertNotNull(testFile);
		assertEquals("namedFile.txt", testFile.getName());
	}
}