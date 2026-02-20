package com.example.trabalho03.Modelo;

import java.util.Objects;

public class Produto {
    private int id;
    private String nome;
    private String preco;

    public Produto(){}
    public Produto(int id, String nome, String preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
