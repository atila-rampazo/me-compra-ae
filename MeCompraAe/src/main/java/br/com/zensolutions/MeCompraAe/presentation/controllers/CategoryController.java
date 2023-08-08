package br.com.zensolutions.MeCompraAe.presentation.controllers;

import br.com.zensolutions.MeCompraAe.domains.entities.categories.Category;
import br.com.zensolutions.MeCompraAe.domains.records.category.CategoryRecord;
import br.com.zensolutions.MeCompraAe.domains.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private Logger logger = Logger.getLogger(CategoryController.class.getName());
    @Autowired
    CategoryService service;
    @GetMapping()
    public List<Category> getAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Category findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public Category store(@RequestBody Category category){
        return service.create(category);
    }
    @PutMapping(value = "/{id}")
    public Category update(@RequestBody Category category,@PathVariable(value = "id") Long id){
        return service.update(id,category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
