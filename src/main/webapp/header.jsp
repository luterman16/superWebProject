<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="${pageContext.request.contextPath}/eshop" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        Catalog
    </a>

    <div class="col-md-3 text-end">

        <a class="btn btn-block btn-sm btn-dark w-xl-auto mt-2 my-xl-n2" href="${pageContext.request.contextPath}/eshop?command=cart" target="_blank">
            <ya-tr-span data-index="266-0" data-translated="false" data-value="Buy Now for $49"
                        data-type="trSpan">Products in the cart: <%= session.getAttribute("itemsInCort")%> </ya-tr-span></a>
    </div>
</header>