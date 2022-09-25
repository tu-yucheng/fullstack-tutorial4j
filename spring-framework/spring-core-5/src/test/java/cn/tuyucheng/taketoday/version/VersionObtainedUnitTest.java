package cn.tuyucheng.taketoday.version;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = VersionObtained.class)
class VersionObtainedUnitTest {

    public VersionObtained version = new VersionObtained();

    @Test
    void testGetSpringVersion() {
        String res = version.getSpringVersion();
        assertEquals("5.3.13", SpringVersion.getVersion());
        assertThat(res).isNotEmpty();
    }

    @Test
    void testGetJdkVersion() {
        String res = version.getJdkVersion();
        assertEquals("17.0.1", res);
        assertThat(res).isNotEmpty();
    }

    @Test
    void testGetJavaVersion() {
        String res = version.getJavaVersion();
        assertThat(res).isNotEmpty();
    }
}