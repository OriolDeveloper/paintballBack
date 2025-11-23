package com.paintballProject.paintballBack.news.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paintballProject.paintballBack.category.model.Category;
import com.paintballProject.paintballBack.category.repository.CategoryRepository;
import com.paintballProject.paintballBack.news.dto.NewsDto;
import com.paintballProject.paintballBack.news.dto.NewsRequest;
import com.paintballProject.paintballBack.news.dto.NewsResponse;
import com.paintballProject.paintballBack.news.model.News;
import com.paintballProject.paintballBack.news.repository.NewsRepository;
import com.paintballProject.paintballBack.users.model.User;
import com.paintballProject.paintballBack.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository; // JPA
    private final CategoryRepository categoryRepository; // JPA
    private final UserRepository userRepository; // JPA



public NewsResponse toResponse(News news) {
    NewsResponse response = new NewsResponse();

    NewsDto dto = new NewsDto();
    dto.setId(news.getId());
    dto.setTitle(news.getTitle());
    dto.setDescription(news.getDescription());
    dto.setContent(news.getContent());
    if(news.getImageName() != null)dto.setImageName(news.getImageName());
    if(news.getImage64() != null)dto.setImage64(news.getImage64());
    dto.setIsFeatured(news.getIsFeatured());
    //Podriamos llevarnos el objeto entero al front si obviasemos el .getUsername() es una relación directa de JPA (SI INCREIBLE)
    if(news.getAuthorId() != null)dto.setAuthorId(news.getAuthorId().getUsername());
    dto.setCategoryId(news.getCategoryId().getName());

    response.setNewsDto(dto);
    if(news.getPublishedAt() != null)response.setPublishedAt(news.getPublishedAt());
    response.setUpdatedAt(news.getUpdatedAt());
    return response;
}

    public List<NewsResponse> getAllNews()  {
        return newsRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

public NewsResponse addNew(NewsRequest request) throws IOException {
    News newAdd = new News();

    newAdd.setTitle(request.getTitle());
    newAdd.setDescription(request.getDescription());
    newAdd.setContent(request.getContent());
    newAdd.setImage64(Base64.getEncoder().encodeToString(request.getImageBytes().getBytes()));
    newAdd.setImageName(request.getImageName());
    newAdd.setPublishedAt(LocalDateTime.now());
    newAdd.setUpdatedAt(LocalDateTime.now());
    newAdd.setIsFeatured(request.getIsFeatured());

    User author = userRepository.findById(request.getAuthorId())
            .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

    Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

    newAdd.setAuthorId(author);
    newAdd.setCategoryId(category);

    News newsSaved = newsRepository.save(newAdd);

    return toResponse(newsSaved);
}

public NewsResponse updateNew(NewsRequest request) throws IOException {

        News existingNew = newsRepository.findById(request.getId())
            .orElseThrow(() -> new RuntimeException("News not found"));
        existingNew.setId(request.getId());
        existingNew.setTitle(request.getTitle());
        existingNew.setDescription(request.getDescription());
        existingNew.setContent(request.getContent());
        existingNew.setIsFeatured(request.getIsFeatured());
        existingNew.setImageName(request.getImageName());
        existingNew.setUpdatedAt(LocalDateTime.now());

            Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existingNew.setCategoryId(category);

        if (request.getImageBytes() != null) {
            existingNew.setImage64(Base64.getEncoder().encodeToString(request.getImageBytes().getBytes()));
        }
        News newsSaved = newsRepository.save(existingNew);
        return toResponse(newsSaved);
    }


    public boolean deleteNew(Long id) {
    Optional<News> newDelete = newsRepository.findById(id);
    if (newDelete.isPresent()) {
        newsRepository.delete(newDelete.get());
        return true;
    } else {
        return false;
    }
}

}
