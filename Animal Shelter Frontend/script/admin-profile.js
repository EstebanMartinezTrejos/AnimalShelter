document.addEventListener('DOMContentLoaded', () => {
    // Menú móvil
    const menuToggle = document.querySelector('.menu-toggle');
    const navLinks = document.querySelector('.nav-links');

    menuToggle.addEventListener('click', () => {
        navLinks.classList.toggle('show');
    });

    // Manejo de botones del perfil de administrador
    const editEventsBtn = document.getElementById('edit-events-btn');
    const addEventBtn = document.getElementById('add-event-btn');
    const viewDonationsBtn = document.getElementById('view-donations-btn');
    const addAnimalBtn = document.getElementById('add-animal-btn');
    const editAnimalsBtn = document.getElementById('edit-animals-btn');
    const viewVolunteersBtn = document.getElementById('view-volunteers-btn');
    const logoutBtn = document.getElementById('logout-btn');

    editEventsBtn.addEventListener('click', () => {
        alert('Funcionalidad de editar eventos en desarrollo');
    });

    addEventBtn.addEventListener('click', () => {
        alert('Funcionalidad de agregar evento en desarrollo');
    });

    viewDonationsBtn.addEventListener('click', () => {
        alert('Funcionalidad de ver reporte de donaciones en desarrollo');
    });

    addAnimalBtn.addEventListener('click', () => {
        alert('Funcionalidad de agregar animal en desarrollo');
    });

    editAnimalsBtn.addEventListener('click', () => {
        alert('Funcionalidad de editar animales en desarrollo');
    });

    viewVolunteersBtn.addEventListener('click', () => {
        alert('Funcionalidad de ver voluntarios en desarrollo');
    });

    logoutBtn.addEventListener('click', () => {
        alert('Cerrando sesión...');
        window.location.href = 'index.html';
    });
});