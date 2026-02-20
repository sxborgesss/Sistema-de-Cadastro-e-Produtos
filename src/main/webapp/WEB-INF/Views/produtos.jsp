<%--
  Created by IntelliJ IDEA.
  User: Samuel
  Date: 10/12/2025
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:import url="/WEB-INF/Views/cabecalho.jsp"/>
<h1>Produtos</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Preço</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${produtos}">
        <tr>
            <td>${p.id}</td>
            <td>${p.nome}</td>
            <td>${p.preco}</td>
            <c:if test="${usuario != null && usuario.tipo == 1}">
                <td><a href="/Cliente/adicionarCarrinho?id=${p.id}">Adicionar ao carrinho</a></td>
            </c:if>
            <c:if test="${usuario != null && usuario.tipo == 2}">
                <td><a href="/Func/removerProdutoFuncionario?id=${p.id}">Remover</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<c:if test="${usuario != null}">
    <form>
        <a href="editarusuario">Editar Usuario</a>
    </form>
</c:if>
    <c:if test="${usuario != null && usuario.tipo == 1}">
        <form>
        <a href="/Cliente/verCarrinho">Carrinho</a>
        </form>
    </c:if>
<c:if test="${usuario != null && usuario.tipo == 2}">
    <form>
        <a href="/Func/editarProduto">Editar produto</a><br>
        <a href="/Func/adicionarproduto">Adicionar produto</a><br>
        <a href="/Func/cadastrarFunc">Cadastrar Funcionario</a>
    </form>
</c:if>
<a href="/">Voltar para pagina inicial</a>
</html>
