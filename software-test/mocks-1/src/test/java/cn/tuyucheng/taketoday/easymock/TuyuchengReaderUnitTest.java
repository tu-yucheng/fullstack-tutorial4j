package cn.tuyucheng.taketoday.easymock;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TuyuchengReaderUnitTest {

	private TuyuchengReader tuyuchengReader;

	private ArticleReader mockArticleReader;

	private ArticleWriter mockArticleWriter;

	@Test
	void givenTuyuchengReader_whenReadNext_thenNextArticleRead() {
		mockArticleReader = mock(ArticleReader.class);
		tuyuchengReader = new TuyuchengReader(mockArticleReader);

		expect(mockArticleReader.next()).andReturn(null);
		replay(mockArticleReader);

		TuyuchengArticle article = tuyuchengReader.readNext();
		verify(mockArticleReader);
		assertNull(article);
	}

	@Test
	void givenTuyuchengReader_whenReadNextAndSkimTopics_thenAllAllowed() {
		mockArticleReader = strictMock(ArticleReader.class);
		tuyuchengReader = new TuyuchengReader(mockArticleReader);

		expect(mockArticleReader.next()).andReturn(null);
		expect(mockArticleReader.ofTopic("easymock")).andReturn(null);
		replay(mockArticleReader);

		tuyuchengReader.readNext();
		tuyuchengReader.readTopic("easymock");
		verify(mockArticleReader);
	}

	@Test
	void givenTuyuchengReader_whenReadNextAndOthers_thenAllowed() {
		mockArticleReader = niceMock(ArticleReader.class);
		tuyuchengReader = new TuyuchengReader(mockArticleReader);

		expect(mockArticleReader.next()).andReturn(null);
		replay(mockArticleReader);

		tuyuchengReader.readNext();
		tuyuchengReader.readTopic("easymock");
		verify(mockArticleReader);
	}

	@Test
	void givenTuyuchengReader_whenWriteMaliciousContent_thenArgumentIllegal() {
		mockArticleWriter = mock(ArticleWriter.class);
		tuyuchengReader = new TuyuchengReader(mockArticleWriter);
		expect(mockArticleWriter.write("easymock", "<body onload=alert('baeldung')>")).andThrow(new IllegalArgumentException());
		replay(mockArticleWriter);

		Exception expectedException = null;
		try {
			tuyuchengReader.write("easymock", "<body onload=alert('baeldung')>");
		} catch (Exception exception) {
			expectedException = exception;
		}

		verify(mockArticleWriter);
		assertEquals(IllegalArgumentException.class, expectedException.getClass());
	}

	@Test
	void givenTuyuchengReader_whenWrite_thenWriterCalled() {
		mockArticleWriter = mock(ArticleWriter.class);
		tuyuchengReader = new TuyuchengReader(mockArticleWriter);
		expect(mockArticleWriter.write("title", "content")).andReturn(null);
		replay(mockArticleWriter);
		String articleId = tuyuchengReader.write("title", "content");
		verify(mockArticleWriter);
		assertNull(articleId);
	}

	@Test
	void givenArticlesInReader_whenReadTillEnd_thenThrowException() {
		ArticleReader mockArticleReader = mock(ArticleReader.class);
		tuyuchengReader = new TuyuchengReader(mockArticleReader);
		expect(mockArticleReader.next())
				.andReturn(null)
				.times(2)
				.andThrow(new NoSuchElementException());
		replay(mockArticleReader);
		try {
			for (int i = 0; i < 3; i++) {
				tuyuchengReader.readNext();
			}
		} catch (Exception ignored) {
		}
		verify(mockArticleReader);
	}
}