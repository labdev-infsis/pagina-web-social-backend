package com.infsis.socialpagebackend.sections.repositories;

import com.infsis.socialpagebackend.sections.models.ArticleMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMediaRepository extends JpaRepository<ArticleMedia, Integer> {

    @Query("SELECT am FROM ArticleMedia am WHERE am.uuid = ?1")
    ArticleMedia findOneByUuid(String uuid);
}
