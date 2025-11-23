package com.paintballProject.paintballBack.news.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paintballProject.paintballBack.common.constants.globalConstants;
import com.paintballProject.paintballBack.news.dto.NewsRequest;
import com.paintballProject.paintballBack.news.dto.NewsResponse;
import com.paintballProject.paintballBack.news.service.NewsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(globalConstants.ENDPOINT_NEWS)
@RequiredArgsConstructor
@CrossOrigin(origins = globalConstants.FRONTEND_URL, allowCredentials = "true")
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/allNews")
    public List<NewsResponse> getAllNews() {
        List<NewsResponse> news = newsService.getAllNews();
        if (news == null || news.isEmpty()) {
            return List.of();
        }
        return news;
    }

        @GetMapping("/getNew/{id}")
    public List<NewsResponse> getNew() {
        List<NewsResponse> news = newsService.getAllNews();
        if (news == null || news.isEmpty()) {
            return List.of();
        }
        return news;
    }

        @PutMapping(value = "/updateNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateNew(@ModelAttribute NewsRequest request) throws IOException {
        try{
        NewsResponse resp = newsService.updateNew(request);
        return ResponseEntity.ok(resp);

        } catch (DataAccessException ex) {
        // Errores típicos de BD (Spring Data / JDBC)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        // Otros errores genéricos
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Error al procesar la petición: " + ex.getMessage());
    }        
    }

    @PostMapping(value = "/addNew", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addNew(@ModelAttribute NewsRequest request) throws IOException {
        try{
        NewsResponse resp = newsService.addNew(request);
        return ResponseEntity.ok(resp);
        } catch (DataAccessException ex) {
        // Errores típicos de BD (Spring Data / JDBC)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Error de base de datos: " + ex.getMessage());
    } catch (Exception ex) {
        // Otros errores genéricos
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Error al procesar la petición: " + ex.getMessage());
    }        
    }

    @DeleteMapping(value = "/deleteNew/{id}")
    public ResponseEntity<?> deleteNew(@PathVariable Long id) {
        try {
            boolean deleted = newsService.deleteNew(id);
            if (deleted) {
                return ResponseEntity.ok("Noticia eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body("No se encontró la noticia con id: " + id);
            }
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error de base de datos: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Error al procesar la petición: " + ex.getMessage());
        }
    }
}
