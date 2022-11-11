package cn.tuyucheng.taketoday.wiring.configuration.autowired;

import cn.tuyucheng.taketoday.dependency.ArbitraryDependency;
import cn.tuyucheng.taketoday.wiring.configuration.ApplicationContextTestAutowiredName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class,
		classes = ApplicationContextTestAutowiredName.class
)
class FieldAutowiredNameIntegrationTest {

	@Autowired
	private ArbitraryDependency autowiredFieldDependency;

	@Test
	void givenAutowiredAnnotation_WhenOnField_ThenDependencyValid() {
		assertNotNull(autowiredFieldDependency);
		assertEquals("Arbitrary Dependency", autowiredFieldDependency.toString());
	}
}