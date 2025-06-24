package com.infsis.socialpagebackend.sections.repositories;

import com.infsis.socialpagebackend.sections.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("SELECT a FROM Article a WHERE a.uuid = ?1")
    Article findOneByUuid(String uuid);
}
