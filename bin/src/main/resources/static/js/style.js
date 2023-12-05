// Automatically hide the alert message after 3 seconds
	setTimeout(function() {
		var alertElement = document.querySelector('.alert');
		if (alertElement) {
			alertElement.style.display = 'none';
		}
	}, 3000); // 3000 milliseconds = 3 seconds
	

/* password visibility script */
$(document).ready(function() {
    $("#show_hide_password a").on('click', function(event) {
        event.preventDefault();
        if($('#show_hide_password input').attr("type") == "text"){
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass( "fa-eye-slash" );
            $('#show_hide_password i').removeClass( "fa-eye" );
        }else if($('#show_hide_password input').attr("type") == "password"){
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass( "fa-eye-slash" );
            $('#show_hide_password i').addClass( "fa-eye" );
        }
    });
});

/* forget password script */
   