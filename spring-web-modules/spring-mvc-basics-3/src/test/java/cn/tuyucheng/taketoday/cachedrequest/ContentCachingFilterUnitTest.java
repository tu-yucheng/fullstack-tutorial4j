package cn.tuyucheng.taketoday.cachedrequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContentCachingFilterUnitTest {

	@InjectMocks
	private ContentCachingFilter filterToTest;

	@Test
	void testGivenHttpRequest_whenDoFilter_thenCreatesRequestWrapperObject() throws IOException, ServletException {
		// given
		MockHttpServletRequest mockedRequest = new MockHttpServletRequest();
		MockHttpServletResponse mockedResponse = new MockHttpServletResponse();
		FilterChain mockedFilterChain = mock(FilterChain.class);

		// when
		filterToTest.doFilter(mockedRequest, mockedResponse, mockedFilterChain);

		// then
		verify(mockedFilterChain, times(1))
				.doFilter(any(CachedBodyHttpServletRequest.class), any(MockHttpServletResponse.class));
	}
}