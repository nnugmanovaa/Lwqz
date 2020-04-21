package kz.nargiza.Lwqz.models.mappers;

import kz.nargiza.Lwqz.models.dtos.CategoryDto;
import kz.nargiza.Lwqz.models.entities.Category;
import kz.nargiza.Lwqz.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper extends AbstractModelMapper<Category, CategoryDto> {
    private ModelMapper modelMapper;

    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    @Override
    public List<CategoryDto> toDtoList(List<Category> categories) {
        return categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> toEntityList(List<CategoryDto> categoryDtos) {
        return categoryDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
