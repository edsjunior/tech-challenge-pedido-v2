package br.com.fiap.pedido.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class PagamentoDTO {
    private UUID id;
    private BigDecimal valor;
    private String metodo;

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
