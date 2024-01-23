package com.jeromerichard.pdfstream.Service.Implementations;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ArticleDTOWayIN;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Repository.ArticleRepository;
import com.jeromerichard.pdfstream.Service.Interfaces.ArticleServiceInt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ArticleService implements ArticleServiceInt {
    @Autowired
    private ArticleRepository repository;

    @Override
    public Article saveArticle(ArticleDTOWayIN article) {
        Article articleToSave = new Article();
        articleToSave.setTitle(article.getTitle());
        articleToSave.setOrder(article.getOrder());
        articleToSave.setDescription(article.getDescription());
        articleToSave.setCreatedAt(new Date());
        log.info("Nouvel article de Charte ajouté");
        repository.save(articleToSave);
        return articleToSave;
    }

    @Override
    public List<Article> getAllArticles() throws EmptyListException {
        List<Article> articlesList = repository.findAll();
        if(articlesList ==null) {
            throw new EmptyListException("Aucune liste ne correspond à votre demande");
        }
        return articlesList;
    }

    @Override
    public Article getArticleById(Integer id) throws NotFoundException {
        Article article = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cet article n'existe pas, reformulez votre demande")
        );
        log.info("l'article id " + id + " est affiché.");
        return article;
    }

    @Override
    public Article updateArticle(Integer id, ArticleDTOWayIN article) throws NotFoundException {
        Article articleToUpdate = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cet article n'existe pas, reformulez votre demande.")
        );
        articleToUpdate.setTitle(article.getTitle());
        articleToUpdate.setDescription(article.getDescription());
        articleToUpdate.setOrder(article.getOrder());
        articleToUpdate.setUpdateAt(new Date());
        log.info("L'article id" + id + "à correctement été modifié");
        repository.save(articleToUpdate);
        return articleToUpdate;
    }

    @Override
    public void deleteArticle(Integer id) throws NotFoundException {
        Article articleToDelete = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Cet article de la charte n'existe pas, reformulez votre demande")
        );
        log.info("L'article " + articleToDelete.getTitle() + "à correctement été supprimée");
        repository.deleteById(id);
    }
}
