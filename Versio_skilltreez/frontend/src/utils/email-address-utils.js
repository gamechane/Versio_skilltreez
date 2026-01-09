import {AvailabilityChecker} from "@/services/availability.js";
import {InputFieldUtils} from "@/utils/input-field-utils.js";

const EmailAddressUtils = {
    async showEmailAddressValidity(emailAddressInputField) {
        const emailAddressAvailability = await AvailabilityChecker.checkEmailAddressAvailability(emailAddressInputField.value);

        if (emailAddressAvailability) {
            emailAddressInputField.setCustomValidity("");
        } else {
            emailAddressInputField.setCustomValidity(`There already exists an account with this e-mail address: ${emailAddressInputField.value}`);
        }

        InputFieldUtils.showValidity(emailAddressInputField);
    }
}

export {EmailAddressUtils};
