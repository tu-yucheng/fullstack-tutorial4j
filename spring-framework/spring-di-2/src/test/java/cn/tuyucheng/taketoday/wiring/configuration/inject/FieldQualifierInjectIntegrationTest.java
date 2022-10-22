package cn.tuyucheng.taketoday.wiring.configuration.inject;

import cn.tuyucheng.taketoday.dependency.ArbitraryDependency;
import cn.tuyucheng.taketoday.wiring.configuration.ApplicationContextTestInjectQualifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = ApplicationContextTestInjectQualifier.class
)
class FieldQualifierInjectIntegrationTest {
    
    @Inject
    @Qualifier("defaultFile")
    private ArbitraryDependency defaultDependency;

    @Inject
    @Qualifier("namedFile")
    private ArbitraryDependency namedDependency;

    @Test
    void givenInjectQualifier_WhenOnField_ThenDefaultFileValid() {
        assertNotNull(defaultDependency);
        assertEquals("Arbitrary Dependency", defaultDependency.toString());
    }

    @Test
    void givenInjectQualifier_WhenOnField_ThenNamedFileValid() {
        assertNotNull(defaultDependency);
        assertEquals("Another Arbitrary Dependency", namedDependency.toString());
    }
}