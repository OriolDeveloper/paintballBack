package com.paintballProject.paintballBack.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paintballProject.paintballBack.authentication.dto.LoginRequest;
import com.paintballProject.paintballBack.users.dto.UserDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.IOException;
import java.util.Collections;

public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonUsernamePasswordAuthenticationFilter(AuthenticationManager authManager) {
        setAuthenticationManager(authManager);
        setFilterProcessesUrl("/api/authentication/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequest creds = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword());
            return this.getAuthenticationManager().authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo request JSON", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {

        SecurityContextHolder.getContext().setAuthentication(authResult);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        Object principal = authResult.getPrincipal();

        if (principal instanceof CustomUserDetails user) {
            // Devuelve el usuario completo
            UserDto userDto = new UserDto(user.getId(), user.getEmail(),
                    user.getUsername(), user.getRoleId());
            objectMapper.writeValue(response.getWriter(), userDto);
        } else {
            objectMapper.writeValue(response.getWriter(), Collections.singletonMap("message", "Login correcto"));
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(),
                Collections.singletonMap("message", "Email o contraseña incorrectos"));
    }
public record UserDtoResponse(Long id, String email, String username, Integer role_id) {}
}


