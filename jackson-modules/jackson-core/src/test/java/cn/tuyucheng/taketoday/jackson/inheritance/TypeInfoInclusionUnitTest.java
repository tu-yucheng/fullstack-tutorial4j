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

class TypeInfoInclusionUnitTest {

	@Test
	void givenTypeInfo_whenAnnotatingGlobally_thenTypesAreCorrectlyRecovered() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
				.allowIfSubType("cn.tuyucheng.taketoday.jackson.inheritance")
				.allowIfSubType("java.util.ArrayList")
				.build();
		mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

		TypeInfoStructure.Car car = new TypeInfoStructure.Car("Mercedes-Benz", "S500", 5, 250.0);
		TypeInfoStructure.Truck truck = new TypeInfoStructure.Truck("Isuzu", "NQR", 7500.0);

		List<TypeInfoStructure.Vehicle> vehicles = new ArrayList<>();
		vehicles.add(car);
		vehicles.add(truck);

		TypeInfoStructure.Fleet serializedFleet = new TypeInfoStructure.Fleet();
		serializedFleet.setVehicles(vehicles);

		String jsonDataString = mapper.writeValueAsString(serializedFleet);
		TypeInfoStructure.Fleet deserializedFleet = mapper.readValue(jsonDataString, TypeInfoStructure.Fleet.class);

		assertThat(deserializedFleet.getVehicles().get(0), instanceOf(TypeInfoStructure.Car.class));
		assertThat(deserializedFleet.getVehicles().get(1), instanceOf(TypeInfoStructure.Truck.class));
	}

	@Test
	void givenTypeInfo_whenAnnotatingPerClass_thenTypesAreCorrectlyRecovered() throws IOException {
		ObjectMapper mapper = new ObjectMapper();

		TypeInfoAnnotatedStructure.Car car = new TypeInfoAnnotatedStructure.Car("Mercedes-Benz", "S500", 5, 250.0);
		TypeInfoAnnotatedStructure.Truck truck = new TypeInfoAnnotatedStructure.Truck("Isuzu", "NQR", 7500.0);

		List<TypeInfoAnnotatedStructure.Vehicle> vehicles = new ArrayList<>();
		vehicles.add(car);
		vehicles.add(truck);

		TypeInfoAnnotatedStructure.Fleet serializedFleet = new TypeInfoAnnotatedStructure.Fleet();
		serializedFleet.setVehicles(vehicles);

		String jsonDataString = mapper.writeValueAsString(serializedFleet);
		TypeInfoAnnotatedStructure.Fleet deserializedFleet = mapper.readValue(jsonDataString, TypeInfoAnnotatedStructure.Fleet.class);

		assertThat(deserializedFleet.getVehicles().get(0), instanceOf(TypeInfoAnnotatedStructure.Car.class));
		assertThat(deserializedFleet.getVehicles().get(1), instanceOf(TypeInfoAnnotatedStructure.Truck.class));
	}
}