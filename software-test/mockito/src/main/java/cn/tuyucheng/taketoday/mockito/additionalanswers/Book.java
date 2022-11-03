package cn.tuyucheng.taketoday.mockito.additionalanswers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    private Long bookId;

    private String title;

    private String author;

    private int numberOfPages;

    public Book(String title, String author, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book(Long bookId, String title, String author, int numberOfPages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }
}