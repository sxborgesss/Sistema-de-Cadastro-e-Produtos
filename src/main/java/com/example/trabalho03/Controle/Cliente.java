package com.example.trabalho03.Controle;

import DAO.ErroDao;
import DAO.ProdutosDao;
import com.example.trabalho03.Modelo.Carrinho;
import com.example.trabalho03.Modelo.Produto;
import com.example.trabalho03.Modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Cliente")
public class Cliente{
    //Função Adicionar produto ao carrinho, Apenas Usuários Logados
    @GetMapping("/adicionarCarrinho")
    public String adicionarCarrinho(@RequestParam("id") int id, @SessionAttribute(value = "usuario", required = false) Usuario usuario,
                                    @SessionAttribute (value = "carrinho", required = false) Carrinho carrinho, RedirectAttributes atributos) {
        if (usuario == null) {
            atributos.addFlashAttribute("mensagem", "Você precisa estar Logado");
            return "redirect:/";
        }
        try {
            ProdutosDao dao = new ProdutosDao();
            Produto p = dao.buscarPorId(id);
            if (p != null) {
                carrinho.getProdutos().add(p);
                atributos.addFlashAttribute("mensagem", "Produto adicionado com sucesso!");
                return "redirect:/produtos";
            }
        } catch (ErroDao e) {
            atributos.addFlashAttribute("mensagem", "Erro ao tentar adicionar produto!");
            return "redirect:/produtos";
        }
        return "redirect:/produtos";
    }
    //Remover Produto Cliente
    @GetMapping("/removerProduto")
    public String removerDoCarrinho(@RequestParam("idProduto") int id,
                                    @SessionAttribute("carrinho") Carrinho carrinho, RedirectAttributes atributo) {
        carrinho.removerProduto(id);
        atributo.addFlashAttribute("mensagem", "Produto removido com sucesso!");
        return "redirect:/Cliente/verCarrinho";
    }
    //Visualizar Carrinho CLiente
    @GetMapping("/verCarrinho")
    public String verCarrinho(Model modelo, @SessionAttribute("carrinho") Carrinho carrinho) {
        modelo.addAttribute("total", carrinho.getPreçoTotal());
        return "/verCarrinho";
    }
}
