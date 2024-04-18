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
        // Mock the behavior of the repository method
        when(articleRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Perform the service method invocation
        Page<Article> result = articleService.getPaginatedArticles(PageRequest.of(0, 5));

        // Verify the result
        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    }

    // Additional service tests for other methods can be added here
}
