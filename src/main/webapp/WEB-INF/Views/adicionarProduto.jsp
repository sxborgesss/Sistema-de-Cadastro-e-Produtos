<%--
  Created by IntelliJ IDEA.
  User: Samuel
  Date: 13/12/2025
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Adicionar Produto</title>
</head>
<body>
<c:import url="/WEB-INF/Views/cabecalho.jsp"/>

<h2>Cadastro de Produto</h2>
<c:if test="${usuario != null && usuario.tipo == 2}">
    <form action="/Func/adicionarproduto" method="POST">
        <label for="nome">Nome do Produto:</label><br>
        <input type="text" id="nome" name="nome" required><br><br>
        <label for="preco">Preço:</label><br>
        <input type="number" id="preco" name="preco" required><br><br>
        <input type="submit" value="Cadastrar Produto">
    </form>
</c:if>
<br>
<a href="/produtos">Ver Produtos</a>
</body>
</html>
