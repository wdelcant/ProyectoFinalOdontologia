document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username,
            password: password,
        }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            // Inicio de sesión exitoso
            // Guardar el token en el almacenamiento local
            localStorage.setItem('token', data.token);
            // Redirigir al usuario a la página principal o mostrar un mensaje de éxito
        } else {
            // Inicio de sesión fallido
            // Mostrar un mensaje de error al usuario
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
});