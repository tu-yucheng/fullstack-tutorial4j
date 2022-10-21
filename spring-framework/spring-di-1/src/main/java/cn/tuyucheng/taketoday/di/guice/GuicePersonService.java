package cn.tuyucheng.taketoday.di.guice;

import cn.tuyucheng.taketoday.di.spring.PersonDao;
import com.google.inject.Inject;

public class GuicePersonService {

    private PersonDao personDao;

    @Inject
    public GuicePersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public PersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }
}