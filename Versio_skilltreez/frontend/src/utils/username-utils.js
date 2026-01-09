import {AvailabilityChecker} from "@/services/availability.js";
import {InputFieldUtils} from "@/utils/input-field-utils.js";

const UsernameUtils = {
    async showUsernameValidity(usernameInputField) {
        // Regex: 6-30 chars, a-z, A-Z, 0-9, . and _
        const regex = "^[A-Za-z0-9._]{6,30}$";
        console.log(`Checking username validity for: '${usernameInputField.value}' against regex: ${regex}`);

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
