<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title><spring:message code="myAddress"></spring:message></title>
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
						<spring:message code="addressList"></spring:message>
					</li>
				</ol>
			</nav>

			<br>
			<div class="row">
	            <main class="col-sm-12">
					<c:if test="${errorMsg != null}">
                   		<div class="alert alert-warning" role="alert">${errorMsg}</div>
                   	</c:if>
	                <div class="card">
	                    <table class="table table-hover shopping-cart-wrap" style="margin-bottom: 0;">
	                    	<c:if test="${errorMsg == null}">
	                    		<thead class="text-muted">
			                        <tr>
			                            <th scope="col">
			                            	<spring:message code="name"></spring:message>, 
			                            	<spring:message code="address"></spring:message>
			                            </th>
			                            <th scope="col" width="250">
			                            	<spring:message code="city"></spring:message>, 
			                            	<spring:message code="state"></spring:message>
			                            </th>
			                            <th scope="col" width="150"><spring:message code="country"></spring:message></th>
			                            <th scope="col" class="text-right" width="300"><spring:message code="action"></spring:message></th>
			                        </tr>
		                        </thead>
		            		</c:if>
	                        <tbody>
	                        	<c:if test="${errorMsg == null}">
	                        	<c:forEach items="${addressSet}" var="address">
	                            	<tr>
		                                <td>
		                                    <figure class="media">
		                                        <figcaption class="media-body">
		                                            <h6 class="title text-truncate">${address.getFullName()}</h6>
		                                            <dl class="dlist-inline small">
		                                                <dd>${address.getStreet()}</dd>
		                                            </dl>
		                                            <dl class="dlist-inline small">
		                                                <dd>${address.getStreetTwo()}</dd>
		                                            </dl>
		                                        </figcaption>
		                                    </figure>
		                                </td>
		                                <td>
		                                    <dl class="dlist-inline small">
		                                        <dd>${address.getCity()}</dd>
		                                    </dl>
		                                    <dl class="dlist-inline small">
		                                        <dd>${address.getState()}, ${address.getZip()}</dd>
		                                    </dl>
		                                </td>
		                                <td>
		                                    <div class="price-wrap">
		                                        <h6 class="title text-truncate">${address.getCountry()}</h6>
		                                    </div> <!-- price-wrap .// -->
		                                </td>
		                                <td class="text-right">
		                                    <a href="editAddressPage?id=${address.getAddressId()}" class="btn btn-outline-primary">
		                                    	<spring:message code="edit"></spring:message>
		                                    </a>
		                                    <a href="deleteAddress?id=${address.getAddressId()}" class="btn btn-outline-danger">
		                                    	<spring:message code="remove"></spring:message>
		                                    </a>
		                                </td>
		                            </tr>
	                            </c:forEach>
	                            </c:if>
	                            <tr>
	                                <td class="text-center" colspan="4">
	                                    <a href="addressPage" class="btn btn-outline-success">
	                                    	<spring:message code="addNewAddress"></spring:message>
	                                    </a>
	                                </td>
	                            </tr>
                        	</tbody>
	                    </table>
	                </div> <!-- card.// -->
	
	            </main> <!-- col.// -->
	        </div>
		</div>
	</section>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>