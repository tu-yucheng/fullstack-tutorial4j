package cn.tuyucheng.taketoday.multireleaseapp;

public class App {

	// private static final Logger logger = LoggerFactory.getLogger(App.class);

	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(App.class.getName());

	public static void main(String[] args) throws Exception {
		String dateToCheck = args[0];
		boolean isLeapYear = DateHelper.checkIfLeapYear(dateToCheck);
		LOGGER.info("Date given " + dateToCheck + " is leap year: " + isLeapYear);
	}
}