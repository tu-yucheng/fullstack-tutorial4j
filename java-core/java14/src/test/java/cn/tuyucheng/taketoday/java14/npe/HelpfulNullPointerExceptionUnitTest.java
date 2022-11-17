package cn.tuyucheng.taketoday.java14.npe;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HelpfulNullPointerExceptionUnitTest {

	@Test
	void givenAnEmptyPersonalDetails_whenEmailAddressIsAccessed_thenThrowNPE() {
		var helpfulNPE = new HelpfulNullPointerException();

		var employee = new HelpfulNullPointerException.Employee();
		employee.setName("Eduard");
		employee.setPersonalDetails(new HelpfulNullPointerException.PersonalDetails());
		assertThrows(NullPointerException.class, () -> helpfulNPE.getEmployeeEmailAddress(employee));
	}

	@Test
	void givenCompletePersonalDetails_whenEmailAddressIsAccessed_thenSuccess() {
		var helpfulNPE = new HelpfulNullPointerException();
		var emailAddress = "eduard@gmx.com";

		var employee = new HelpfulNullPointerException.Employee();
		employee.setName("Eduard");

		var personalDetails = new HelpfulNullPointerException.PersonalDetails();
		personalDetails.setEmailAddress(emailAddress.toUpperCase());
		personalDetails.setPhone("1234");
		employee.setPersonalDetails(personalDetails);

		assertThat(helpfulNPE.getEmployeeEmailAddress(employee)).isEqualTo(emailAddress);
	}
}