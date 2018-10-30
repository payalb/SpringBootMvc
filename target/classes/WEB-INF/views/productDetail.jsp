<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="productDetail"></spring:message></title>
    <!-- Bootstrap 4 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Font awesome 5 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

    <link href="css/uikit.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<section class="section-content bg padding-y-sm">
    <div class="container">
        <nav class="mb-3">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index"><spring:message code="home"></spring:message></a></li>
                <li class="breadcrumb-item active" aria-current="page">
                	<spring:message code="productDetail"></spring:message>
                </li>
            </ol>
        </nav>

        <div class="row">
            <div class="col-xl-12 col-md-9 col-sm-12">
                <main class="card">
                    <div class="row no-gutters">
                        <aside class="col-sm-6 border-right">
                            <article class="gallery-wrap">
                                <div class="img-big-wrap">
                                    <div><a href="${product.getImagePath()}" data-fancybox=""><img
                                            src="${product.getImagePath()}"></a></div>
                                </div> <!-- slider-product.// -->
                            </article> <!-- gallery-wrap .end// -->
                        </aside>
                        <aside class="col-sm-6">
                            <article class="card-body">
                                <!-- short-info-wrap -->
                                <h3 class="title mb-3">${product.getProductName()}</h3>

                                <div class="mb-3">
                                    <var class="price h3 text-primary">
                                        <span class="currency">US $</span><span class="num">${product.getPrice()}</span>
                                    </var>
                                </div> <!-- price-detail-wrap .// -->
                                <dl>
                                	<dt><spring:message code="description"></spring:message></dt>
                                	<dd>
                                		${product.getDescription()}
                                	</dd>
                                </dl>
                                <dl class="row">
                                    <dt class="col-sm-3"><spring:message code="brand"></spring:message></dt>
                                    <dd class="col-sm-9">${product.getBrand()}</dd>

                                    <dt class="col-sm-3">
                                    	<spring:message code="stock"></spring:message>
                                    </dt>
                                    <dd class="col-sm-9">${product.getStock()}</dd>
                                </dl>
                                <hr>
                                <c:forEach items="${product.getProperties()}" var="property">
                                	<div class="row">
	                                    <div class="col-sm-12" style="padding-left:0;">
	                                        <dl class="dlist-inline">
	                                            <dt class="col-sm-3">${property.getPropertyName()}:</dt>
	                                            <dd class="col-sm-5">
	                                                <select class="form-control form-control-sm" style="width:70px;">
	                                                	<c:forEach items="${property.getPropertyValues()}" var="propertyValue">
		                                                    <option> ${propertyValue.getPropertyValueName()}</option>
		                                            	</c:forEach>
	                                                </select>
	                                            </dd>
	                                        </dl>  <!-- item-property .// -->
	                                    </div> <!-- col.// -->
	                                </div> <!-- row.// -->
                                </c:forEach>
                                <div class="row">
                                    <div class="col-sm-12" style="padding-left:0;">
                                        <dl class="dlist-inline">
                                            <dt class="col-sm-3"><spring:message code="quantity"></spring:message>:</dt>
                                            <dd class="col-sm-5">
                                            <input type="number" name="productQuantity" id="productQuantity"
                                            	class="form-control form-control-sm" min="1" max="${product.getStock()}" step="1" value="1"> 
                                                <!-- <select id="productQuantity" class="form-control form-control-sm" style="width:70px;">
                                                    <option value="1"> 1</option>
                                                    <option value="2"> 2</option>
                                                    <option value="3"> 3</option>
                                                </select> -->
                                            </dd>
                                        </dl>  <!-- item-property .// -->
                                    </div> <!-- col.// -->
                                </div> <!-- row.// -->
                                <hr>
                                <div>
                                	<a id="add-to-cart" class="btn btn-outline-primary" style="color: #007bff;">
                                		<spring:message code="addToCart"></spring:message>
                                	</a>
                                </div>
                                <!-- short-info-wrap .// -->
                            </article> <!-- card-body.// -->
                        </aside> <!-- col.// -->
                    </div> <!-- row.// -->
                </main> <!-- card.// -->
            </div> <!-- col // -->

        </div> <!-- row.// -->
    </div><!-- container // -->
</section>

<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">
$( document ).ready(function() {
	var pId = '${product.getProductId()}';
	console.log(pId);
	$("#add-to-cart").click(function() {
		var form = $('<form action="addCartItem">' + 
	    '<input type="hidden" name="id" value="' + pId + '">' +
	    '<input type="hidden" name="quantity" value="' + $("#productQuantity").val() + '">' +
	    '</form>');
		$(document.body).append(form);
		form.submit();
	});
});
</script>
</body>
</html>