package cn.tuyucheng.taketoday.testcase;

public class LoginService {

	private LoginDao loginDao;

	private String currentUser;

	public boolean login(UserForm userForm) {
		assert null != userForm;
		int loginResults = loginDao.login(userForm);
		return loginResults == 1;
	}

	public void setCurrentUser(String username) {
		if (null != username) {
			this.currentUser = username;
		}
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	// standard setters and getters
}