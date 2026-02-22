import {ApiService} from "@/services/api.js";

const AvailabilityChecker = {
    async checkUsernameAvailability(username) {
        return await ApiService.makeApiCall(`user/username/availability/${username}`);
    },
    async checkEmailAddressAvailability(emailAddress) {
        return await ApiService.makeApiCall(`user/email-address/availability/${emailAddress}`);
    }
};

export { AvailabilityChecker };
