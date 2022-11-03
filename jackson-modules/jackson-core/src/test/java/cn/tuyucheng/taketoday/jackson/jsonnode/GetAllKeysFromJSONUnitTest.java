package cn.tuyucheng.taketoday.jackson.jsonnode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllKeysFromJSONUnitTest {

	private static final String json = """
			{
			   "Name":"Craig",
			   "Age":10,
			   "BookInterests":[
			      {
			         "Book":"The Kite Runner",
			         "Author":"Khaled Hosseini"
			      },
			      {
			         "Book":"Harry Potter",
			         "Author":"J. K. Rowling"
			      }
			   ],
			   "FoodInterests":{
			      "Breakfast":[
			         {
			            "Bread":"Whole wheat",
			            "Beverage":"Fruit juice"
			         },
			         {
			            "Sandwich":"Vegetable Sandwich",
			            "Beverage":"Coffee"
			         }
			      ]
			   }
			}""";

	private static final ObjectMapper mapper = new ObjectMapper();
	private static final GetAllKeysFromJSON getAllKeysFromJSONUtil = new GetAllKeysFromJSON();

	// Top level keys : [Name, Age, BookInterests, FoodInterests]
	// All keys: [Name, Age, BookInterests, Book, Author, Book, Author, FoodInterests, Breakfast, Bread, Beverage, Sandwich, Beverage]

	@Test
	void givenAJsonNode_whenUsingFieldNamesMethod_thenWeGetTopFieldNames() {
		List<String> keys;
		try {
			keys = getAllKeysFromJSONUtil.getKeysInJsonUsingJsonNodeFieldNames(json, mapper);
			assertEquals(4, keys.size());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	void givenAJsonNode_whenUsingFieldNamesMethodForAllNodes_thenWeGetAllFieldNames() {
		List<String> keys;
		try {
			keys = getAllKeysFromJSONUtil.getAllKeysInJsonUsingJsonNodeFieldNames(json, mapper);
			assertEquals(13, keys.size());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	void givenAJsonNode_whenUsingFieldsMethod_thenWeGetAllFieldNames() {
		List<String> keys;
		try {
			keys = getAllKeysFromJSONUtil.getAllKeysInJsonUsingJsonNodeFields(json, mapper);
			assertEquals(13, keys.size());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	void givenAJsonNode_whenUsingJsonParserMethod_thenWeGetAllFieldNames() {
		List<String> keys;
		try {
			keys = getAllKeysFromJSONUtil.getKeysInJsonUsingJsonParser(json, mapper);
			assertEquals(13, keys.size());

			keys = getAllKeysFromJSONUtil.getKeysInJsonUsingJsonParser(json);
			assertEquals(13, keys.size());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	void givenAJsonNode_whenUsingMaps_thenWeGetAllFieldNames() {
		List<String> keys;
		try {
			keys = getAllKeysFromJSONUtil.getKeysInJsonUsingMaps(json, mapper);
			assertEquals(13, keys.size());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}