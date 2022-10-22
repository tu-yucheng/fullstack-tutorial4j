package cn.tuyucheng.taketoday.joinpoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticleServiceIntegrationTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void shouldGetNotEmptyArticleList() {
        List<String> articleList = articleService.getArticleList();

        assertFalse(articleList.isEmpty());
    }

    @Test
    void shouldGetNotEmptyArticleListWithStartsWithFilter() {
        List<String> articleList = articleService.getArticleList("Article");

        assertFalse(articleList.isEmpty());
    }

    @Test/*(expected = IllegalArgumentException.class)*/
    void shouldThrowExceptionIfStartsWithFilterIsBlank() {
        articleService.getArticleList(" ");
    }
}