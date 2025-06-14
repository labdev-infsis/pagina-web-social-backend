package com.infsis.socialpagebackend.sections.controllers;

import com.infsis.socialpagebackend.sections.dtos.ArticleDTO;
import com.infsis.socialpagebackend.sections.services.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{articleUuid}")
    public ArticleDTO get(@PathVariable String articleUuid) {
        return articleService.getArticle(articleUuid);
    }

    @GetMapping
    public List<ArticleDTO> getAll() {
        return articleService.getAllArticles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO create(@Valid @RequestBody ArticleDTO articleDTO) {
        return articleService.saveArticle(articleDTO);
    }

    @DeleteMapping("/{articleUuid}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDTO delete(@PathVariable String articleUuid) {
        return articleService.deleteArticle(articleUuid);
    }

    @PutMapping("/{articleUuid}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDTO update(@PathVariable String articleUuid, @Valid @RequestBody ArticleDTO articleDTO) {
        return articleService.updateArticle(articleUuid, articleDTO);
    }

}
