$(document).ready(function () {
    $('#submitFormButton').click(function () {
        var username = $('#adminUsername').val();
        var adminRole = $('#adminRole').val();
        var mobileNumber = $('#mobileNumber').val();
        var password = $('#password').val();

        // Create an object with the collected values
        var formData = {
            adminUsername: username,
            adminRole: adminRole,
            mobileNumber: mobileNumber,
            Password: password,
        };

        $.ajax({
            type: 'POST',
            url: '/admin/v1/create-admin',
            data: formData,
            success: function (response) {
                // Check if the response is an error message
                if (response.toLowerCase().includes('error')) {
                    showError(response);
                } else {
                    showSuccess(response);
                }
            },
            error: function () {
                showError('An error occurred while creating the admin.');
            }
        });
        $('#exampleModalCenter').modal('show');
    });

    function showSuccess(message) {
        var messageContainer = $('#messageContainer');
        messageContainer.html('<div class="alert alert-success">' + message + '</div>');
    }

    function showError(message) {
        var messageContainer = $('#messageContainer');
        messageContainer.html('<div class="alert alert-danger">' + message + '</div>');
    }
});
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

/*db form js*/
$(document).ready(function(){

            $('#smartwizard').smartWizard({
                    selected: 0,
                    theme: 'arrows',
                    autoAdjustHeight:true,
                    transitionEffect:'fade',
                    showStepURLhash: false,
                 
            });
// Show Smart Wizard modal when "Followers" link is clicked
    $('#followers-link').click(function () {
        $('#exampleModal').modal('show');
    }); 
        });
        