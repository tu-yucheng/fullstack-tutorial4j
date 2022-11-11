package cn.tuyucheng.taketoday.wiring.configuration.inject;

import cn.tuyucheng.taketoday.dependency.ArbitraryDependency;
import cn.tuyucheng.taketoday.wiring.configuration.ApplicationContextTestInjectName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;
import javax.inject.Named;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		loader = AnnotationConfigContextLoader.class,
		classes = ApplicationContextTestInjectName.class
)
class FieldByNameInjectIntegrationTest {

	@Inject
	@Named("yetAnotherFieldInjectDependency")
	private ArbitraryDependency yetAnotherFieldInjectDependency;

	@Test
	void givenInjectQualifier_WhenSetOnField_ThenDependencyValid() {
		assertNotNull(yetAnotherFieldInjectDependency);
		assertEquals("Yet Another Arbitrary Dependency", yetAnotherFieldInjectDependency.toString());
	}
}