package com.bechallenge.orestesoliveirajavabe.controller;

import com.bechallenge.orestesoliveirajavabe.repo.Article;
import com.bechallenge.orestesoliveirajavabe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

@Autowired
    private ArticleService articleService;

    @GetMapping
    public Page<Article> getArticles(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return articleService.getPaginatedArticles(pageRequest);
    }

    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam String keyword) {
        return articleService.searchArticles(keyword);
    }

    @GetMapping("/filter")
    public List<Article> filterArticles(@RequestParam(required = false) String author,
                                        @RequestParam(required = false) String tags,
                                        @RequestParam(required = false) String title) {
        return articleService.filterArticles(author, tags, title);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

}
