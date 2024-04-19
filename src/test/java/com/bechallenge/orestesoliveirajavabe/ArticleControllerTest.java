package com.bechallenge.orestesoliveirajavabe;

import static org.mockito.Mockito.*;
import com.bechallenge.orestesoliveirajavabe.controller.ArticleController;
import com.bechallenge.orestesoliveirajavabe.repo.Article;
import com.bechallenge.orestesoliveirajavabe.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {


    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleService articleService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();

    }

    @Test
    public void testGetArticles() {
        when(articleService.getPaginatedArticles(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<Article> result = articleController.getArticles(0, 5);

        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    }

    @Test
    public void testGetArticleById() {
        Article expectedArticle = new Article("Title", "Content");
        when(articleService.getArticleById(1L)).thenReturn(expectedArticle);

        Article actualArticle = articleController.getArticleById(1L);

        assertEquals(expectedArticle, actualArticle);
    }

    @Test
    public void testCreateArticle() {
        Article articleToCreate = new Article("Title", "Content");

        articleController.createArticle(articleToCreate);

        verify(articleService, times(1)).createArticle(articleToCreate);
    }

    @Test
    public void testUpdateArticle() {
        Article articleToUpdate = new Article("Title", "Updated Content");

        articleController.updateArticle(1L, articleToUpdate);

        verify(articleService, times(1)).updateArticle(1L, articleToUpdate);
    }

    @Test
    public void testDeleteArticle() {
        articleController.deleteArticle(1L);

        verify(articleService, times(1)).deleteArticle(1L);
    }
}
