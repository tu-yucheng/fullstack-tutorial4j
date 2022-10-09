package cn.tuyucheng.taketoday.mockito.fluentapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PizzaUnitTest {

    @Test
    void givenFluentPizzaApi_whenBuilt_thenPizzaHasCorrectAttributes() {
        Pizza pizza = new Pizza.PizzaBuilder()
                .name("Margherita")
                .size(Pizza.PizzaSize.LARGE)
                .withExtraTopping("Mushroom")
                .withStuffedCrust(false)
                .willCollect(true)
                .applyDiscount(20)
                .build();

        assertEquals("Margherita", pizza.getName(), "Pizza name: ");
        assertEquals(Pizza.PizzaSize.LARGE, pizza.getSize(), "Pizza size: ");
        assertEquals("Mushroom", pizza.getToppings().get(0), "Extra toppings: ");
        assertFalse(pizza.isStuffedCrust(), "Has stuffed crust: ");
        assertTrue(pizza.isCollecting(), "Will collect: ");
        assertEquals(Integer.valueOf(20), pizza.getDiscount(), "Discounts: ");
    }
}