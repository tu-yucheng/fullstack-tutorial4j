package cn.tuyucheng.taketoday.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class MockitoAnnotationsInjectIntoSpyUnitTest {

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
		spyDic = Mockito.spy(new MyDictionary(wordMap));
	}

	@Mock
	private Map<String, String> wordMap;

	@InjectMocks
	private MyDictionary dic = new MyDictionary();

	private MyDictionary spyDic;

	@Test
	void whenUseInjectMocksAnnotation_thenCorrect() {
		Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

		assertEquals("aMeaning", spyDic.getMeaning("aWord"));
	}
}