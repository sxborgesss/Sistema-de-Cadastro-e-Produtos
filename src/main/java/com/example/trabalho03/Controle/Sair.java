package com.example.trabalho03.Controle;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Sair{
    @GetMapping("/sair")
    public String sair(HttpSession sessao, RedirectAttributes atributo) {
        if (sessao != null) {
            sessao.invalidate();
        }
        atributo.addFlashAttribute("mensagem", "Sessão encerrada");
        return "redirect:/";
    }
}