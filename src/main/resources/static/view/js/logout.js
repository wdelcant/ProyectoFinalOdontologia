// logout.js
window.logout = function() {
    // Borrar el token del localStorage
    localStorage.removeItem('jwtToken');

    // Redirigir al usuario a la página de inicio de sesión
    window.location.href = 'login.html';
}