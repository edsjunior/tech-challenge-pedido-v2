package br.com.fiap.pedido.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoDTO {
    private UUID id;
    private String nome;
    private BigDecimal preco;

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
