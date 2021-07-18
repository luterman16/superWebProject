<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<%@ include file="header.jsp" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<body>
<div class="container">
    <h1>Cart </h1>
    <div class="row">
        <div class="col-md-8">
            <c:if test="${not empty cart}">
            <c:forEach items="${cart}" var="item">
            <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative"
                 style="margin: 0.5rem">
                <div class="col-auto d-none d-lg-block">
                    <img src="${contextPath}/images/${item.getImage_path()}" alt="${item.getImage_path()}">
                </div>
                <div class="col p-4 d-flex flex-column position-static">
                    <div class="card-body">
                        <a href="${pageContext.request.contextPath}/product?itemId=${item.getId()}"><h5 class="card-title">${item.getName()}</h5></a>
                        <p class="card-text">${item.getDescription()}</p>
                        <p class="card-text">Price: <strong class="text-muted">${item.getPrice()} $</strong></p>
                    </div>
                    <a href="${pageContext.request.contextPath}/cart/remove?itemId=${item.getId()}">
                        <button type="button" class="btn btn-warning" style="width: 100%">Delete in cart</button>
                    </a>
                </div>
            </div>
            </c:forEach>
            </c:if>
        </div>
        <c:if test="${not empty totalprice}">
            <p>Total price: ${totalprice}</p>
        </c:if>

        <form method="post" action="cart/confirmpay" 1class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 ">
            <div class="col-md-4">
                <button type="submit" class="btn btn-success">Confirm order</button>
            </div>
        </form>

    </div>
</div>
</body>
</html>