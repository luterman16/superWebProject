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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<body>
<%@ include file="header.jsp" %>
<div class="container">
    Каталог
    <c:if test="${not empty categories}">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <c:forEach items="${categories}" var="category">
                <a href="${pageContext.request.contextPath}/eshop?command=item&product=${category.getId()}" >
                    <div class="col" style="margin-top: 10px">
                        <div class="card shadow-sm">
                            <div><h4 class="text-center">${category.getName()}</h4></div>
                            <div class="card">
                                <img src="${contextPath}/static/${category.getIMAGE_PATH()}" alt="">
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </c:if>


</div>
</body>
</html>
