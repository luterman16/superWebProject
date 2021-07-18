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
    <title>Product</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="header.jsp" %>
<body>
<div class="container">

    <div class="row mb-2">
                <div class="col-md-10">
                    <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative"
                         style="margin: 0.5rem">
                        <div class="col-auto d-none d-lg-block">
                            <img src="${contextPath}/images/${item.getImage_path()}" alt="${item.getImage_path()}">
                        </div>
                        <div class="col p-4 d-flex flex-column position-static">
                            <div class="card-body">
                                <h5 class="card-title">${item.getName()}</h5>
                                <p class="card-text">${item.getDescription()}</p>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut, consequuntur cumque doloribus eos, fuga illo libero magnam maxime minima modi molestiae obcaecati quae quibusdam quo saepe sint tempore voluptatem voluptatibus.</p>
                                <p class="card-text">Price: <strong class="text-muted">${item.getPrice()} $</strong></p>
                            </div>
                            <a href="${pageContext.request.contextPath}/category/addItem?itemId=${item.getId()}&categoryId=${item.getCategory().getId()}&pageNumber=${page + 1}&pageSize=${pageSize}&sortField=name&sortDirection=ASC">
                                <button type="button" class="btn btn-success" style="width: 100%">Add to cart</button>
                            </a>
                        </div>
                    </div>
                </div>
    </div>
</div>
</body>
</html>
