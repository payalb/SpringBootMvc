<%@page import="org.springframework.web.context.annotation.SessionScope" session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><spring:message code="searchResult"></spring:message></title>
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
<section class="section-content padding-y border-top">
    <div class="container">
        <div class="row">
            <aside class="col-sm-3">
                <div class="card">
                    <header class="card-header bg-secondary white">
                        <i class="icon-menu"></i> <spring:message code="category"></spring:message>
                    </header>
                    <ul class="menu-category">
                        <li class="has-submenu">
                            <a href="productList?categoryName=ELECTRONICS">
                            	<spring:message code="electronics"></spring:message> & 
                            	<spring:message code="computers"></spring:message> 
                            	<i class="icon-arrow-right pull-right"></i>
                            </a>
                            <ul class="submenu">
                                <li> 
                                	<a href="productList?categoryName=TV">
                                		<spring:message code="television"></spring:message>
                                	</a>
                                </li>
                                <li> 
                               		<a href="productList?categoryName=LAPTOP">
                               			<spring:message code="laptop"></spring:message>
                               		</a>
                               	</li>
                                <li> 
                                	<a href="productList?categoryName=PHONE">
                                		<spring:message code="phone"></spring:message> 
                                	</a>
                                </li>
                            </ul>
                        </li>
                        <li class="has-submenu">
                            <a href="#">
                            	<spring:message code="food"></spring:message> & <spring:message code="beverage"></spring:message>
                            	<i class="icon-arrow-right pull-right"></i>
                            </a>
                            <ul class="submenu">
                                <li> <a href="#"><spring:message code="food"></spring:message> </a></li>
                                <li> <a href="#"><spring:message code="beverage"></spring:message> </a></li>
                            </ul>
                        </li>
                        <li class="has-submenu">
                            <a href="#">
                            	<spring:message code="clothing"></spring:message> & <spring:message code="shoes"></spring:message> 
                            	<i class="icon-arrow-right pull-right"></i>
                            </a>
                            <ul class="submenu">
                                <li> <a href="#"><spring:message code="men"></spring:message> </a></li>
                                <li> <a href="#"><spring:message code="women"></spring:message> </a></li>
                            </ul>
                        </li>
                        <li class="has-submenu">
                            <a href="#">
                            	<spring:message code="books"></spring:message> & <spring:message code="audible"></spring:message>  
                            	<i class="icon-arrow-right pull-right"></i>
                            </a>
                            <ul class="submenu">
                                <li> <a href="#"><spring:message code="books"></spring:message> </a></li>
                                <li> <a href="#"><spring:message code="audible"></spring:message>  </a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><spring:message code="moreCategory"></spring:message></a>
                        </li>
                    </ul>
                </div> <!-- card.// -->
            </aside> <!-- col.// -->

            <main class="col-sm-9">
            	<nav class="mb-3">
					<ol class="breadcrumb">
						<li class="breadcrumb-item">
							<a href="index"><spring:message code="home"></spring:message></a>
						</li>
						<li class="breadcrumb-item active" aria-current="page">
							<spring:message code="searchResult"></spring:message>
						</li>
					</ol>
				</nav>
				
                <c:if test="${productList == null || productList.size() == 0}">
                	<article class="card card-product">
                  		<div class="card-body">
	                   		<div class="alert alert-warning" role="alert">
								<spring:message code="noMatchFound"></spring:message>.
							</div>
						</div>
					</article>
               	</c:if>
                  	
                <c:forEach items="${productList}" var="product">
                    <article class="card card-product">
                    	<div class="card-body">
	                        <div class="row">
	                            <aside class="col-sm-3">
	                                <div class="img-wrap" style="height: 100px;"><img src="${product.getImagePath()}"></div>
	                            </aside> <!-- col.// -->
	                            <article class="col-sm-6">
	                                <h4 class="title"> ${product.getProductName()}</h4>
	                                <br>
	                                <dl class="dlist-align">
	                                    <dt>Brand</dt>
	                                    <dd>${product.getBrand()}</dd>
	                                </dl>  <!-- item-property-hor .// -->
	                                <dl class="dlist-align">
	                                    <dt>Stock</dt>
	                                    <dd>${product.getStock()}</dd>
	                                </dl>  <!-- item-property-hor .// -->
	
	                            </article> <!-- col.// -->
	                            <aside class="col-sm-3 border-left">
	                                <div class="action-wrap">
	                                    <div class="price-wrap h4">
	                                        <span class="price">$ ${product.getPrice()}</span>
	                                    </div> <!-- info-price-detail // -->
	                                    <br>
	                                    <p>
	                                        <a href="addCartItem?id=${product.getProductId()}&quantity=1" class="btn btn-primary"> 
	                                        	<spring:message code="buyNow"></spring:message>
	                                        </a>
	                                        <a href="getProduct?id=${product.getProductId()}" class="btn btn-secondary"> 
	                                        	<spring:message code="details"></spring:message>
	                                        </a>
	                                    </p>
	                                </div> <!-- action-wrap.// -->
	                            </aside> <!-- col.// -->
	                        </div> <!-- row.// -->
                    	</div> <!-- card-body .// -->
               		</article> <!-- card product .// -->
                </c:forEach>
                   
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                    	<li class="page-item ${page == 1 ? 'disabled' : ''}">
                            <a class="page-link" href="productList?categoryName=${categoryName}&keyword=${keyword}&page=${page-1}" tabindex="-1">
                            	<spring:message code="previousPage"></spring:message>
                            </a>
                        </li>
                    	<%
                    		int totalPage = (int) request.getSession().getAttribute("totalPage");
                    		for (int i = 1; i <= totalPage; i++) {
                    			%>
                    			<li class="page-item ${page == i ? 'disabled' : ''}">
                    				<a class="page-link" href="productList?categoryName=${categoryName}&keyword=${keyword}&page=<%=i %>"><%=i %></a>
                    			</li>
                    			<%
                    		}
                    	%>
                        <li class="page-item ${page == sessionScope.totalPage ? 'disabled' : ''}">
                            <a class="page-link" href="productList?categoryName=${categoryName}&keyword=${keyword}&page=${page+1}">
                            	<spring:message code="nextPage"></spring:message>
							</a>
                        </li>
                    </ul>
                </nav>
            </main> <!-- col.// -->
        </div>

    </div> <!-- container .//  -->
</section>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>