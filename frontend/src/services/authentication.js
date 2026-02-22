import {DataManager} from "@/services/store.js";
import {ApiService} from "@/services/api.js";
import router from "@/router/index.js";

const AuthenticationService = {
    async login(accountType, usernameEmailAddress, password) {
        let loginRequestBody = {
            usernameEmailAddress: usernameEmailAddress,
            password: password,
        };

        const result = await ApiService.makeApiCall("authenticate", "POST", undefined, loginRequestBody);

        if (result.token)  {
            DataManager.token.set(result.token);
            await router.push("/dashboard");
        }

        return false;
    },
    /**
     * @param {string} pin
     * @return {Promise<false | void>}
     */
    async loginGamePin(pin) {
        const result = await ApiService.makeApiCall(`student/skill-tree/login/${pin}`, "GET", undefined, undefined);
        // Temporary solution.
        // This whole code block below should be replaced with checking the status code of the response and the code should depend on that.
        // But this is not possible currently because the backend always returns a response with status code 200.
        if (result.id) {
            DataManager.gamePin.set(result);
            await router.push(`/skill-tree/${pin}`);
            // Script on skill tree page should check if pin is stored.
        } else {
            return false;
        }
    },
    async logout() {
        DataManager.token.clear();
        await router.push("/login");
    },
    /**
     * @return {boolean}
     */
    authenticated() {
        return !!DataManager.token.get();
    }
};

export { AuthenticationService };
