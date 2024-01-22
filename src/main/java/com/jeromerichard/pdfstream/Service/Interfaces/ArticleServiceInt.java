package com.jeromerichard.pdfstream.Service.Interfaces;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ArticleDTODown;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;

import javax.lang.model.type.IntersectionType;
import java.util.List;
import java.util.Set;

public interface ArticleServiceInt {
    // CRUD JPA
    public Article saveArticle(ArticleDTODown article);
    public List<Article> getAllArticles() throws EmptyListException;
    public Article getArticleById(Integer id) throws NotFoundException;
    public Article updateArticle(Integer id, ArticleDTODown article) throws NotFoundException;
    public void deleteArticle(Integer id) throws NotFoundException;
}
