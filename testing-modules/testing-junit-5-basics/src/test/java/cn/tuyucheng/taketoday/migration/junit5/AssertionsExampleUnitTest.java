package cn.tuyucheng.taketoday.migration.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsExampleUnitTest {

    @Test
    @Disabled
    void shouldFailBecauseTheNumbersAreNotEqual() {
        assertEquals(2, 3, "Numbers are not equal!");
    }

    @Test
    @Disabled
    void shouldFailBecauseItsNotTrue_overloading() {
        assertTrue(false, "It's not true!");
    }

    @Test
    void shouldAssertAllTheGroup() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        assertAll("List is not incremental",
                () -> assertEquals(list.get(0).intValue(), 1),
                () -> assertEquals(list.get(1).intValue(), 2),
                () -> assertEquals(list.get(2).intValue(), 3)
        );
    }
}