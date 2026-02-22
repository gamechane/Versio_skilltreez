import {ApiService} from "@/services/api.js";
import {AuthenticationService} from "@/services/authentication.js";
import {JwtUtils} from "@/utils/jwt-utils.js";
import {DataManager} from "@/services/store.js";

const UserService = {
    async register(accountType, username, emailAddress, password) {
        let registerRequestBody = {
            username: username.toLowerCase(),
            emailAddress: emailAddress.toLowerCase(),
            password: password,
            role: accountType,
        };

        const registerResult = await ApiService.makeApiCall("user/register", "POST", undefined, registerRequestBody);

        if (registerResult.id) {
            await AuthenticationService.login(accountType, username, password);
        }
    },
    async editPassword(currentPassword, newPassword) {
        let editPasswordRequestBody = {
            username: JwtUtils.parseJwt(DataManager.token.get()).sub,
            currentPassword: currentPassword,
            newPassword: newPassword,
        }

        await ApiService.makeApiCall("password", "PATCH", DataManager.token.get(), editPasswordRequestBody);
    }
}

export {UserService}
