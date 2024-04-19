package com.bechallenge.orestesoliveirajavabe;

import com.bechallenge.orestesoliveirajavabe.repo.Article;
import com.bechallenge.orestesoliveirajavabe.repo.ArticleRepository;
import com.bechallenge.orestesoliveirajavabe.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void testGetPaginatedArticles() {
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<Article> result = articleService.getPaginatedArticles(PageRequest.of(0, 5));
        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    }



    @Test
    public void testDeleteArticle() {
        doNothing().when(articleRepository).deleteById(any(Long.class));
        articleService.deleteArticle(any(Long.class));
        verify(articleRepository).deleteById(any(Long.class));
    }

    @Test
    public void testGetArticleById() {
        Article article = new Article();
        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(article));
        Article result = articleService.getArticleById(any(Long.class));
        assertEquals(article, result);
    }

    @Test
    public void testCreateArticle() {
        Article article = new Article();
        articleService.createArticle(article);
        verify(articleRepository).save(article);
    }

    @Test
    public void testUpdateArticle() {
        Article existingArticle = new Article();
        existingArticle.setId(1L);
        existingArticle.setTitle("Old Title");
        existingArticle.setContent("Old Content");

        Article updatedArticle = new Article();
        updatedArticle.setId(1L);
        updatedArticle.setTitle("New Title");
        updatedArticle.setContent("New Content");

        when(articleRepository.findById(any(Long.class))).thenReturn(Optional.of(existingArticle));
        articleService.updateArticle(1L, updatedArticle);
        verify(articleRepository).save(existingArticle);
        assertEquals("New Title", existingArticle.getTitle());
        assertEquals("New Content", existingArticle.getContent());
    }

}
