<%--
  Created by IntelliJ IDEA.
  User: Samuel
  Date: 14/12/2025
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cadastrar Novo Funcionário</title>
</head>
<body>
<c:import url="/WEB-INF/Views/cabecalho.jsp"/>

<h2>Cadastro de Funcionário</h2>
<c:if test="${usuario != null && usuario.tipo == 2}">
<form action="cadastrarFunc" method="POST">
    <label for="nome">Nome:</label><br>
    <input type="text" name="nome" required><br><br>
    <label for="login">Login:</label><br>
    <input type="text" name="login" required><br><br>
    <label for="senha">Senha:</label><br>
    <input type="password" name="senha" required><br><br>
    <input type="submit" value="Cadastrar">
</form>
</c:if>
<br>
<a href="/produtos">Voltar</a>
</body>
</html>
