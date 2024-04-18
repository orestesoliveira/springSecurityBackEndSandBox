package com.bechallenge.orestesoliveirajavabe.service;

import com.bechallenge.orestesoliveirajavabe.repo.Article;
import com.bechallenge.orestesoliveirajavabe.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> getPaginatedArticles(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public List<Article> searchArticles(String keyword) {
        return articleRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Article> filterArticles(String author, String tags, String title) {
        if (author != null && tags != null && title != null) {
            return articleRepository.findByAuthorAndTagsAndTitle(author, tags, title);
        } else if (author != null && tags != null) {
            return articleRepository.findByAuthorAndTags(author, tags);
        } else if (author != null && title != null) {
            return articleRepository.findByAuthorAndTitle(author, title);
        } else if (tags != null && title != null) {
            return articleRepository.findByTagsAndTitle(tags, title);
        } else if (author != null) {
            return articleRepository.findByAuthor(author);
        } else if (tags != null) {
            return articleRepository.findByTags(tags);
        } else if (title != null) {
            return articleRepository.findByTitle(title);
        } else {
            return articleRepository.findAll();
        }
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
