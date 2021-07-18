<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.05.2021
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="header.jsp" %>
<body>
<div class="container">
    </div>
    <div class="row mb-2">
        <c:if test="${not empty category.getProductList()}">
            <c:forEach items="${category.getProductList()}" var="item">
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
                    <a href="${pageContext.request.contextPath}/category/addItem?itemId=${item.getId()}&categoryId=${category.getId()}&pageNumber=${page + 1}&pageSize=${pageSize}&sortField=name&sortDirection=ASC">
                        <button type="button" class="btn btn-success" style="width: 100%">Add to cart</button>
                    </a>
                </div>
            </div>
            </div>
            </c:forEach>
        </c:if>
    </div>
<nav aria-label="...">
    <ul class="pagination">
        <li class="page-item" aria-current="page">
            <c:choose>
                <c:when test="${page < 1}">
                    <a style="pointer-events: none; color: grey" aria-disabled="true" class="page-link" href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&?pageNumber=${page}&pageSize=${pageSize}&sortField=name&sortDirection=ASC">Privious</a>
                </c:when>
                <c:when test="${page >= 1}">
                    <a class="page-link" href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&pageNumber=${page}&pageSize=${pageSize}&sortField=name&sortDirection=ASC">Privious</a>
                </c:when>
                <c:otherwise>
                    <a  style="pointer-events: none; color: grey" class="page-link" href="#">Privious</a>
                </c:otherwise>
            </c:choose>

        </li>
        <li class="page-item" aria-current="page">
            <a class="page-link" href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&pageNumber=${page + 1}&pageSize=${pageSize}&sortField=name&sortDirection=ASC">${page + 1}</a>
        </li>
        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&pageNumber=${page + 2}&pageSize=${pageSize}&sortField=name&sortDirection=ASC">Next</a></li>
    </ul>
    Элементов на странице:
    <span><a href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&pageNumber=1&pageSize=5&sortField=name&sortDirection=ASC">5</a></span>
    <span><a href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&pageNumber=1&pageSize=10&sortField=name&sortDirection=ASC">10</a></span>
    <span><a href="${pageContext.request.contextPath}/category?categoryId=${category.getId()}&pageNumber=1&pageSize=20&sortField=name&sortDirection=ASC">20</a></span>
</nav>
</div>
</body>
</html>
