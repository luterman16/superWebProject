<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="p-2 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}/home" class="nav-link px-2 text-secondary">Home</a></li>
            </ul>
            <form method="post" action="/search" 1class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3 ">
                <div>
                <input type="search" name="searchText"  class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
                </div>
            </form>

            <div class="col-md text-end">
                <a class="btn btn-outline-light me-2" href="${pageContext.request.contextPath}/user">Login !</a>
                <a class="btn  btn-success w-xl-auto" href="${pageContext.request.contextPath}/cart" target="_blank">
                    Products in the cart: <%= session.getAttribute("cartProductsListCounter")%> </a>
            </div>
        </div>
    </div>
</header>