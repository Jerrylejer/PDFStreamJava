package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ArticleDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import java.util.List;

public interface ArticleServiceInt {
    // CRUD JPA
    public Article saveArticle(ArticleDTOWayIN article);
    public List<Article> getAllArticles() throws EmptyListException;
    public Article getArticleById(Integer id) throws NotFoundException;
    public Article updateArticle(Integer id, ArticleDTOWayIN article) throws NotFoundException;
    public void deleteArticle(Integer id) throws NotFoundException;
}
