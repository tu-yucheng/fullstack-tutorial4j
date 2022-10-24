package cn.tuyucheng.taketoday.cachedrequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CachedBodyServletInputStreamUnitTest {

    private CachedBodyServletInputStream servletInputStream;

    @AfterEach
    void cleanUp() throws IOException {
        if (null != servletInputStream) {
            servletInputStream.close();
        }
    }

    @Test
    void testGivenServletInputStreamCreated_whenCalledIsFinished_thenFalse() {
        // given
        byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
        servletInputStream = new CachedBodyServletInputStream(cachedBody);

        // when
        boolean finished = servletInputStream.isFinished();

        // then
        assertFalse(finished);
    }

    @Test
    void testGivenServletInputStreamCreatedAndBodyRead_whenCalledIsFinished_thenTrue() throws IOException {
        // given
        byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
        servletInputStream = new CachedBodyServletInputStream(cachedBody);
        StreamUtils.copyToByteArray(servletInputStream);

        // when
        boolean finished = servletInputStream.isFinished();

        // then
        assertTrue(finished);
    }

    @Test
    void testGivenServletInputStreamCreatedAndBodyRead_whenCalledIsReady_thenTrue() throws IOException {
        // given
        byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
        servletInputStream = new CachedBodyServletInputStream(cachedBody);

        // when
        boolean ready = servletInputStream.isReady();

        // then
        assertTrue(ready);
    }

    @Test
    void testGivenServletInputStreamCreated_whenCalledIsRead_thenReturnsBody() throws IOException {
        // given
        byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
        servletInputStream = new CachedBodyServletInputStream(cachedBody);

        // when
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = servletInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        // then
        assertEquals(new String(cachedBody), byteArrayOutputStream.toString());
    }

    @Test
    void testGivenServletInputStreamCreated_whenCalledIsRead_thenThrowsException() throws IOException {
        // given
        byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
        servletInputStream = new CachedBodyServletInputStream(cachedBody);

        // when
        assertThrows(UnsupportedOperationException.class, () -> servletInputStream.setReadListener(Mockito.mock(ReadListener.class)));
    }
}