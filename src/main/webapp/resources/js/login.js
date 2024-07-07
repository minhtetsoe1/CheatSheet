
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('loginForm');
    const usernameInput = document.getElementById('loginname');
    const passwordInput = document.getElementById('loginpassword');
    const priceInput = document.getElementById('price');
    const usernameError = document.getElementById('loginnameError');
    const passwordError = document.getElementById('loginpasswordError');
    const priceError = document.getElementById('priceError');
       function validatePrice() {
        if (priceInput.value.trim() === '') {
            priceInput.classList.add('invalid');
            priceError.textContent = 'Please enter price.';
            return false;
        } else {
            priceInput.classList.remove('invalid');
            priceError.textContent = '';
            return true;
        }
    }
    
    
    function validateUsername() {
        if (usernameInput.value.trim() === '') {
            usernameInput.classList.add('invalid');
            usernameError.textContent = 'Please enter your username.';
            return false;
        } else {
            usernameInput.classList.remove('invalid');
            usernameError.textContent = '';
            return true;
        }
    }

    function validatePassword() {
        if (passwordInput.value.trim() === '') {
            passwordInput.classList.add('invalid');
            passwordError.textContent = 'Please enter your password.';
            return false;
        } else {
            passwordInput.classList.remove('invalid');
            passwordError.textContent = '';
            return true;
        }
    }

    function validateForm() {
        return validateUsername() && validatePassword()&& validatePrice();
    }

    // Blur event listeners
    usernameInput.addEventListener('blur', validateUsername);
    passwordInput.addEventListener('blur', validatePassword);
    priceInput.addEventListener('blur', validatePrice);
    // Focus event listeners to clear error messages
    usernameInput.addEventListener('focus', function () {
        usernameInput.classList.remove('invalid');
        usernameError.textContent = '';
    });

    passwordInput.addEventListener('focus', function () {
        passwordInput.classList.remove('invalid');
        passwordError.textContent = '';
    });
    
    priceInput.addEventListener('focus', function () {
        priceInput.classList.remove('invalid');
        priceError.textContent = '';
    });

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the form from submitting
        if (validateForm()) {
            // If validation passes, you can submit the form
            // You might want to do additional checks here before submitting
            form.submit();
        }
    });
});
