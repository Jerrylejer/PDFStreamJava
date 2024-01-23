package com.jeromerichard.pdfstream.Controller;

import com.jeromerichard.pdfstream.Dto.DtoToEntity.CategoryDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.CollectionDTOWayIN;
import com.jeromerichard.pdfstream.Dto.DtoToEntity.SearchDTOWayIN;
import com.jeromerichard.pdfstream.Dto.EntityToDto.CategoryDTO;
import com.jeromerichard.pdfstream.Dto.EntityToDto.CollectionDTO;
import com.jeromerichard.pdfstream.Dto.EntityToDto.SearchDTO;
import com.jeromerichard.pdfstream.Entity.Category;
import com.jeromerichard.pdfstream.Entity.Collection;
import com.jeromerichard.pdfstream.Entity.Search;
import com.jeromerichard.pdfstream.Exception.EmptyListException;
import com.jeromerichard.pdfstream.Exception.NotFoundException;
import com.jeromerichard.pdfstream.Service.Implementations.CategoryService;
import com.jeromerichard.pdfstream.Service.Implementations.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/new")
    @ResponseBody
    // les datas seront placées dans le corps de la réponse HTTP sans être interprétées comme une vue HTML.
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTOWayIN clientDatas) {
        // Conversion des datas front en DTOWayIN
        CategoryDTOWayIN categoryDTOWayIN = modelMapper.map(clientDatas, CategoryDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Category category = service.saveCategory(categoryDTOWayIN);
        // Conversion sens Entité à DTO
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTOWayIN clientDatas) throws NotFoundException {
        // Conversion des datas front en DTOWayIN
        CategoryDTOWayIN categoryDTOWayIN = modelMapper.map(clientDatas, CategoryDTOWayIN.class);
        // Conversion sens DTOWayIN à Entité
        Category category = service.updateCategory(id, categoryDTOWayIN);
        // Conversion sens Entité à DTO
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) throws NotFoundException {
        service.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getCategoriesList() throws EmptyListException {
        // Le flux de datas retourné par getAllAlerts() est traité pour que chaque data soit convertie en class AlertDTO et retournée sous forme de List
        List<CategoryDTO> categoryDTOList = service.getAllCategories().stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer id) throws NotFoundException {
        Category category = service.getCategoryById(id);
        // Conversion sens Entité à DTO
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }
}
