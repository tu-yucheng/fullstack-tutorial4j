package cn.tuyucheng.taketoday.sealed.classes;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class VehicleUnitTest {

	private static Vehicle car;
	private static Vehicle truck;

	@BeforeAll
	static void createInstances() {
		car = new Car(5, "VZ500DA");
		truck = new Truck(19000, "VZ600TA");
	}

	// @Test
	// void givenCar_whenUsingReflectionAPI_thenSuperClassIsSealed() {
	// 	assertThat(car.getClass().isSealed()).isEqualTo(false);
	// 	assertThat(car.getClass().getSuperclass().isSealed()).isEqualTo(true);
	// 	assertThat(car.getClass().getSuperclass().getPermittedSubclasses())
	// 			.contains(ClassDesc.of(car.getClass().getCanonicalName()));
	// }
	//
	// @Test
	// void givenTruck_whenUsingReflectionAPI_thenSuperClassIsSealed() {
	// 	assertThat(truck.getClass().isSealed()).isEqualTo(false);
	// 	assertThat(truck.getClass().getSuperclass().isSealed()).isEqualTo(true);
	// 	assertThat(truck.getClass().getSuperclass().getPermittedSubclasses())
	// 			.contains(ClassDesc.of(truck.getClass().getCanonicalName()));
	// }

	@Test
	void givenCar_whenGettingPropertyTraditionalWay_thenNumberOfSeatsPropertyIsReturned() {
		assertThat(getPropertyTraditionalWay(car)).isEqualTo(5);
	}

	@Test
	void givenCar_whenGettingPropertyViaPatternMatching_thenNumberOfSeatsPropertyIsReturned() {
		assertThat(getPropertyViaPatternMatching(car)).isEqualTo(5);
	}

	@Test
	void givenTruck_whenGettingPropertyTraditionalWay_thenLoadCapacityIsReturned() {
		assertThat(getPropertyTraditionalWay(truck)).isEqualTo(19000);
	}

	@Test
	void givenTruck_whenGettingPropertyViaPatternMatching_thenLoadCapacityIsReturned() {
		assertThat(getPropertyViaPatternMatching(truck)).isEqualTo(19000);
	}

	private int getPropertyTraditionalWay(Vehicle vehicle) {
		if (vehicle instanceof Car) {
			return ((Car) vehicle).getNumberOfSeats();
		} else if (vehicle instanceof Truck) {
			return ((Truck) vehicle).getLoadCapacity();
		} else {
			throw new RuntimeException("Unknown instance of Vehicle");
		}
	}

	private int getPropertyViaPatternMatching(Vehicle vehicle) {
		if (vehicle instanceof Car car) {
			return car.getNumberOfSeats();
		} else if (vehicle instanceof Truck truck) {
			return truck.getLoadCapacity();
		} else {
			throw new RuntimeException("Unknown instance of Vehicle");
		}
	}
}