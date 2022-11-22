package cn.tuyucheng.taketoday.spring.data.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl implements FooService {
	@Autowired
	private FooDAO dao;

	@Override
	public Foo create(Foo foo) {
		return dao.save(foo);
	}
}