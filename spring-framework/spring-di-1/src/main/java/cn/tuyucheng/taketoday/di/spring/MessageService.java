package cn.tuyucheng.taketoday.di.spring;

public class MessageService implements Service {

    private final String message;

    public MessageService(String message) {
        this.message = message;
    }

    @Override
    public String serve() {
        return message;
    }
}