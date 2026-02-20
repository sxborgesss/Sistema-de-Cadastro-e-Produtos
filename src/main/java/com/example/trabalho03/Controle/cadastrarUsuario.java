package com.example.trabalho03.Controle;

import DAO.ErroDao;
import DAO.UsuarioDao;
import com.example.trabalho03.Modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastrarusuario")
public class cadastrarUsuario{

    UsuarioDao usuarioDao = new UsuarioDao();

    @GetMapping
    public String exibirForm(@SessionAttribute(value = "usuario", required = false) Usuario usuarioLogado, RedirectAttributes atributo) {

        if (usuarioLogado != null && usuarioLogado.getTipo() == 1) {
            atributo.addFlashAttribute("mensagem", "Você não pode acessar essa área");
            return "redirect:/";
        }
        return "cadastrarUsuario";
    }

    @PostMapping
    public String cadastrar(@RequestParam("nome") String nome,
                            @RequestParam("login") String login,
                            @RequestParam("senha") String senha, Usuario usuarioLogado,  RedirectAttributes atributo) {

        if (usuarioLogado != null && usuarioLogado.getTipo() == 1) {
            atributo.addFlashAttribute("mensagem", "Você não pode acessar essa aréa");
            return "redirect:/";
        }
        int tipo = 1;

        if (nome != null && !nome.isBlank() && login != null && !login.isBlank() &&
                senha != null && !senha.isBlank()) {

            Usuario novoUsuario = new Usuario(nome, login, senha, tipo);

            try {
                usuarioDao.cadastrar(novoUsuario);
                atributo.addFlashAttribute("mensagem", "Usuario cadastrado com sucesso");
                return "redirect:/";
            } catch (ErroDao e) {
                atributo.addFlashAttribute("mensagem", "Login já em uso");
                return "redirect:/";
            }
        } else {
            atributo.addFlashAttribute("mensagem", "Preencha todos os campos!!!");
            return "redirect:/";
        }
    }
}
