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
<c:set var="type" value="${CATEGORY_NAME}"/>
<%@ include file="header.jsp" %>
<body>
<div class="container">
    <h1>Catalog ${type}</h1>
    <form id="filter-form" method="post" class="w-50">
        <input type="hidden" name="command" value="filter" >
        <div class="input-group mb-3">
            <span class="input-group-text">Цена От</span>
            <input type="text" class="form-control" placeholder="" name="pricebefore" aria-label="pricebefore">
            <span class="input-group-text">Цена До</span>
            <input type="text" class="form-control" placeholder="" name="priceafter" aria-label="priceafter">
        </div>
        <div>
            <input name="category" type="hidden"/>
            <select id="categories">
                <option value="0" >Любая категория</option>
                <option value="1">Мобильные телефоны</option>
                <option value="2">Ноутбуки</option>
                <option value="3">Холодильники</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Отфильтровать</button>
    </form>
</div>
<div class="row mb-2">
    <c:if test="${not empty items}">
        <c:forEach items="${items}" var="item">
            <div class="col-md-6">
                <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative"
                     style="margin: 0.5rem">
                    <div class="col-auto d-none d-lg-block">
                        <img src="${contextPath}/static/${item.getImage_path()}" alt="${item.getImage_path()}">
                    </div>
                    <div class="col p-4 d-flex flex-column position-static">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/eshop?command=product_info&itemId=${item.getId()}"><h5 class="card-title">${item.getName()}</h5></a>
                            <p class="card-text">${item.getDescription()}</p>
                            <p class="card-text">Price: <strong class="text-muted">${item.getPrice()} $</strong></p>
                        </div>
                        <a href="${pageContext.request.contextPath}/eshop?command=item&product=${item.getCategory_id()}&addItem=1&itemId=${item.getCategory_id()}">
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
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
        </li>
        <li class="page-item active"><a class="page-link" href="#">1</a></li>
        <li class="page-item" aria-current="page">
            <a class="page-link" href="#">2</a>
        </li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#">Next</a>
        </li>
    </ul>
    <span>${items.size()}/10</span>
    <span><a href="#">20</a></span>
    <span><a href="#">50</a></span>
    <span><a href="#">Все</a></span>
</nav>
</div>
<script>
    $('select#categories').on('change', function() {
        $('input[name="category"]').val(this.value);
    });

</script>
</body>
</html>
