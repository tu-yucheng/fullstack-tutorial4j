package cn.tuyucheng.boot.readonlyrepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ReadOnlyRepositoryApplication.class)
class ReadOnlyRepositoryUnitTest {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReadOnlyRepository bookReadOnlyRepository;

    @Test
    void givenBooks_whenUsingReadOnlyRepository_thenGetThem() {
        Book aChristmasCarolCharlesDickens = new Book();
        aChristmasCarolCharlesDickens.setTitle("A Christmas Carol");
        aChristmasCarolCharlesDickens.setAuthor("Charles Dickens");
        bookRepository.save(aChristmasCarolCharlesDickens);

        Book greatExpectationsCharlesDickens = new Book();
        greatExpectationsCharlesDickens.setTitle("Great Expectations");
        greatExpectationsCharlesDickens.setAuthor("Charles Dickens");
        bookRepository.save(greatExpectationsCharlesDickens);

        Book greatExpectationsKathyAcker = new Book();
        greatExpectationsKathyAcker.setTitle("Great Expectations");
        greatExpectationsKathyAcker.setAuthor("Kathy Acker");
        bookRepository.save(greatExpectationsKathyAcker);

        List<Book> charlesDickensBooks = bookReadOnlyRepository.findByAuthor("Charles Dickens");
        assertEquals(2, charlesDickensBooks.size());

        List<Book> greatExpectationsBooks = bookReadOnlyRepository.findByTitle("Great Expectations");
        assertEquals(2, greatExpectationsBooks.size());

        List<Book> allBooks = bookReadOnlyRepository.findAll();
        assertEquals(3, allBooks.size());

        Long bookId = allBooks.get(0).getId();
        Book book = bookReadOnlyRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        assertNotNull(book);
    }
}