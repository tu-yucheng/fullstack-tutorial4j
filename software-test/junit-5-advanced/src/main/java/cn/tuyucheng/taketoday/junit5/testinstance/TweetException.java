package cn.tuyucheng.taketoday.junit5.testinstance;

import java.io.Serial;

public class TweetException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TweetException(String message) {
        super(message);
    }
}