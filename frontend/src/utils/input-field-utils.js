const InputFieldUtils = {
    showValidity(inputField) {
        if (inputField.checkValidity()) {
            inputField.classList.remove("invalid");
            inputField.classList.add("valid");
        } else {
            inputField.classList.remove("valid");
            inputField.classList.add("invalid");
        }
    }
}

export {InputFieldUtils};
