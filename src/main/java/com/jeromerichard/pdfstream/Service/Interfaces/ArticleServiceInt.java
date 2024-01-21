package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ArticleDTODown;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.Set;

public interface ArticleServiceInt {
    // CRUD JPA
    public Article saveArticle(ArticleDTODown article);
    public Set<Article> getAllArticles() throws EmptyListException;
    public Article getArticleById(Long id) throws NotFoundException;
    public Article updateArticle(Long id, ArticleDTODown article) throws NotFoundException;
    public void deleteArticle(Long id) throws NotFoundException;
}
