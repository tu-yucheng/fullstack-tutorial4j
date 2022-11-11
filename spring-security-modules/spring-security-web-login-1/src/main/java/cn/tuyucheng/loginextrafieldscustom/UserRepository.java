package cn.tuyucheng.loginextrafieldscustom;

public interface UserRepository {

	User findUser(String username, String domain);
}