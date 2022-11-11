package cn.tuyucheng.taketoday.wiring.configuration.resource;

import cn.tuyucheng.taketoday.wiring.configuration.ApplicationContextTestResourceQualifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
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
		classes = ApplicationContextTestResourceQualifier.class
)
class MethodByQualifierResourceIntegrationTest {

	private File arbDependency;

	private File anotherArbDependency;

	@Resource
	@Qualifier("namedFile")
	public void setArbDependency(File arbDependency) {
		this.arbDependency = arbDependency;
	}

	@Resource
	@Qualifier("defaultFile")
	public void setAnotherArbDependency(File anotherArbDependency) {
		this.anotherArbDependency = anotherArbDependency;
	}

	@Test
	void givenResourceQualifier_WhenSetter_ThenValidDependencies() {
		assertNotNull(arbDependency);
		assertEquals("namedFile.txt", arbDependency.getName());
		assertNotNull(anotherArbDependency);
		assertEquals("defaultFile.txt", anotherArbDependency.getName());
	}
}