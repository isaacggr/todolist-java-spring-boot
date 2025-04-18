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

/**
 * Filtro CORS extremamente simples que permite todas as origens sem credenciais
 * Esse é o último recurso quando outras configurações CORS falham
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    // Lista de domínios permitidos
    private final List<String> allowedOrigins = Arrays.asList(
        "https://todolist-frontend-dt9a.onrender.com",
        "https://todolist-frontend.onrender.com",
        "https://isaacggr.github.io",
        "http://localhost:5500",
        "http://localhost:3000"
    );

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");
        
        // Log para debug
        System.out.println("Requisição recebida de origem: " + origin);
        
        // Se a origem está na lista permitida
        if (origin != null && allowedOrigins.contains(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            System.out.println("CORS permitido para origem específica: " + origin);
        } else {
            // Para outras origens, permitimos tudo em ambiente de desenvolvimento
            response.setHeader("Access-Control-Allow-Origin", "*");
            System.out.println("CORS permitido para todas as origens");
        }
        
        // Configurações comuns
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        
        // Para requisições OPTIONS, retorne OK imediatamente
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Não faz nada
    }

    @Override
    public void destroy() {
        // Não faz nada
    }
} 