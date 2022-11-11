package cn.tuyucheng.taketoday.cachedrequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CachedBodyHttpServletRequestUnitTest {

	private CachedBodyServletInputStream servletInputStream;

	@AfterEach
	void cleanUp() throws IOException {
		if (null != servletInputStream) {
			servletInputStream.close();
		}
	}

	@Test
	void testGivenHttpServletRequestWithBody_whenCalledGetInputStream_thenGetsServletInputStreamWithSameBody() throws IOException {
		// given
		byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
		MockHttpServletRequest mockedHttpServletRequest = new MockHttpServletRequest();
		mockedHttpServletRequest.setContent(cachedBody);
		CachedBodyHttpServletRequest request = new CachedBodyHttpServletRequest(mockedHttpServletRequest);

		// when
		InputStream inputStream = request.getInputStream();

		// then
		assertEquals(new String(cachedBody), new String(StreamUtils.copyToByteArray(inputStream)));
	}

	@Test
	void testGivenHttpServletRequestWithBody_whenCalledGetReader_thenGetBufferedReaderWithSameBody() throws IOException {
		// given
		byte[] cachedBody = "{\"firstName\" :\"abc\",\"lastName\" : \"xyz\",\"age\" : 30\"}".getBytes();
		MockHttpServletRequest mockedHttpServletRequest = new MockHttpServletRequest();
		mockedHttpServletRequest.setContent(cachedBody);
		CachedBodyHttpServletRequest request = new CachedBodyHttpServletRequest(mockedHttpServletRequest);

		// when
		BufferedReader bufferedReader = request.getReader();

		// then
		String line = "";
		StringBuilder builder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			builder.append(line);
		}
		assertEquals(new String(cachedBody), builder.toString());
	}
}