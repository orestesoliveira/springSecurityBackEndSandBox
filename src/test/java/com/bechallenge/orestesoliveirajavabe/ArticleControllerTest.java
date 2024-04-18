package com.bechallenge.orestesoliveirajavabe;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bechallenge.orestesoliveirajavabe.controller.ArticleController;
import com.bechallenge.orestesoliveirajavabe.repo.Article;
import com.bechallenge.orestesoliveirajavabe.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
        //this.mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()) // Enable Spring Security for the mockMvc
                .build();
    }

    @Test
    public void testGetArticles() {
        // Mock the behavior of the service method
        when(articleService.getPaginatedArticles(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Perform the request to the controller
        Page<Article> result = articleController.getArticles(0, 5);

        // Verify the result
        assertNotNull(result);
        assertEquals(0, result.getContent().size());
    }

    @Test
    public void testGetArticlesWithPagination() throws Exception {
        // Mock the behavior of the service method with pagination
        when(articleService.getPaginatedArticles(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Perform the request to the controller with pagination parameters and verify the response
        mockMvc.perform(get("/articles?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isEmpty());
    }
}
