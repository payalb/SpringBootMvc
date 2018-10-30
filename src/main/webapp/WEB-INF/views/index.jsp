<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>YiBuy</title>

    <!-- Bootstrap 4 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Font awesome 5 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.css" />

    <link href="<c:url value="/css/uikit.css" />" rel="stylesheet" type="text/css">
</head>

<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<section class="section-main padding-y-sm border-top">
        <div class="container">
            <div class="card">
                <div class="card-body">
                    <div class="row row-sm">
                        <aside class="col-md-3">
                            <h5 class="text-uppercase"><spring:message code="category"></spring:message></h5>
                            <ul class="menu-category">
                                <li class="has-submenu">
                                    <a href="productList?categoryName=ELECTRONICS">
                                    	<spring:message code="electronics"></spring:message> 
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
                                        <li> <a href="#"><spring:message code="audible"></spring:message> </a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#"><spring:message code="moreCategory"></spring:message></a>
                                </li>
                            </ul>
                        </aside> <!-- col.// -->

                        <div class="col-md-9">
                            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner" role="listbox">
                                    <div class="carousel-item active">
                                        <img class="d-block img-fluid" src="images/banners/banner1.png" alt="First slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block img-fluid" src="images/banners/banner2.png" alt="Second slide">
                                    </div>
                                    <div class="carousel-item">
                                        <img class="d-block img-fluid" src="images/banners/banner3.png" alt="Third slide">
                                    </div>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only"><spring:message code="previousPage"></spring:message></span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only"><spring:message code="nextPage"></spring:message></span>
                                </a>
                            </div>

                        </div> <!-- col.// -->
                    </div> <!-- row.// -->
                </div> <!-- card-body .// -->
            </div> <!-- card.// -->

        </div> <!-- container .//  -->
    </section>

    <section class="section-content padding-y-sm">

        <div class="container">
            <header class="section-heading">
                <h4 class="title-section"><spring:message code="newProduct"></spring:message></h4>
            </header>

            <%-- <div class="card">
                <div class="row text-center">
                    <div class="col-md-12">
                        <ul class="row no-gutters border-cols">
                        	<c:forEach items="${productList}" var="product">
	                            <li class="col-6 col-md-3">
	                                <a href="getProduct?id=${product.getProductId()}" class="itembox">
	                                    <div class="card-body">
	                                        <img claass="img-sm" src="${product.getImagePath() }">
	                                        <p class="word-limit">${product.getProductName()} </p>
	                                        <p class="word-limit">$${product.getPrice()} </p>
	                                    </div>
	                                </a>
	                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div> --%>
            <div class="row-sm">
	            <c:forEach items="${productList}" var="product">
		            <div class="col-md-3 col-sm-6">
						<figure class="card card-product">
							<div class="img-wrap"> <img src="${product.getImagePath()}"></div>
							<figcaption class="info-wrap">
								<a href="getProduct?id=${product.getProductId()}" class="title">
									${product.getProductName()}
								</a>
								<div class="price-wrap">
									<span class="price-new">$${product.getPrice()}</span>
								</div> <!-- price-wrap.// -->
							</figcaption>
						</figure> <!-- card // -->
					</div> <!-- col // -->
	            </c:forEach>
	    	</div>
        </div>
    </section>

    <!-- <section class="section-content padding-y-sm bg">

        <div class="container">
            <header class="section-heading heading-line">
                <h4 class="title-section bg">Food & Beverage</h4>
            </header>

            <div class="card">
                Page Features
                <div class="row text-center">

                    <div class="col-md-12">
                        <ul class="row no-gutters border-cols">
                            <li class="col-6 col-md-3">
                                <a href="#" class="itembox">
                                    <div class="card-body">
                                        <p class="word-limit">Home and kitchen electronic  stuff collection  </p>
                                        <img class="img-sm" src="images/items/1.jpg">
                                    </div>
                                </a>
                            </li>
                            <li class="col-6 col-md-3">
                                <a href="#" class="itembox">
                                    <div class="card-body">
                                        <p class="word-limit">Kitchen equipments collection</p>
                                        <img class="img-sm" src="images/items/2.jpg">
                                    </div>
                                </a>
                            </li>
                            <li class="col-6 col-md-3">
                                <a href="#" class="itembox">
                                    <div class="card-body">
                                        <p class="word-limit">Accessiries and other good items</p>
                                        <img class="img-sm" src="images/items/3.jpg">
                                    </div>
                                </a>
                            </li>
                            <li class="col-6 col-md-3">
                                <a href="#" class="itembox">
                                    <div class="card-body">
                                        <p class="word-limit">Techs, Electronics</p>
                                        <img class="img-sm" src="images/items/4.jpg">
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div> container .// 
    </section> -->
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
