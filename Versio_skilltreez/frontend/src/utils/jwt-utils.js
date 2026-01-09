const JwtUtils = {
    parseJwt(token) {
        return JSON.parse(atob(token.split('.')[1]));
    },
    getRole(token) {
        return this.parseJwt(token).roles.trim().toUpperCase();
    },
    isGameMaster(token) {
        let isGameMaster = false;

        const role = this.getRole(token);
        if (role.includes("GAME_MASTER")) {
            isGameMaster = true;
        }

        return isGameMaster;
    }
}

export {JwtUtils}
