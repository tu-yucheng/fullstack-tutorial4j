package cn.tuyucheng.taketoday.multireleaseapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHelper {

	// private static final Logger logger = LoggerFactory.getLogger(DateHelper.class);
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DateHelper.class.getName());

	public static boolean checkIfLeapYear(String dateStr) throws Exception {

		LOGGER.info("Checking for leap year using Java 1 calendar API");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
		int year = cal.get(Calendar.YEAR);
		return (new GregorianCalendar()).isLeapYear(year);
	}
}