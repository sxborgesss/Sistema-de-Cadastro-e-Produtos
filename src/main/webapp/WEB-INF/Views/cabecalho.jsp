<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <%request.setCharacterEncoding("UTF-8");%>
    <c:if test="${mensagem!=null}">
        <div class="mensagem">
                ${mensagem}
        </div>
    </c:if>
    <h1>Sistema Produtos</h1>

    <c:if test="${not empty sessionScope.usuario}">
        <p>Seja bem vindo ${sessionScope.usuario.nome}</p>
    </c:if>
    <hr>
</header>
