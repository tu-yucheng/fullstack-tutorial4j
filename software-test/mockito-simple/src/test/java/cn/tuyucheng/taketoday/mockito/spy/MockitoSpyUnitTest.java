package cn.tuyucheng.taketoday.mockito.spy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockitoSpyUnitTest {

    @Test
    void whenSpyingOnList_thenCorrect() {
        final List<String> list = new ArrayList<>();
        final List<String> spyList = Mockito.spy(list);

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertThat(spyList).hasSize(2);
    }

    @Spy
    private List<String> aSpyList = new ArrayList<>();

    @Test
    void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        aSpyList.add("one");
        aSpyList.add("two");

        Mockito.verify(aSpyList).add("one");
        Mockito.verify(aSpyList).add("two");

        assertThat(aSpyList).hasSize(2);
    }

    @Test
    void whenStubASpy_thenStubbed() {
        final List<String> list = new ArrayList<>();
        final List<String> spyList = Mockito.spy(list);

        assertEquals(0, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertThat(spyList).hasSize(100);
    }

    @Test
    void whenCreateMock_thenCreated() {
        final List<String> mockedList = Mockito.mock(ArrayList.class);

        mockedList.add("one");
        Mockito.verify(mockedList).add("one");

        assertThat(mockedList).hasSize(0);
    }

    @Test
    void whenCreateSpy_thenCreate() {
        final List<String> spyList = Mockito.spy(new ArrayList<>());

        spyList.add("one");
        Mockito.verify(spyList).add("one");

        assertThat(spyList).hasSize(1);
    }
}