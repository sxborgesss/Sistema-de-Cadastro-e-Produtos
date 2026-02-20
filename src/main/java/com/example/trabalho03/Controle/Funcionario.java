package com.example.trabalho03.Controle;

import DAO.ErroDao;
import DAO.ProdutosDao;
import DAO.UsuarioDao;
import com.example.trabalho03.Modelo.Produto;
import com.example.trabalho03.Modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/Func")
public class Funcionario{
    //Verificação de Usuário, Cliente, Funcionário ou Usuário sem cadastro.
    @GetMapping("cadastrarFunc")
    public String exibirFormCadastro(@SessionAttribute(value = "usuario", required = false) Usuario usuarioLogado,
                             RedirectAttributes atributo){

        if (usuarioLogado != null && usuarioLogado.getTipo() == 2) {
            return "cadastrarFunc";
        } else {
            atributo.addFlashAttribute("mensagem", "Apenas funcionarios podem acessar essa area");
            return "redirect:/";
        }
    }
    //Função cadastrar Funcionário, Apenas Funcionários
    @PostMapping("cadastrarFunc")
    public String cadastrar(@RequestParam("nome") String nome, @RequestParam("login") String login, @RequestParam("senha") String senha,
                            @SessionAttribute(value = "usuario", required = false) Usuario usuarioLogado,
                            RedirectAttributes atributo) {

        if (usuarioLogado == null || usuarioLogado.getTipo() != 2) {
            atributo.addFlashAttribute("mensagem", "Apenas funcionario podem acessar essa area");
            return "redirect:/";
        }

        if (nome == null || nome.isBlank() || login == null || login.isBlank() || senha == null || senha.isBlank()) {
            atributo.addFlashAttribute("mensagem", "Preencha todos os campos");
            return "redirect:/cadastrarfunc";
        }

        try {
            Usuario novoFuncionario = new Usuario(nome, login, senha, 2);

            UsuarioDao usuarioDao = new UsuarioDao();
            usuarioDao.cadastrar(novoFuncionario);
            atributo.addFlashAttribute("mensagem", "Funcionário " + nome + " cadastrado com sucesso");
            return "redirect:/produtos";

        } catch (ErroDao e) {
            atributo.addFlashAttribute("mensagem", "Login já em uso");
            return "redirect:/cadastrarfunc";
        }
    }
    //Função Editar Produto, Apenas Funcionários!
    @GetMapping("editarProduto")
    public String exibirFormEditar(@SessionAttribute(value = "usuario", required = false) Usuario usuario, RedirectAttributes atributo) {

        if (usuario != null && usuario.getTipo() == 2) {
            return "editarProduto";
        } else {
            atributo.addFlashAttribute("mensagem", "Apenas funcionário podem acessar essa área!");
            return "redirect:/";
        }
    }
    @PostMapping("editarProduto")
    public String atualizar(@RequestParam("id") String idStr,
                            @RequestParam("nome") String novoNome,
                            @RequestParam("preco") String novoPreco,
                            @SessionAttribute(value = "usuario", required = false) Usuario usuario, RedirectAttributes atributo) {

        if (usuario == null || usuario.getTipo() != 2) {
            atributo.addFlashAttribute("mensagem", "Apenas funcionário podem acessar essa área!");
            return "redirect:/";
        }
        if (idStr != null && !idStr.isEmpty() &&
                novoNome != null && !novoNome.isEmpty() &&
                novoPreco != null && !novoPreco.isEmpty()) {

            int id = Integer.parseInt(idStr);
            Produto produto = new Produto(id, novoNome, novoPreco);

            ProdutosDao produtosDao = new ProdutosDao();
            produtosDao.editarProduto(produto);
            atributo.addFlashAttribute("mensagem", "Produto editado com sucesso!");
            return "redirect:/produtos";
        } else {
            atributo.addFlashAttribute("mensagem", "Preencha todos os campos!!!");
            return "redirect:/editarProduto";
        }
    }
    //Função Remover Produto do Sistema, Apenas Funcionários!
    @GetMapping("removerProdutoFuncionario")
    public String remover(@RequestParam(value = "id", required = false) String id,
                          @SessionAttribute(value = "usuario", required = false) Usuario usuario, RedirectAttributes atributo) {
        if (usuario != null && usuario.getTipo() == 2) {
            if (id != null && !id.isEmpty()) {
                int idProduto = Integer.parseInt(id);

                ProdutosDao produtosDao = new ProdutosDao();
                produtosDao.remover(idProduto);
                atributo.addFlashAttribute("mensagem", "Produto removido com sucesso!");
                return "redirect:/produtos";
            }
            atributo.addFlashAttribute("mensagem", "Nenhum produto encontrado!");
            return "redirect:/produtos";

        } else {
            atributo.addFlashAttribute("mensagem", "Apenas funcionários podem acessar essa área!");
            return "redirect:/";
        }
    }
    //Adicionar Produtos ao Sistema, Apenas Funcionarios!
    @GetMapping("adicionarproduto")
    public String exibirFormAdicionarProduto(HttpSession sessao, RedirectAttributes atributos) {
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        if (usuario != null && usuario.getTipo() == 2) {
            return "adicionarProduto";
        } else {
            atributos.addFlashAttribute("mensagem", "Apenas funcionario podem acessar essa área");
            return "redirect:/";
        }
    }

    @PostMapping("adicionarproduto")
    public String salvar(@RequestParam("nome") String nome, @RequestParam("preco") String preco, HttpSession sessao, RedirectAttributes atributos) {
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        if (usuario != null && usuario.getTipo() == 2) {

            if (nome == null || nome.isBlank() || preco == null || preco.isBlank()) {
                atributos.addFlashAttribute("mensagem", "Você precisa estar logado");
                return "redirect:/adicionarproduto";
            }
            try {
                Produto produto = new Produto();
                produto.setNome(nome);
                produto.setPreco(preco);

                ProdutosDao produtosDao = new ProdutosDao();
                produtosDao.adicionarProduto(produto);
                atributos.addFlashAttribute("mensagem", "Produto adicionado com sucesso");
                return "redirect:/produtos";

            } catch (ErroDao e) {
                atributos.addFlashAttribute("mensagem", "Erro ao adicionar produto");
                return "redirect:/adicionarproduto";
            }
        }else{
            atributos.addFlashAttribute("mensagem", "Apenas funcionários podem acessar assa área");
            return "redirect:/";}
    }
}
