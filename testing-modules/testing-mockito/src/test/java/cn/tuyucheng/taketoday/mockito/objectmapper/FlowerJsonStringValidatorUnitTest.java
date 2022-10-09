package cn.tuyucheng.taketoday.mockito.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlowerJsonStringValidatorUnitTest {

    @Mock
    private ObjectMapper objectMapper;

    private FlowerJsonStringValidator flowerJsonStringValidator;

    @BeforeEach
    void setUp() {
        flowerJsonStringValidator = new FlowerJsonStringValidator(objectMapper);
    }

    @Test
    void whenCallingHasPetalsWithPetals_thenReturnsTrue() throws JsonProcessingException {
        Flower rose = new Flower("testFlower", 100);

        when(objectMapper.readValue(anyString(), eq(Flower.class))).thenReturn(rose);

        assertTrue(flowerJsonStringValidator.flowerHasPetals("this can be a very long json flower"));

        verify(objectMapper, times(1)).readValue(anyString(), eq(Flower.class));
    }

    @Test
    void whenCallingHasPetalsWithZeroPetal_thenReturnsFalse() throws JsonProcessingException {
        Flower rose = new Flower("testFlowerWithoutPetal", 0);

        when(objectMapper.readValue(anyString(), eq(Flower.class))).thenReturn(rose);

        assertFalse(flowerJsonStringValidator.flowerHasPetals("this can be a very long json flower"));

        verify(objectMapper, times(1)).readValue(anyString(), eq(Flower.class));
    }
}