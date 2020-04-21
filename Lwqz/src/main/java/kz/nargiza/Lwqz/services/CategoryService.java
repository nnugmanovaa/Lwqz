package kz.nargiza.Lwqz.services;

import kz.nargiza.Lwqz.models.entities.Category;
import kz.nargiza.Lwqz.models.exceptions.SystemServiceException;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id) throws SystemServiceException;

    Category findByName(String name) throws SystemServiceException;

    Category addCategory(String name) throws SystemServiceException;
}
