package br.com.fiap.pedido.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    // Configuração para RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Configuração para WebClient
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
