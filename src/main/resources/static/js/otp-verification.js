const inputs = ["input1", "input2", "input3", "input4", "input5", "input6"];

inputs.map((id) => {
  const input = document.getElementById(id);
  addListener(input);
});

function addListener(input) {
  input.addEventListener("keyup", () => {
    const code = parseInt(input.value);
    if (code >= 0 && code <= 9) {
      const n = input.nextElementSibling;
      if (n) n.focus();
    } else {
      input.value = "";
    }

    const key = event.key; // const {key} = event; ES6+
    if (key === "Backspace" || key === "Delete") {
      const prev = input.previousElementSibling;
      if (prev) prev.focus();
    }

    // Fetch the session email
    const email = document.getElementById("emailInput").value;
    const otp = getOTP();

    // Check if both email and OTP are available
    if (email && otp.length === 6) {
      sendOTPVerificationRequest(email, otp);
    }
  });
}

function getOTP() {
  const otp = inputs.map((id) => document.getElementById(id).value).join('');
  return otp;
}

function sendOTPVerificationRequest(email, otp) {
  const xhr = new XMLHttpRequest();
  const url = `/otp-verification?digit1=${otp[0]}&digit2=${otp[1]}&digit3=${otp[2]}&digit4=${otp[3]}&digit5=${otp[4]}&digit6=${otp[5]}`;
  xhr.open("GET", url, true);

  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // Handle the response from the controller if needed
      console.log(xhr.responseText);
    }
  };

  xhr.send();
}