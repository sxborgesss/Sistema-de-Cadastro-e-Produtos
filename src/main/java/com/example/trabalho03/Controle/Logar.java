package com.example.trabalho03.Controle;

import DAO.ErroDao;
import DAO.UsuarioDao;
import com.example.trabalho03.Modelo.Carrinho;
import com.example.trabalho03.Modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Logar{
    @PostMapping("/logar")
    public String logar(@RequestParam("login") String login,
                        @RequestParam("senha") String senha,
                        HttpSession sessao,
                        RedirectAttributes atributos) {
        if (sessao.getAttribute("usuario") != null) {
            atributos.addFlashAttribute("mensagem", "Você já está logado! Saia da sessão e tente novamente");
            return "redirect:/";
        }

        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            atributos.addAttribute("mensagem", "Preencha todos os campos!");
            return "redirect:/";
        }

        try {
            UsuarioDao dao = new UsuarioDao();
            Usuario logado = dao.buscar(login, senha);

            if (logado != null) {
                sessao.setAttribute("usuario", logado);
                sessao.setAttribute("carrinho", new Carrinho());

                return "redirect:/produtos";
            } else {
                atributos.addFlashAttribute("mensagem", "Login ou senha incorretos!");
                return "redirect:/";
            }

        } catch (ErroDao e) {
            atributos.addFlashAttribute("mensagem", "Erro ao tentar logar!");
            return "redirect:/";
        }
    }
}