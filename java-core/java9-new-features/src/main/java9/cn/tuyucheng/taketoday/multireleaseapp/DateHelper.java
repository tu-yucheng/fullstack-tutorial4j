package cn.tuyucheng.taketoday.multireleaseapp;

import java.time.LocalDate;

public class DateHelper {

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DateHelper.class.getName());

	public static boolean checkIfLeapYear(String dateStr) throws Exception {
		LOGGER.info("Checking for leap year using Java 9 Date Api");
		return LocalDate.parse(dateStr).isLeapYear();
	}
}