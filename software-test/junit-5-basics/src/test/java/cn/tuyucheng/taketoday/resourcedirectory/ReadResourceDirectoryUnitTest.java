package cn.tuyucheng.taketoday.resourcedirectory;

import cn.tuyucheng.taketoday.migration.junit5.extensions.TraceUnitExtension;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReadResourceDirectoryUnitTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TraceUnitExtension.class);

	@Test
	void givenResourcePath_whenReadAbsolutePathWithFile_thenAbsolutePathEndsWithDirectory() {
		String path = "src/test/resources";

		File file = new File(path);
		String absolutePath = file.getAbsolutePath();

		LOGGER.debug(absolutePath);
		assertTrue(absolutePath.endsWith("src" + File.separator + "test" + File.separator + "resources"));
	}

	@Test
	void givenResourcePath_whenReadAbsolutePathWithPaths_thenAbsolutePathEndsWithDirectory() {
		Path resourceDirectory = Paths.get("src", "test", "resources");

		String absolutePath = resourceDirectory.toFile().getAbsolutePath();

		LOGGER.debug(absolutePath);
		assertTrue(absolutePath.endsWith("src" + File.separator + "test" + File.separator + "resources"));
	}

	@Test
	void givenResourceFile_whenReadResourceWithClassLoader_thenPathEndWithFilename() {
		String resourceName = "example_resource.txt";

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(resourceName).getFile());
		String absolutePath = file.getAbsolutePath();

		LOGGER.debug(absolutePath);
		assertTrue(absolutePath.endsWith(File.separator + "example_resource.txt"));
	}
}