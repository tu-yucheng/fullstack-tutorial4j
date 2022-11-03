package cn.tuyucheng.taketoday;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

class CopyFileUnitTest {

	@Test
	void whenCopyingAFileFromSourceToDestination_thenFileShouldBeInDestination() {
		File destinationFile = new File("target/destination-folder/foo.txt");
		assertEquals(true, destinationFile.exists());
	}
}