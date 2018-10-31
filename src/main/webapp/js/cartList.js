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