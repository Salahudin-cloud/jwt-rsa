package com.learn.jwtrsa.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.learn.jwtrsa.config.JwtValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.equals("/api/v1/auth/login") || path.equals("/api/v1/auth/register");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);

            try {
                DecodedJWT jwt = JwtValidator.verifyToken(token);
                String username = jwt.getSubject();

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (com.auth0.jwt.exceptions.TokenExpiredException e) {
                writeErrorResponse(response, "JWT token has expired");
                return;
            } catch (Exception e) {
                writeErrorResponse(response, "Invalid JWT token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void writeErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Tetap set status HTTP, tapi tidak ditampilkan di body
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = String.format("{\"error\": \"%s\"}", message);
        response.getWriter().write(json);
    }

}
