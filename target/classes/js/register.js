$( document ).ready(function() {
	$.validate({
		modules : 'date, security'
	});
	
	$('#email').on('focusout', function () {
		console.log('enter');
		if($(this).val().trim()) {
			$.get({
				url: "existEmail?email="+$(this).val().trim(),
				success: function(data){
					console.log(data);
					if(data=='true') {
						$('#emailalert').show();
					} else {
						$('#emailalert').hide();
					}
				}
			});
		}
	});
});