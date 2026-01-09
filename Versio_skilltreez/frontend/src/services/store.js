// simple data manager for managing global state
export const DataManager = {
    token: {
        set(token) { localStorage.setItem("token", token); },
        get() { return localStorage.getItem("token"); },
        clear() { return localStorage.removeItem("token"); }
    },
    gamePin: {
        set(gamePin) { localStorage.setItem("gamePin", JSON.stringify(gamePin)); },
        get() { return JSON.parse(localStorage.getItem("gamePin")); },
        clear() { return localStorage.removeItem("gamePin"); }
    }
};