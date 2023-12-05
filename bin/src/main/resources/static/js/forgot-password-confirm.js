var password = document.getElementById("password")
  , confirm_password = document.getElementById("confirmPassword");

document.getElementById('signupLogo').src = "https://s3-us-west-2.amazonaws.com/shipsy-public-assets/shipsy/SHIPSY_LOGO_BIRD_BLUE.png";
enableSubmitButton();

function validatePassword() {
  if(password.value != confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
    return false;
  } else {
    confirm_password.setCustomValidity('');
    return true;
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

function enableSubmitButton() {
  document.getElementById('submitButton').disabled = false;
  document.getElementById('loader').style.display = 'none';
}

function disableSubmitButton() {
  document.getElementById('submitButton').disabled = true;
  document.getElementById('loader').style.display = 'unset';
}

function validateSignupForm() {
  var form = document.getElementById('signupForm');
  
  for(var i=0; i < form.elements.length; i++){
      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
        console.log('There are some required fields!');
        return false;
      }
    }
  
  if (!validatePassword()) {
    return false;
  }
  
  onSignup();
}

function onSignup() {
  var email = document.getElementById("email").value; // Get the email
  var newPassword = document.getElementById("password").value; // Get the new password

  // Prepare the data to send to the server
  var data = {
    emailData: email,
    newPassword: newPassword
  };
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    disableSubmitButton();

    if (this.readyState == 4) {
      if (this.status == 200) {
        console.log('Request successful');
        enableSubmitButton();
      } else {
        console.log('AJAX call failed!');
        setTimeout(function () {
          enableSubmitButton();
        }, 1000);
      }
    }
  };

  xhttp.open("POST", "/reset-password", true); // Use POST method
  xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8"); // Set the content type
  xhttp.send(JSON.stringify(data)); // Send the data as JSON

  return false; // Prevent the form from submitting normally
}