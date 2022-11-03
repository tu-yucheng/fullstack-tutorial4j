package cn.tuyucheng.taketoday.mockito.fluentapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PizzaServiceUnitTest {

    @Mock
    private Pizza expectedPizza;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private Pizza.PizzaBuilder anotherBuilder;

    @Captor
    private ArgumentCaptor<String> stringCaptor;
    @Captor
    private ArgumentCaptor<Pizza.PizzaSize> sizeCaptor;

    @Test
    void givenTraditionalMocking_whenServiceInvoked_thenPizzaIsBuilt() {
        Pizza.PizzaBuilder nameBuilder = Mockito.mock(Pizza.PizzaBuilder.class);
        Pizza.PizzaBuilder sizeBuilder = Mockito.mock(Pizza.PizzaBuilder.class);
        Pizza.PizzaBuilder firstToppingBuilder = Mockito.mock(Pizza.PizzaBuilder.class);
        Pizza.PizzaBuilder secondToppingBuilder = Mockito.mock(Pizza.PizzaBuilder.class);
        Pizza.PizzaBuilder stuffedBuilder = Mockito.mock(Pizza.PizzaBuilder.class);
        Pizza.PizzaBuilder willCollectBuilder = Mockito.mock(Pizza.PizzaBuilder.class);
        Pizza.PizzaBuilder discountBuilder = Mockito.mock(Pizza.PizzaBuilder.class);

        Pizza.PizzaBuilder builder = Mockito.mock(Pizza.PizzaBuilder.class);
        when(builder.name(anyString())).thenReturn(nameBuilder);
        when(nameBuilder.size(any(Pizza.PizzaSize.class))).thenReturn(sizeBuilder);
        when(sizeBuilder.withExtraTopping(anyString())).thenReturn(firstToppingBuilder);
        when(firstToppingBuilder.withStuffedCrust(anyBoolean())).thenReturn(stuffedBuilder);
        when(stuffedBuilder.withExtraTopping(anyString())).thenReturn(secondToppingBuilder);
        when(secondToppingBuilder.willCollect(anyBoolean())).thenReturn(willCollectBuilder);
        when(willCollectBuilder.applyDiscount(anyInt())).thenReturn(discountBuilder);
        when(discountBuilder.build()).thenReturn(expectedPizza);

        PizzaService service = new PizzaService(builder);
        assertEquals(expectedPizza, service.orderHouseSpecial(), "Expected Pizza");

        verify(builder).name(stringCaptor.capture());
        assertEquals("Special", stringCaptor.getValue(), "Pizza name: ");

        verify(nameBuilder).size(sizeCaptor.capture());
        assertEquals(Pizza.PizzaSize.LARGE, sizeCaptor.getValue(), "Pizza size: ");

    }

    @Test
    void givenDeepStubs_whenServiceInvoked_thenPizzaIsBuilt() {
        Mockito.when(anotherBuilder.name(anyString())
                        .size(any(Pizza.PizzaSize.class))
                        .withExtraTopping(anyString())
                        .withStuffedCrust(anyBoolean())
                        .withExtraTopping(anyString())
                        .willCollect(anyBoolean())
                        .applyDiscount(anyInt())
                        .build())
                .thenReturn(expectedPizza);

        PizzaService service = new PizzaService(anotherBuilder);
        assertEquals(expectedPizza, service.orderHouseSpecial(), "Expected Pizza");
    }
}