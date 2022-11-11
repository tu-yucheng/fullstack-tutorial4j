package cn.tuyucheng.loginextrafieldssimple;

public interface UserRepository {

	User findUser(String username, String domain);
}