<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title><spring:message code="editCard"></spring:message></title>

<!-- Bootstrap 4 -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!-- Font awesome 5 -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css" />

<link href="css/uikit.css" rel="stylesheet" type="text/css">
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
						<spring:message code="editCard"></spring:message>
					</li>
				</ol>
			</nav>

			<br>
			<div class="row" >
	            <div class="col-md-12 order-md-1">
	                <h4 class="mb-3"><spring:message code="payment"></spring:message></h4>
	                <form class="needs-validation" action="editCard">
	                    <div class="row">
	                        <div class="col-md-6 mb-3">
	                        	<input type="hidden" class="form-control" id="cardId" name="cardId"
	                                   value="${card.getCardId()}" required>
	                            <label for="cardName"><spring:message code="name"></spring:message></label>
	                            <input type="text" class="form-control" id="cardName" name="cardName"
	                                   placeholder="<spring:message code="name"></spring:message>" 
	                                   value="${card.getCardName()}" required>
	                        </div>
	                        <div class="col-md-6 mb-3">
	                            <label for="cardNum"><spring:message code="cardNum"></spring:message></label>
	                            <input type="number" class="form-control" id="cardNum" name="cardNum"
	                                   placeholder="<spring:message code="cardNum"></spring:message>" 
	                                   value="${card.getCardNum()}" required>
	                        </div>
	                    </div>
						<div class="row">
	                        <div class="col-md-6 mb-3">
	                            <label for="expiration"><spring:message code="expiration"></spring:message></label>
	                            <input type="text" class="form-control" id="expiration" name="expiration"
	                                   placeholder="MM-yyyy" value="<fmt:formatDate value="${card.getExpiration()}" pattern="MM-yyyy"/>" required>
	                        </div>
	                        <div class="col-md-6 mb-3">
	                            <label for="cvv">CVV</label>
	                            <input type="number" class="form-control" id="cvv" name="cvv"
	                                   placeholder="CVV" value="${card.getCvv()}" required>
	                        </div>
	                    </div>
	                    <hr class="mb-4">
	                    <div class="col-md-12 text-center">
	                        <button class="btn btn-primary btn-lg text-center" style="width: 30%;" type="submit">
	                            <spring:message code="submit"></spring:message>
	                        </button>
	                    </div>
	                </form>
	            </div>
	        </div>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>