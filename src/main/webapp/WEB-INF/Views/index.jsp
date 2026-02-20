<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="cabecalho.jsp"/>
<h2>Login</h2>
<form action="logar" method="post">
    Login: <input type="text" name="login"><br>
    Senha: <input type="password" name="senha"><br>
    <input type="submit" value="Entrar">
</form>

<hr>
<c:if test="${usuario == null || usuario.tipo == 2}">
    <h2>Cadastrar Cliente</h2>
    <form action="cadastrarusuario" method="post">
        Nome: <input type="text" name="nome"><br>
        Login: <input type="text" name="login"><br>
        Senha: <input type="password" name="senha"><br>
        <input type="submit" value="Cadastrar">
    </form>
</c:if>
<hr>
<form action="produtos">
    <a href="produtos">Produtos</a>
</form>
<hr>

<form action="sair" method="get">
    <input type="submit" value="Sair">
</form>