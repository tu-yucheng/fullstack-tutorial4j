package cn.tuyucheng.taketoday.jmockit;

import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class AppManagerUnitTest {

	private AppManager appManager;

	@BeforeEach
	void setUp() {
		appManager = new AppManager();
	}

	@Test
	void givenAppManager_whenStaticMethodCalled_thenValidateExpectedResponse() {
		new MockUp<AppManager>() {
			@Mock
			public boolean isResponsePositive(String value) {
				return false;
			}
		};

		assertFalse(appManager.managerResponse("Why are you coming late?"));
	}
}