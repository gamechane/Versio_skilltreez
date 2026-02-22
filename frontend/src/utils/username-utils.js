import {AvailabilityChecker} from "@/services/availability.js";
import {InputFieldUtils} from "@/utils/input-field-utils.js";

const UsernameUtils = {
    async showUsernameValidity(usernameInputField) {
        const regex = "^[A-Za-z\\d._]{6,30}$";

        if (usernameInputField.value.match(regex)) {
            const usernameAvailability = await AvailabilityChecker.checkUsernameAvailability(usernameInputField.value);

            if (usernameAvailability) {
                usernameInputField.setCustomValidity("");
            } else {
                usernameInputField.setCustomValidity(`There already exists an account with this username: ${usernameInputField.value}`);
            }
        } else {
            usernameInputField.setCustomValidity("The username must contain a minimum of 6 and a maximum of 30 characters without any whitespace. Only the following special characters are allowed: ._");
        }

        InputFieldUtils.showValidity(usernameInputField);
    }
}

export {UsernameUtils}
