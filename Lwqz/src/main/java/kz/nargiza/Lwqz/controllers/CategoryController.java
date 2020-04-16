package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.entities.Category;
import kz.nargiza.Lwqz.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping
    public List<Category> loadAll(){
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable Long id){
        return categoryService.findById(id);
    }
}
