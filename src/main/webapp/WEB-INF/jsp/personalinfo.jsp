<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign-in</title>
</head>
<body>
<%@ include file="header.jsp" %>
<c:set var="user" value="${userinfo}"/>
<c:set var="orderslist" value="${orderslist}"/>

<div class="container">
    <div class="row ">
        <h2>Personal info</h2>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">Name: ${user.getName()}</li>
            <li class="list-group-item">Surname: ${user.getSurname()} </li>
            <li class="list-group-item">Date of birthday: ${user.getDateOfBirthday()}</li>
            <li class="list-group-item">e-mail:${user.getEmail()}</li>
        </ul>
    </div>
    <div>
        <h2>История заказов</h2>
        <c:if test="${not empty orderslist}">
        <c:forEach items="${orderslist}" var="order">
        <div class="card">
            <h3 class="card-header">Заказ: ${order.getId()} / дата заказа: ${order.getDate()}</h3>
                <c:forEach items="${order.getProductsList()}" var="item">
                    <div class="col-md-6">
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
                            </div>
                        </div>
                    </div>
                </c:forEach>
        </div>
        </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>