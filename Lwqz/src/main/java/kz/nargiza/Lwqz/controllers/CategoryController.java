package kz.nargiza.Lwqz.controllers;

import kz.nargiza.Lwqz.models.dtos.CategoryDto;
import kz.nargiza.Lwqz.models.entities.Category;
import kz.nargiza.Lwqz.models.mappers.CategoryMapper;
import kz.nargiza.Lwqz.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController extends BaseController {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<?> loadAll(){
        return buildResponse(categoryMapper.toDtoList(categoryService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return buildResponse(categoryMapper.toDto(categoryService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody @Validated CategoryDto categoryDto){
        Category category = categoryMapper.toEntity(categoryDto);
        category = categoryService.addCategory(category.getName());
        return buildResponse(categoryMapper.toDto(category), HttpStatus.OK);
    }
}
