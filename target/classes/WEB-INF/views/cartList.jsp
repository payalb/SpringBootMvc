<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" import="com.dto.CartItem, java.util.List"%>
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
<section class="section-content padding-y border-top">
    <div class="container">
        <nav class="mb-3">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Cart</li>
            </ol>
        </nav>

        <div class="row">
            <main class="col-sm-9">

                <div class="card">
                    <table class="table table-hover shopping-cart-wrap">
                        <thead class="text-muted">
                        <tr>
                            <th scope="col">Product</th>
                            <th scope="col" width="120">Quantity</th>
                            <th scope="col" width="160">Price</th>
                            <th scope="col" class="text-right" width="120">Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        	<% 
                        		Float total = 0f;
                        		List<CartItem> list = (List<CartItem>) request.getAttribute("cartList");
                        		for (CartItem cartItem: list) {
                            		total += cartItem.getProduct().getPrice() * cartItem.getAmount(); 
                        	%>
                            <tr>
                                <td>
                                    <figure class="media">
                                        <div class="img-wrap"><img src="<%=cartItem.getProduct().getImagePath() %>" class="img-thumbnail img-sm"></div>
                                        <figcaption class="media-body">
                                            <h6 class="title text-truncate"><%=cartItem.getProduct().getProductName()%></h6>
                                            <dl class="dlist-inline small">
                                                <dt>Brand:</dt>
                                                <dd><%=cartItem.getProduct().getBrand()%></dd>
                                            </dl>
                                        </figcaption>
                                    </figure>
                                </td>
                                <td>
                                    <h6 class="text-truncate"></h6>
                                    <input type="number" name="productQuantity" id="productQuantity<%=cartItem.getProduct().getProductId() %>" value="<%=cartItem.getAmount() %>"
                                            	class="form-control form-control-sm" min="1" max="<%=cartItem.getProduct().getStock() %>" step="1" 
                                            	onchange="quantityChange(this.value, <%=cartItem.getProduct().getProductId() %>)"> 
                                </td>
                                <td>
                                    <div class="price-wrap">
                                        <var class="price">USD <%=cartItem.getProduct().getPrice()%></var>
                                        <small class="text-muted">(<%=cartItem.getProduct().getStock()%> Left)</small>
                                    </div> <!-- price-wrap .// -->
                                </td>
                                <td class="text-right">
                                    <a href="deleteCartItem?id=<%=cartItem.getProduct().getProductId()%>" class="btn btn-outline-danger"> × Remove</a>
                                </td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div> <!-- card.// -->

            </main> <!-- col.// -->
            <aside class="col-sm-3">
                <h5 class="text-uppercase">Order Summary</h5>
                <hr>
                <dl class="dlist-align">
                    <dt>Total price:</dt>
                    <dd class="text-right">USD <%=total %></dd>
                </dl>
                <dl class="dlist-align">
                    <dt>Shipping:</dt>
                    <dd class="text-right">USD 5</dd>
                </dl>
                <hr>
                <dl class="dlist-align h4">
                    <dt>Total:</dt>
                    <dd class="text-right"><strong>USD <%=total + 5 %></strong></dd>
                </dl>
                <hr>
               	<a href="checkoutPage" class="btn btn-primary btn-block">Check Out</a>
            </aside> <!-- col.// -->
        </div>

    </div> <!-- container .//  -->
</section>

<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">
	function quantityChange(value, id) {
		if (value == 0) {
			var form = $('<form action="deleteCartItem">' + 
				'<input type="hidden" name="id" value="' + id + '">' +
			    '<input type="hidden" name="quantity" value="' + value + '">' +
			    '</form>');
		} else {
			var form = $('<form action="updateCartItem">' + 
			    '<input type="hidden" name="id" value="' + id + '">' +
			    '<input type="hidden" name="quantity" value="' + value + '">' +
			    '</form>');
		}
		$(document.body).append(form);
		form.submit();
	}
</script>
</body>
</html>