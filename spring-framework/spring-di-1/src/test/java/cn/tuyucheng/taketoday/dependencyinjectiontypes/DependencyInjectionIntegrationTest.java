package cn.tuyucheng.taketoday.dependencyinjectiontypes;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DependencyInjectionIntegrationTest {

	@Test
	void givenAutowiredAnnotation_WhenSetOnSetter_ThenDependencyValid() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dependencyinjectiontypes-context.xml");
		ArticleWithSetterInjection article = (ArticleWithSetterInjection) context.getBean("articleWithSetterInjectionBean");

		String originalText = "This is a text !";
		String formattedArticle = article.format(originalText);

		assertEquals(originalText.toUpperCase(), formattedArticle);
	}

	@Test
	void givenAutowiredAnnotation_WhenSetOnConstructor_ThenDependencyValid() {
		ApplicationContext context = new ClassPathXmlApplicationContext("dependencyinjectiontypes-context.xml");
		ArticleWithConstructorInjection article = (ArticleWithConstructorInjection) context.getBean("articleWithConstructorInjectionBean");

		String originalText = "This is a text !";
		String formattedArticle = article.format(originalText);

		assertEquals(originalText.toUpperCase(), formattedArticle);
	}
}