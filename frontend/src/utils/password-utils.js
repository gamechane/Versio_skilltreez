import {InputFieldUtils} from "@/utils/input-field-utils.js";

const PasswordUtils = {
    showPasswordValidity(passwordInputField) {
        const regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&\\S]{6,30}$";

        if (passwordInputField.value.match(regex)) {
            passwordInputField.setCustomValidity("");
        } else {
            passwordInputField.setCustomValidity("The password must contain a minimum of 6 and a maximum of 30 characters without any whitespace, at least one uppercase letter, one lowercase letter, one number and one of the following special characters: @$!%*?&");
        }

        InputFieldUtils.showValidity(passwordInputField);
    },
    showRepeatPasswordMatch(passwordInputField, repeatPasswordInputField) {
        if (passwordInputField.value === repeatPasswordInputField.value) {
            repeatPasswordInputField.setCustomValidity("");
        } else {
            repeatPasswordInputField.setCustomValidity("The passwords don't match!");
        }

        InputFieldUtils.showValidity(repeatPasswordInputField);
    },
    showDifferentPassword(currentPasswordInputField, newPasswordInputField) {
        if (newPasswordInputField.value === currentPasswordInputField.value) {
            newPasswordInputField.setCustomValidity("New password cannot be the same as the current password!");
        } else {
            newPasswordInputField.setCustomValidity("");
        }

        InputFieldUtils.showValidity(newPasswordInputField);
    }
}

export { PasswordUtils };
