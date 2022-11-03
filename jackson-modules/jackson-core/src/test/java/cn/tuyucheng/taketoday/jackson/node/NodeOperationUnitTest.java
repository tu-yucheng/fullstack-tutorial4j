package cn.tuyucheng.taketoday.jackson.node;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NodeOperationUnitTest {
	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	void givenAnObject_whenConvertingIntoNode_thenCorrect() {
		final NodeBean fromValue = new NodeBean(2016, "tuyucheng.com");

		final JsonNode node = mapper.valueToTree(fromValue);

		assertEquals(2016, node.get("id").intValue());
		assertEquals("tuyucheng.com", node.get("name").textValue());
	}

	@Test
	void givenANode_whenWritingOutAsAJsonString_thenCorrect() throws IOException {
		final String pathToTestFile = "node_to_json_test.json";
		final char[] characterBuffer = new char[50];

		final JsonNode node = mapper.createObjectNode();
		((ObjectNode) node).put("id", 2016);
		((ObjectNode) node).put("name", "tuyucheng.com");

		try (FileWriter outputStream = new FileWriter(pathToTestFile)) {
			mapper.writeValue(outputStream, node);
		}

		try (FileReader inputStreamForAssertion = new FileReader(pathToTestFile)) {
			inputStreamForAssertion.read(characterBuffer);
		}
		final String textContentOfTestFile = new String(characterBuffer);

		assertThat(textContentOfTestFile, containsString("2016"));
		assertThat(textContentOfTestFile, containsString("tuyucheng.com"));

		Files.delete(Paths.get(pathToTestFile));
	}

	@Test
	void givenANode_whenConvertingIntoAnObject_thenCorrect() throws JsonProcessingException {
		final JsonNode node = mapper.createObjectNode();
		((ObjectNode) node).put("id", 2016);
		((ObjectNode) node).put("name", "tuyucheng.com");

		final NodeBean toValue = mapper.treeToValue(node, NodeBean.class);

		assertEquals(2016, toValue.getId());
		assertEquals("tuyucheng.com", toValue.getName());
	}

	@Test
	void givenANode_whenAddingIntoATree_thenCorrect() throws IOException {
		final JsonNode rootNode = ExampleStructure.getExampleRoot();
		final ObjectNode addedNode = ((ObjectNode) rootNode).putObject("address");
		addedNode.put("city", "Seattle")
				.put("state", "Washington")
				.put("country", "United States");

		assertFalse(rootNode.path("address")
				.isMissingNode());
		assertEquals("Seattle", rootNode.path("address")
				.path("city")
				.textValue());
		assertEquals("Washington", rootNode.path("address")
				.path("state")
				.textValue());
		assertEquals("United States", rootNode.path("address")
				.path("country")
				.textValue());
	}

	@Test
	void givenANode_whenModifyingIt_thenCorrect() throws IOException {
		final String newString = "{\"nick\": \"cowtowncoder\"}";
		final JsonNode newNode = mapper.readTree(newString);

		final JsonNode rootNode = ExampleStructure.getExampleRoot();
		((ObjectNode) rootNode).set("name", newNode);

		assertFalse(rootNode.path("name")
				.path("nick")
				.isMissingNode());
		assertEquals("cowtowncoder", rootNode.path("name")
				.path("nick")
				.textValue());
	}

	@Test
	void givenANode_whenRemovingFromATree_thenCorrect() throws IOException {
		final JsonNode rootNode = ExampleStructure.getExampleRoot();
		((ObjectNode) rootNode).remove("company");

		assertTrue(rootNode.path("company").isMissingNode());
	}
}