<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header class="section-header">
    <section class="header-main">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-5-24 col-sm-5 col-4">
                    <div class="brand-wrap">
                        <img class="logo" src="images/logo.png">
                        <h2 class="logo-text">YiBuy</h2>
                    </div> <!-- brand-wrap.// -->
                </div>
                <div class="col-lg-11-24 col-sm-12 order-3 order-lg-2">
                    <form id = "searchForm" action="productList">
                        <div class="input-group w-100">
                            <select class="custom-select" name="categoryName">
                                <option value="ELECTRONICS"><spring:message code="electronics"></spring:message></option>
                                <option value="TV"><spring:message code="television"></spring:message></option>
                                <option value="LAPTOP"><spring:message code="laptop"></spring:message></option>
                                <option value="PHONE"><spring:message code="phone"></spring:message></option>
                            </select>
                            <input type="text" class="form-control" style="width:60%;" name="keyword" 
                            	placeholder="<spring:message code="search"></spring:message>">

                            <div class="input-group-append">
                                <button class="btn btn-primary" type="submit">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form> <!-- search-wrap .end// -->
                </div> <!-- col.// -->
                <div class="col-lg-8-24 col-sm-7 col-8  order-2  order-lg-3">
                    <div class="d-flex justify-content-end">
                    	<div class="widget-header dropdown">
                            <a href="#" data-toggle="dropdown" data-offset="20,10">
                                <div class="icontext">
                                    <div class="icon-wrap icon-sm round border">
                                        <i class="fas fa-globe"></i>
                                    </div>
                                    <div class="text-wrap">
                                        <i class="fa fa-caret-down"></i>
                                    </div>
                                </div>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="?language=en">English</a>
                                <a class="dropdown-item" href="?language=cn">中文</a>
                            </div>
                        </div>
                    	<c:if test="${sessionScope.user==null}">
				    		<div class="widget-header">
							    <small class="title text-muted">
							    	<spring:message code="welcome"></spring:message> 
							    	<spring:message code="guest"></spring:message>!
							    </small>
							    <div> 
							    	<a href="login"><spring:message code="signin"></spring:message></a> 
							    	<span class="dark-transp"> | </span>
							        <a href="register"><spring:message code="register"></spring:message></a>
								</div>
							</div>
				      	</c:if>
				      	
                    	<c:if test="${sessionScope.user!=null}">
				    		<div class="widget-header dropdown">
	                            <a href="#" data-toggle="dropdown" data-offset="20,10">
	                                <div class="icontext">
	                                    <div class="icon-wrap"><i class="icon-sm round border fa fa-user"></i></div>
	                                    <div class="text-wrap">
	                                        <small><spring:message code="welcome"></spring:message></small>
	                                        <div>
	                                        	<spring:message code="myAccount"></spring:message>
	                                        	<i class="fa fa-caret-down"></i> 
	                                        </div>
	                                    </div>
	                                </div>
	                            </a>
	                            <div class="dropdown-menu dropdown-menu-right">
	                            	<a class="dropdown-item" href="profile"><spring:message code="userProfile"></spring:message></a>
	                                <a class="dropdown-item" href="addressListPage"><spring:message code="myAddress"></spring:message></a>
	                                <a class="dropdown-item" href="cardListPage"><spring:message code="myCard"></spring:message></a>
	                                <a class="dropdown-item" href="orderHistory"><spring:message code="myOrder"></spring:message></a>
	                                <a class="dropdown-item" href="userLogout.do"><spring:message code="logout"></spring:message></a>
	                            </div> <!--  dropdown-menu .// -->
	                        </div>  <!-- widget-header .// -->
				      	</c:if>

                        <a href="getCartItem" class="widget-header border-left pl-3 ml-3">
                            <div class="icontext">
                                <div class="icon-wrap icon-sm round border"><i class="fa fa-shopping-cart"></i></div>
                            </div>
                        </a>
                    </div> <!-- widgets-wrap.// -->
                </div> <!-- col.// -->
            </div> <!-- row.// -->
        </div> <!-- container.// -->
    </section> <!-- header-main .// -->
</header> <!-- section-header.// -->