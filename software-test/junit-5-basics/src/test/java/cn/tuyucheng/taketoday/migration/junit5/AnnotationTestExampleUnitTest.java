package cn.tuyucheng.taketoday.migration.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@Tag("annotations")
@Tag("junit5")
class AnnotationTestExampleUnitTest {

    @Test
    void shouldRaiseAnException() throws Exception {
        assertThrows(Exception.class, () -> {
            throw new Exception("This is my expected exception");
        });
    }

    @Test
    @Disabled
    void shouldFailBecauseTimeout() throws InterruptedException {
        assertTimeout(Duration.ofMillis(1), () -> Thread.sleep(10));
    }
}