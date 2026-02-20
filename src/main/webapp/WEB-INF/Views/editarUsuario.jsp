<%--
  Created by IntelliJ IDEA.
  User: Samuel
  Date: 14/12/2025
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Editar Perfil</title>
</head>
<body>
<c:import url="/WEB-INF/Views/cabecalho.jsp"/>

<h2>Editar Usuario</h2>
<form action="editarusuario" method="POST">
    <label for="login">Novo Login:</label><br>
    <input type="text" name="login" required><br><br>
    <label for="senha">Nova Senha:</label><br>
    <input type="password" name="senha" required><br><br>
    <input type="submit" value="Salvar">
</form>
<br>
<a href="/produtos">Voltar</a>
</body>
</html>
