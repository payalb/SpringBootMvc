<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title><spring:message code="userProfile"></spring:message></title>

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
						<spring:message code="userProfile"></spring:message>
					</li>
				</ol>
			</nav>

			<br>
			<main role="main" class="container">
				<div class="col-md-12">
					<form class="needs-validation" action="profile.do" method="post">
	
						<div class="mb-3">
						
							<spring:bind path="login.loginId">
								<input type="hidden" class="form-control" id="loginId" name="loginId"
									value="${login.getLoginId()}">
							</spring:bind>
							
							<spring:bind path="login.password">
								<input type="hidden" class="form-control" id="password" name="password"
									value="${login.getPassword()}">
							</spring:bind>
							
							<spring:bind path="login.role">
								<input type="hidden" class="form-control" id="role" name="role"
									value="${login.getRole()}">
							</spring:bind>
						
							<label for="email"><spring:message code="email"></spring:message></label>
							<spring:bind path="login.email">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="you@example.com" value="${login.getEmail()}" readonly>
							</spring:bind>
	
						</div>
	
						<div class="mb-3">
							<label for="name"><spring:message code="name"></spring:message></label>
							<spring:bind path="login.name">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="<spring:message code="name"></spring:message>" 
									value="${login.getName()}">
							</spring:bind>
						</div>
	
						<div class="d-block my-3">
							<spring:bind path="user.userId">
								<input type="hidden" class="form-control" id="userId" name="userId"
									value="${user.getUserId()}">
							</spring:bind>
						
							<label><spring:message code="gender"></spring:message></label> <br>
							<div class="form-check form-check-inline">
								<spring:bind path="user.gender">
									<input class="form-check-input" type="radio" name="gender"
										id="inlineRadio1" value="MALE"
										${user.getGender().toString()=='MALE'?'checked':''}
										required>
								</spring:bind>
								<label class="form-check-label" for="inlineRadio1">
									<spring:message code="male"></spring:message>
								</label>
							</div>
							<div class="form-check form-check-inline">
								<spring:bind path="user.gender">	
									<input class="form-check-input" type="radio" name="gender"
										id="inlineRadio2" value="FEMALE"
										${user.getGender().toString()=='FEMALE'?'checked':''}>
									<label class="form-check-label" for="inlineRadio2">
										<spring:message code="female"></spring:message>
									</label>
								</spring:bind>
							</div>
						</div>
	
						<div class="mb-3">
							<label for="mobile"><spring:message code="mobile"></spring:message></label> 
							<spring:bind path="user.mobile">
								<input type="text" class="form-control" id="mobile" name="mobile" 
									placeholder="<spring:message code="mobileNum"></spring:message>" 
									value="${user.getMobile()}">
							</spring:bind>
						</div>
	
						<button class="btn btn-primary btn-lg" style="width: 30%; text-align: center;"
							type="submit"><spring:message code="submit"></spring:message></button>
					</form>
				</div>
			</main>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>