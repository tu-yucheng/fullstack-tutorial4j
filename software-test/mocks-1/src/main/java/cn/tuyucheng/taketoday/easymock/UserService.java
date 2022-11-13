package cn.tuyucheng.taketoday.easymock;

import java.util.List;

public interface UserService {
	boolean addUser(User user);

	List<User> findByEmail(String email);

	List<User> findByAge(double age);
}