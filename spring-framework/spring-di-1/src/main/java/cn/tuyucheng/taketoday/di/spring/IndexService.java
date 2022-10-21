package cn.tuyucheng.taketoday.di.spring;

public class IndexService implements Service {

    @Override
    public String serve() {
        return "Hello World";
    }
}