package kz.nargiza.Lwqz.services.impl;

import kz.nargiza.Lwqz.models.entities.Category;
import kz.nargiza.Lwqz.models.errors.ErrorCode;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;
import kz.nargiza.Lwqz.repositories.CategoryRepository;
import kz.nargiza.Lwqz.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) throws SystemServiceException {
        return this.categoryRepository.findCategoryById(id).orElseThrow(
                () -> SystemServiceException.builder()
                        .message("No category with such id")
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .build()
        );
    }

    @Override
    public Category findByName(String name) throws SystemServiceException {
        return this.categoryRepository.findCategoryByName(name).orElseThrow(
                () -> SystemServiceException.builder()
                        .message("No category with such name")
                        .errorCode(ErrorCode.ENTITY_NOT_FOUND)
                        .build()
        );
    }

    @Override
    public Category addCategory(String name) throws SystemServiceException {
        if(categoryRepository.findCategoryByName(name).isPresent()){
            throw SystemServiceException.builder()
                    .errorCode(ErrorCode.CATEGORY_ALREADY_EXISTS)
                    .message("Such category already exists")
                    .build();
        }
        return this.categoryRepository.save(Category.builder().name(name).build());
    }
}
