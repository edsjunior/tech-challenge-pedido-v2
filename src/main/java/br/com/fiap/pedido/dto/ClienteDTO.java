package br.com.fiap.pedido.dto;

import java.util.UUID;

public class ClienteDTO {
    private UUID id;
    private String nome;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
