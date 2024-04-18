package com.bechallenge.orestesoliveirajavabe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitleContainingIgnoreCase(String keyword);

    List<Article> findByAuthor(String author);

    List<Article> findByTags(String tags);

    List<Article> findByTitle(String title);

    List<Article> findByAuthorAndTags(String author, String tags);

    List<Article> findByAuthorAndTitle(String author, String title);

    List<Article> findByTagsAndTitle(String tags, String title);

    List<Article> findByAuthorAndTagsAndTitle(String author, String tags, String title);
}