<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title><spring:message code="myOrder"></spring:message></title>
    <!-- Bootstrap 4 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Font awesome 5 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

    <link href="<c:url value="/css/uikit.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<section class="section-content padding-y border-top">
	    <div class="container">
	        <nav class="mb-3">
	            <ol class="breadcrumb">
	                <li class="breadcrumb-item">
	                	<a href="index"><spring:message code="home"></spring:message></a>
	                </li>
	                <li class="breadcrumb-item active" aria-current="page">
	                	<spring:message code="orderHistory"></spring:message>
	                </li>
	            </ol>
	        </nav>
	
	        <div class="row">
	            <main class="col-sm-12">
					<c:forEach items="${orderList}" var="order">
		                <div class="card">
		                    <table class="table table-hover shopping-cart-wrap">
		                        <thead class="text-muted">
		                        <tr>
		                            <th scope="col"><spring:message code="product"></spring:message></th>
		                            <th scope="col" width="120"><spring:message code="quantity"></spring:message></th>
		                            <th scope="col" width="160"><spring:message code="price"></spring:message></th>
		                            <th scope="col" class="text-right" width="120"><spring:message code="orderTime"></spring:message></th>
		                        </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${order.getProducts()}" var="item">
				                        <tr>
				                            <td>
				                                <figure class="media">
				                                    <div class="img-wrap">
				                                    	<img src="${item.key.getImagePath()}" class="img-thumbnail img-sm">
				                                    </div>
				                                    <figcaption class="media-body">
				                                        <h6 class="title text-truncate">${item.key.getProductName()} </h6>
				                                        <dl class="dlist-inline small">
				                                            <dt><spring:message code="brand"></spring:message>:</dt>
				                                            <dd>${item.key.getBrand()}</dd>
				                                        </dl>
				                                    </figcaption>
				                                </figure>
				                            </td>
				                            <td>
				                                <h6 class="text-truncate">${item.value}</h6>
				                            </td>
				                            <td>
				                                <div class="price-wrap">
				                                    <var class="price">USD ${item.key.getPrice()}</var>
				                                </div> <!-- price-wrap .// -->
				                            </td>
				                            <td class="text-right">
				                                <a href="" class="btn btn-outline-danger"> ${order.getOrderTime()}</a>
				                            </td>
				                        </tr>
				               		</c:forEach>
		                        </tbody>
		                    </table>
		                </div> <!-- card.// -->
	                </c:forEach>
	            </main> <!-- col.// -->
	        </div>

   		</div> <!-- container .//  -->
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>