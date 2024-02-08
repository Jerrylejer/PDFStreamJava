package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.ArticleDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.CategoryDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.ArticleDTO;
import com.jeromerichard.pdfstream.Dto.EntityToDto.CategoryDTO;
import com.jeromerichard.pdfstream.Entity.Article;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.ArticleService;
import com.jeromerichard.pdfstream.Service.Implementations.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<ArticleDTO> saveArticle(@RequestBody ArticleDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        ArticleDTOWayIN articleDTOWayIN = modelMapper.map(clientDatas, ArticleDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Article article = service.saveArticle(articleDTOWayIN);
        // Conversion sens Entité à DTO
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return new ResponseEntity<ArticleDTO>(articleDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Integer id, @RequestBody ArticleDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        ArticleDTOWayIN articleDTOWayIN = modelMapper.map(clientDatas, ArticleDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Article article = service.updateArticle(id, articleDTOWayIN);
        // Conversion sens Entité à DTO
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return new ResponseEntity<ArticleDTO>(articleDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteArticle(@PathVariable Integer id) throws NotFoundException {
        service.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ArticleDTO>> getArticlesList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<ArticleDTO> articleDTOList = service.getAllArticles().stream().map(article -> modelMapper.map(article, ArticleDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(articleDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleDTO> getArticle(@PathVariable Integer id) throws NotFoundException {
        Article article = service.getArticleById(id);
        // Conversion sens Entité à DTO
        ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);
        return new ResponseEntity<ArticleDTO>(articleDTO, HttpStatus.OK);
    }
}
