package com.isaacggr.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class TodolistApplication {

	public static void main(String[] args) {
		// Configurações de DNS e rede
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("jsse.enableSNIExtension", "false");
		System.setProperty("sun.net.inetaddr.ttl", "0");
		System.setProperty("sun.net.inetaddr.negative.ttl", "0");
		System.setProperty("networkaddress.cache.ttl", "0");
		System.setProperty("networkaddress.cache.negative.ttl", "0");
		
		// Configurações de DNS para MongoDB Atlas (crítico para o Render)
		System.setProperty("javax.net.ssl.trustStore", System.getProperty("java.home") + "/lib/security/cacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
		
		SpringApplication.run(TodolistApplication.class, args);
	}
	
	// Configuração CORS adicional para garantir acesso do GitHub Pages
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
