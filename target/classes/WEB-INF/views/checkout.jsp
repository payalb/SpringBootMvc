<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="checkout"></spring:message></title>
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
    <section  class="section-content padding-y border-top">
        <div class="container">
            <nav class="mb-3">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                    	<a href="index"><spring:message code="home"></spring:message></a>
                    </li>
                    <li class="breadcrumb-item active" aria-current="page">
                    	<spring:message code="checkout"></spring:message>
                    </li>
                </ol>
            </nav>

            <br>
            <div class="row" >
                <div class="col-md-4 order-md-2 mb-4">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted"><spring:message code="yourCart"></spring:message></span>
                    </h4>
                    <ul class="list-group mb-3">
                    	<c:set var="total" value="${0}"/>
                    	<c:forEach items="${cartList}" var="cart">
                    		<c:set var="price" value="${cart.getProduct().getPrice()}"/>
                    		<c:set var="quantity" value="${cart.getAmount()}"/>
	                        <li class="list-group-item d-flex justify-content-between lh-condensed">
	                            <div>
	                                <h6 class="my-0">${cart.getProduct().getProductName()}</h6>
	                                <small class="text-muted">Quantity: ${quantity}</small>
	                            </div>
	                            <span class="text-muted">$ ${price}</span>
	                        </li>
	                        <c:set var="total" value="${total + price * quantity}" />
	                    </c:forEach>
                        <li class="list-group-item d-flex justify-content-between">
                            <span><spring:message code="totalAmount"></spring:message> (USD)</span>
                            <strong>$${total }</strong>
                        </li>
                    </ul>
                </div>
                <div class="col-md-8 order-md-1">
                    <h4 class="mb-3"><spring:message code="shipAddress"></spring:message></h4>
                    <c:forEach items="${addressList}" var="address">
	                    <div class="form-check mb-2">
	                        <input class="form-check-input" type="radio" name="existAddress" id="existAddress${address.getAddressId()}">
	                        <label class="form-check-label" for="existAddress${loop.index}">
	                            <span class="h6 my-0">${address.getFullName()}</span>, ${address.getStreet()} ${address.getStreetTwo()},
	                             ${address.getCity()}, ${address.getState()} ${address.getZip()}, ${address.getCountry()}
	                        </label>
	                    </div>
                    </c:forEach>
                    <hr class="mb-4">
                       <h4 class="mb-3"><spring:message code="payment"></spring:message></h4>
                       <c:if test="${errorMsg != null}">
							<div class="alert alert-warning" role="alert">${errorMsg}</div>
						</c:if>
                       <c:forEach items="${cardList}" var="card" varStatus="loop">
	                    <div class="form-check mb-2">
	                        <input class="form-check-input" type="radio" name="existCard" id="existCard${card.getCardId()}">
	                        <label class="form-check-label" for="existCard${card.getCardId()}">
	                            <span class="h6 my-0">${card.getCardName()}</span>, ${card.getCardNum()}
	                        </label>
	                    </div>
                    </c:forEach>
                    <hr class="mb-4">
                    <button id="place-order" class="btn btn-primary btn-lg btn-block" type="submit">
                    	<spring:message code="placeOrder"></spring:message>
                    </button>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#place-order').click(function() {
				
				var addId = $('input[type=radio][name=existAddress]:checked').attr('id').substring(12);
				var cardId = $('input[type=radio][name=existCard]:checked').attr('id').substring(9);
				var form = $('<form action="checkout">' + 
					    '<input type="hidden" name="addId" value="' + addId + '">' +
					    '<input type="hidden" name="cardId" value="' + cardId + '">' +
					    '</form>');
						$(document.body).append(form);
						form.submit();
			});
		});
	</script>
</body>
</html>