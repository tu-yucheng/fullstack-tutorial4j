package cn.tuyucheng.taketoday.jackson.node;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonNodeIteratorUnitTest {

	private final JsonNodeIterator onTest = new JsonNodeIterator();
	private static String expectedYaml = """
			name:\s
			  first: Tatu
			  last: Saloranta
			title: Jackson founder
			company: FasterXML
			pets:\s
			- type: dog
			  number: 1
			- type: fish
			  number: 50""";

	@Test
	void givenANodeTree_whenIteratingSubNodes_thenWeFindExpected() throws IOException {
		final JsonNode rootNode = ExampleStructure.getExampleRoot();

		String yaml = onTest.toYaml(rootNode);

		assertEquals(expectedYaml, yaml);
	}
}