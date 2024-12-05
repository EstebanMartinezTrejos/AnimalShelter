document.addEventListener('DOMContentLoaded', () => {
    // Menú móvil
    const menuToggle = document.querySelector('.menu-toggle');
    const navLinks = document.querySelector('.nav-links');

    menuToggle.addEventListener('click', () => {
        navLinks.classList.toggle('show');
    });

    // Manejo del formulario de inicio de sesión
    const loginForm = document.getElementById('login-form');
    const errorMessage = document.getElementById('error-message');

    loginForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        if (username === 'admin123' && password === 'AnimalShelter') {
            // Inicio de sesión exitoso
            errorMessage.textContent = '';
            // Redirigir al perfil del administrador
            window.location.href = 'admin-profile.html';
        } else {
            // Inicio de sesión fallido
            errorMessage.textContent = 'Usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.';
        }
    });
});