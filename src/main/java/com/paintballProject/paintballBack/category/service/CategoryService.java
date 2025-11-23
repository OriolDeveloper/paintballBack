package com.paintballProject.paintballBack.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paintballProject.paintballBack.category.dto.CategoryDto;
import com.paintballProject.paintballBack.category.model.Category;
import com.paintballProject.paintballBack.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

     private CategoryDto categoryDto(Category cat) {
        CategoryDto dto = new CategoryDto();
        dto.setId(cat.getId());
        dto.setName(cat.getName()); 
        return dto;
    }

        
    public List<CategoryDto> getAllCategorys()  {
        return categoryRepository.findAll().stream()
                .map(this::categoryDto)
                .toList();
    }

}
