package com.paintballProject.paintballBack.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paintballProject.paintballBack.category.model.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long>  {

}
