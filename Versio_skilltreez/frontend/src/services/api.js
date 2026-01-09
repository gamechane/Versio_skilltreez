// determines the api server based on the DEV env variable or VITE_API_URL
const API_URL = import.meta.env.VITE_API_URL || (import.meta.env.DEV ? "http://localhost:8080" : window.location.origin);

const ApiService = {
    baseUrl: API_URL,
    /**
     * @param {string} endpoint
     * @param {string} method http method
     * @param {string | undefined} token optional
     * @param {object | array | undefined} body optional
     * @returns {Promise<object | array>}
     */
    async makeApiCall(endpoint, method, token, body) {
        return await fetch(`${this.baseUrl}/api/${endpoint}`, {
            method,
            headers: {
                // if token is defined add Authorization header
                ...( token && { "Authorization": `Bearer ${token}` } ),
                // if body is defined add content header
                ...( body && { "Content-Type": "application/json" } )
            },
            // if body is defined add body
            ...( body && { body: JSON.stringify(body) } )
        }).then(response => response.json());
    },
};

export { ApiService };
