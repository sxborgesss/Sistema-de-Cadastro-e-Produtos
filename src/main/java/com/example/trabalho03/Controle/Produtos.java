package com.example.trabalho03.Controle;

import DAO.ErroDao;
import DAO.ProdutosDao;
import com.example.trabalho03.Modelo.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Produtos {
    @GetMapping("/produtos")
    public String listarProdutos(Model modelo) {
        try {
            ProdutosDao dao = new ProdutosDao();
            List<Produto> lista = dao.listarProdutos();
            modelo.addAttribute("produtos", lista);
            return "produtos";
        } catch (ErroDao e) {
            return "redirect:/?mensagem=Erro ao tentar acessar a lista!";
        }
    }

}
