package cn.tuyucheng.taketoday.mockito.bddmockito;

public interface PhoneBookRepository {

	void insert(String name, String phone);

	String getPhoneNumberByContactName(String name);

	boolean contains(String name);
}