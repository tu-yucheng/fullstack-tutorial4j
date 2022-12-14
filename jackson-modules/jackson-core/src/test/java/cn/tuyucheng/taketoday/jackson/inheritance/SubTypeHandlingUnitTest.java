package cn.tuyucheng.taketoday.jackson.inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubTypeHandlingUnitTest {

	@Test
	void givenSubTypes_whenConvertingObjects_thenDataValuesArePreserved() {
		ObjectMapper mapper = new ObjectMapper();

		SubTypeConversionStructure.Car car = new SubTypeConversionStructure.Car("Mercedes-Benz", "S500", 5, 250.0);
		SubTypeConversionStructure.Truck truck = mapper.convertValue(car, SubTypeConversionStructure.Truck.class);

		assertEquals("Mercedes-Benz", truck.getMake());
		assertEquals("S500", truck.getModel());
	}

	@Test
	void givenSubType_whenNotUsingNoArgsConstructors_thenSucceed() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
				.allowIfSubType("cn.tuyucheng.taketoday.jackson.inheritance")
				.allowIfSubType("java.util.ArrayList")
				.build();
		mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

		SubTypeConstructorStructure.Car car = new SubTypeConstructorStructure.Car("Mercedes-Benz", "S500", 5, 250.0);
		SubTypeConstructorStructure.Truck truck = new SubTypeConstructorStructure.Truck("Isuzu", "NQR", 7500.0);

		List<SubTypeConstructorStructure.Vehicle> vehicles = new ArrayList<>();
		vehicles.add(car);
		vehicles.add(truck);

		SubTypeConstructorStructure.Fleet serializedFleet = new SubTypeConstructorStructure.Fleet();
		serializedFleet.setVehicles(vehicles);

		String jsonDataString = mapper.writeValueAsString(serializedFleet);
		mapper.readValue(jsonDataString, SubTypeConstructorStructure.Fleet.class);

		SubTypeConstructorStructure.Fleet deserializedFleet = mapper.readValue(jsonDataString, SubTypeConstructorStructure.Fleet.class);

		assertThat(deserializedFleet.getVehicles().get(0), instanceOf(SubTypeConstructorStructure.Car.class));
		assertThat(deserializedFleet.getVehicles().get(1), instanceOf(SubTypeConstructorStructure.Truck.class));
	}
}