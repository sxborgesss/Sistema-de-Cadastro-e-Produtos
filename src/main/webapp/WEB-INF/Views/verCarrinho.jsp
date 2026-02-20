<%@ page import="com.example.trabalho03.Modelo.Produto" %>
<%@ page import="com.example.trabalho03.Modelo.Carrinho" %><%--
  Created by IntelliJ IDEA.
  User: Samuel
  Date: 10/12/2025
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:import url="/WEB-INF/Views/cabecalho.jsp"/>
<h2>Carrinho</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Preço</th>
    </tr>
    <c:forEach var="p" items="${carrinho.produtos}">
    <tr>
        <td>${p.id}</td>
        <td>${p.nome}</td>
        <td>${p.preco}</td>
        <td><a href="/Cliente/removerProduto?idProduto=${p.id}">Remover</a></td>
    </tr>
    </c:forEach>
</table>
<p>Total: R$${total}</p>

<a href="/produtos">Voltar</a>
</html>
