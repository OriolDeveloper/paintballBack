package com.paintballProject.paintballBack.category.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paintballProject.paintballBack.category.dto.CategoryDto;
import com.paintballProject.paintballBack.category.service.CategoryService;
import com.paintballProject.paintballBack.common.constants.globalConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(globalConstants.ENDPOINT_CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

  @GetMapping("/allCategory")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategorys();
    }
}