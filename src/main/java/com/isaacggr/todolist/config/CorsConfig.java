package com.isaacggr.todolist.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements Filter {
    
    // Lista das origens permitidas
    private final List<String> allowedOrigins = Arrays.asList(
        "https://isaacggr.github.io",
        "https://isaacggr.github.io/todolist-frontend", 
        "https://isaacggr.github.io/todolist-frontend/",
        "http://localhost:5500", 
        "http://localhost:8080"
    );
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        
        // Obter a origem da requisição
        String origin = request.getHeader("Origin");
        
        // Log para debug da origem da requisição
        System.out.println("Requisição recebida de origem: " + origin);
        
        // Se a origem está na lista permitida, defina o cabeçalho Allow-Origin especificamente para ela
        if (origin != null && (allowedOrigins.contains(origin) || originStartsWithAllowed(origin))) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Credentials", "true");
            System.out.println("CORS permitido para: " + origin);
        } else {
            // Para outras origens, não permitimos credentials
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "false");
            if (origin != null) {
                System.out.println("CORS sem credenciais para: " + origin);
            }
        }
        
        // Outros cabeçalhos CORS comuns
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
        
        // Tratamento especial para requisições OPTIONS (preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }
    
    // Verifica se a origem começa com alguma das origens permitidas
    private boolean originStartsWithAllowed(String origin) {
        return allowedOrigins.stream().anyMatch(origin::startsWith);
    }
    
    @Override
    public void init(FilterConfig filterConfig) {
        // Nada a fazer aqui
    }
    
    @Override
    public void destroy() {
        // Nada a fazer aqui
    }
} 