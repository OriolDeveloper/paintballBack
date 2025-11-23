package com.paintballProject.paintballBack.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paintballProject.paintballBack.news.model.News;

public interface NewsRepository  extends JpaRepository<News, Long>  {
}
