package com.example.trabalho03.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos=new ArrayList<>();
    public List<Produto> getProdutos(){
        return produtos;
    }
    public float getPreçoTotal(){
        float total=0;
        for(Produto p:produtos)
            total+= Float.parseFloat(p.getPreco());
        return total;
    }
    public void removerProduto(int id){
        for(Produto p:produtos){
            if(p.getId()==id){
                produtos.remove(p);
                return;
            }
        }
    }
}
