
$(document).ready(function() {
	$(".forgot a").click(function(e) {
		e.preventDefault(); // Prevent the link from navigating
		$("#login-form")[0].reset(); // Reset the form
	});
});
