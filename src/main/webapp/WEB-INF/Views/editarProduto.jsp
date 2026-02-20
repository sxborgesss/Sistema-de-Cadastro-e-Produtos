<%--
  Created by IntelliJ IDEA.
  User: Samuel
  Date: 13/12/2025
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Editar Produto</title>
</head>
<body>
<c:import url="/WEB-INF/Views/cabecalho.jsp"/>

<c:if test="${usuario != null && usuario.tipo == 2}">
    <form action="${pageContext.request.contextPath}/editarProduto" method="POST">
        <label for="id">ID:</label><br>
        <input type="number" name="id" required><br><br>
        <label for="nome">Nome:</label><br>
        <input type="text" name="nome" required><br><br>
        <label for="preco">Preço:</label><br>
        <input type="number" name="preco" required><br><br>
        <input type="submit" value="Salvar">
    </form>
</c:if>
<br>
<a href="/produtos">Produtos</a>
</body>
</html>
