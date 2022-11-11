package cn.tuyucheng.taketoday.junit5.bean.test;

import cn.tuyucheng.taketoday.junit5.bean.NumbersBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link NumbersBean}.
 *
 * @author tuyucheng
 */
class NumbersBeanUnitTest {

	/**
	 * The bean to test.
	 */
	private final NumbersBean bean = new NumbersBean();

	/**
	 * Tests that when an even number is passed to {@link NumbersBean#isNumberEven(int)}, true is returned.
	 */
	@Test
	void givenEvenNumber_whenCheckingIsNumberEven_thenTrue() {
		boolean result = bean.isNumberEven(8);

		assertTrue(result);
	}

	/**
	 * Tests that when an odd number is passed to
	 * {@link NumbersBean#isNumberEven(int)}, false is returned.
	 */
	@Test
	void givenOddNumber_whenCheckingIsNumberEven_thenFalse() {
		boolean result = bean.isNumberEven(3);
		assertFalse(result);
	}
}