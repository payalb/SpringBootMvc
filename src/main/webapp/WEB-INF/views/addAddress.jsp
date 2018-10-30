<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title><spring:message code="addAddress"></spring:message></title>

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
						<spring:message code="addAddress"></spring:message>
					</li>
				</ol>
			</nav>

			<br>
			<div class="row" >
	            <div class="col-md-12 order-md-1">
	                <h4 class="mb-3"><spring:message code="shipAddress"></spring:message></h4>
	                <form class="needs-validation" action="addAddress">
	                    <div class="row">
	                        <div class="col-md-12 mb-3">
	                            <label for="fullName"><spring:message code="name"></spring:message></label>
	                            <input type="text" class="form-control" id="fullName" name="fullName"
	                                   placeholder="<spring:message code="name"></spring:message>" value="" required>
	                        </div>
	                    </div>
	
	                    <div class="mb-3">
	                        <label for="street"><spring:message code="address"></spring:message></label>
	                        <input type="text" class="form-control" id="street" name="street" 
	                        	placeholder="<spring:message code="address"></spring:message>" required>
	                    </div>
	
	                    <div class="mb-3">
	                        <label for="streetTwo">
	                        	<spring:message code="addAddress"></spring:message> 2 
	                        	<span class="text-muted">(<spring:message code="optional"></spring:message>)</span>
	                        </label>
	                        <input type="text" class="form-control" id="streetTwo" name="streetTwo" 
	                        	placeholder="<spring:message code="apartment"></spring:message> <spring:message code="or"></spring:message> <spring:message code="suite"></spring:message>">
	                    </div>
	
	                    <div class="row">
	                        <div class="col-md-3 mb-3">
	                            <label for="city"><spring:message code="city"></spring:message></label>
	                            <input type="text" class="form-control" id="city" name="city" 
	                            	placeholder="<spring:message code="city"></spring:message>" required>
	                        </div>
	                        <div class="col-md-3 mb-3">
	                            <label for="state"><spring:message code="state"></spring:message></label>
	                            <input type="text" class="form-control" id="state" name="state" 
	                            	placeholder="<spring:message code="state"></spring:message>" required>
	                        </div>
	                        <div class="col-md-3 mb-3">
	                            <label for="zip">Zip</label>
	                            <input type="text" class="form-control" id="zip" name="zip" 
	                            	placeholder="Zip <spring:message code="code"></spring:message>" required>
	                        </div>
	                        <div class="col-md-3 mb-3">
	                            <label for="country"><spring:message code="country"></spring:message></label>
	                            <input type="text" class="form-control" id="country" name="country" 
	                            	placeholder="<spring:message code="country"></spring:message>" required>
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