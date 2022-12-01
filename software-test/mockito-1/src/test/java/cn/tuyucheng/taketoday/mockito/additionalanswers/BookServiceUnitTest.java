package cn.tuyucheng.taketoday.mockito.additionalanswers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.answer;
import static org.mockito.AdditionalAnswers.answerVoid;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceUnitTest {
	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	void givenSaveMethodMocked_whenSaveInvoked_thenReturnFirstArgument_UnitTest() {
		Book book = new Book("To Kill a Mocking Bird", "Harper Lee", 256);
		when(bookRepository.save(any(Book.class))).then(AdditionalAnswers.returnsFirstArg());

		Book savedBook = bookService.save(book);

		assertEquals(savedBook, book);
	}

	@Test
	void givenCheckIfEqualsMethodMocked_whenCheckIfEqualsInvoked_thenReturnSecondArgument_UnitTest() {
		Book book1 = new Book(1L, "The Stranger", "Albert Camus", 456);
		Book book2 = new Book(2L, "Animal Farm", "George Orwell", 300);
		Book book3 = new Book(3L, "Romeo and Juliet", "William Shakespeare", 200);

		when(bookRepository.selectRandomBook(any(Book.class), any(Book.class), any(Book.class))).then(AdditionalAnswers.returnsSecondArg());

		Book secondBook = bookService.selectRandomBook(book1, book2, book3);

		assertEquals(secondBook, book2);
	}

	@Test
	void givenCheckIfEqualsMethodMocked_whenCheckIfEqualsInvoked_thenReturnLastArgument_UnitTest() {
		Book book1 = new Book(1L, "The Stranger", "Albert Camus", 456);
		Book book2 = new Book(2L, "Animal Farm", "George Orwell", 300);
		Book book3 = new Book(3L, "Romeo and Juliet", "William Shakespeare", 200);

		when(bookRepository.selectRandomBook(any(Book.class), any(Book.class), any(Book.class))).then(AdditionalAnswers.returnsLastArg());

		Book lastBook = bookService.selectRandomBook(book1, book2, book3);

		assertEquals(lastBook, book3);
	}

	@Test
	void givenCheckIfEqualsMethodMocked_whenCheckIfEqualsInvoked_ThenReturnArgumentAtIndex_UnitTest() {
		Book book1 = new Book(1L, "The Stranger", "Albert Camus", 456);
		Book book2 = new Book(2L, "Animal Farm", "George Orwell", 300);
		Book book3 = new Book(3L, "Romeo and Juliet", "William Shakespeare", 200);

		when(bookRepository.selectRandomBook(any(Book.class), any(Book.class), any(Book.class))).then(AdditionalAnswers.returnsArgAt(1));

		Book bookOnIndex = bookService.selectRandomBook(book1, book2, book3);

		assertEquals(bookOnIndex, book2);
	}

	@Test
	void givenMockedMethod_whenMethodInvoked_thenReturnBook() {
		Long id = 1L;
		when(bookRepository.getByBookId(anyLong())).thenAnswer(answer(BookServiceUnitTest::buildBook));

		assertNotNull(bookService.getByBookId(id));
		assertEquals("The Stranger", bookService.getByBookId(id).getTitle());
	}

	private static Book buildBook(Long bookId) {
		return new Book(bookId, "The Stranger", "Albert Camus", 456);
	}

	@Test
	void givenMockedMethod_whenMethodInvoked_thenReturnVoid() {
		Long id = 2L;
		when(bookRepository.getByBookId(anyLong())).thenAnswer(answerVoid(BookServiceUnitTest::printBookId));

		bookService.getByBookId(id);

		verify(bookRepository, times(1)).getByBookId(id);
	}

	private static void printBookId(Long bookId) {
		System.out.println(bookId);
	}
}