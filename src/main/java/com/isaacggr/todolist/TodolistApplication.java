package com.isaacggr.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
		
		SpringApplication.run(TodolistApplication.class, args);
	}

}
