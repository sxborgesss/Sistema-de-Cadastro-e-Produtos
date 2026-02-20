package com.example.trabalho03.Controle;

import DAO.ErroDao;
import DAO.UsuarioDao;
import com.example.trabalho03.Modelo.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editarusuario")
public class editarUsuario{

    UsuarioDao usuarioDao = new UsuarioDao();

    @GetMapping
    public String exibirForm(@SessionAttribute(value = "usuario", required = false) Usuario usuarioLogado, RedirectAttributes atributo) {

        if (usuarioLogado != null) {
            return "editarUsuario";
        } else {
            atributo.addFlashAttribute("mensagem", "Você precisa estar logado!!!!");
            return "redirect:/";
        }
    }

    @PostMapping
    public String EditarUsuario(@RequestParam("login") String novoLogin,
                                   @RequestParam("senha") String novaSenha,
                                   @SessionAttribute("usuario") Usuario usuarioLogado,
                                RedirectAttributes atributo) {

        if (novoLogin != null && !novoLogin.isBlank() && novaSenha != null && !novaSenha.isBlank()) {

            usuarioLogado.setLogin(novoLogin);
            usuarioLogado.setSenha(novaSenha);

            try{
                usuarioDao.editarUsuario(usuarioLogado);
                atributo.addFlashAttribute("mensagem", "Usuário editado com sucesso!");
                return "redirect:/produtos";
            }catch (ErroDao e){
                atributo.addFlashAttribute("mensagem", "Login já em uso!");
                return "redirect:/editarusuario";
            }
        } else {
            atributo.addFlashAttribute("mensagem", "Preencha todos os campos!");
            return "redirect:/editarusuario?";
        }
    }
}