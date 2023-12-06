// Function to authenticate and get JWT token
export function authenticateAndGetToken(username, password) {
    // Authentication endpoint URL

    const authEndpoint = 'http://localhost:8080/authenticate';

    // Request payload (can include username, password, etc.)
    const payload = {
        username: username,
        password: password,
    };

    // Request configuration
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
    };

    // Make the authentication request
    return fetch(authEndpoint, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Authentication failed');
            }
            return response.json();
        })
        .then(data => {
            // Assuming the token is in the 'token' property of the response
            const jwtToken = data.token;
            console.log('JWT Token:', jwtToken);

            // You can now use the token for subsequent requests or store it securely
            return jwtToken;
        })
        .catch(error => {
            console.error('Authentication error:', error);
        });
}


export function checkAuthentication() {
    // Authentication endpoint URL


    // Request configuration
    const requestOptions = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },

    };

    // Make the authentication request
    return fetch(authEndpoint, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error('Authentication failed');
            }
            return response.json();
        })
        .then(data => {
            // Assuming the token is in the 'token' property of the response
            const jwtToken = data.token;
            console.log('JWT Token:', jwtToken);

            // You can now use the token for subsequent requests or store it securely
            return jwtToken;
        })
        .catch(error => {
            console.error('Authentication error:', error);
        });
}



