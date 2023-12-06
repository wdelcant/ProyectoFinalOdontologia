async function login() {
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  try {
    // Make a request to the server for authentication
    let valor = await checkAuthentication();

    if (!valor) {
      const response = await fetch('http://localhost:8080/authenticate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error('Authentication failed');
      }

      // Assuming the server responds with a JWT in the 'token' property
      const data = await response.json();
      const token = data.token;

      // Store the token securely (e.g., in a cookie or local storage)
      localStorage.setItem('jwtToken', token);

      // Display a success message or redirect to another page
      window.location.href = 'http://localhost:8080/view/index.html';
    }
  } catch (error) {
    console.error('Login error:', error);
    document.getElementById('result').innerText =
      'Login failed. Please check your credentials.';
  }
}


async function checkAuthentication() {
    // Authentication endpoint URL

  Swal.fire("Bienvenido a la aplicación de gestión de turnos");

    try{
        const response = await fetch('http://localhost:8080/checkAuthenticated', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        }).catch(error => {
            console.error('Authentication error:', error);
            return false;
        });

        if (!response.ok) {
            return false;
        }


    }catch(error){
        return false;
    }


    return true;
}
