document.addEventListener('DOMContentLoaded', () => {
    // Menú móvil
    const menuToggle = document.querySelector('.menu-toggle');
    const navLinks = document.querySelector('.nav-links');

    menuToggle.addEventListener('click', () => {
        navLinks.classList.toggle('show');
    });

    // Animaciones al hacer scroll
    const animatedElements = document.querySelectorAll('.event-card, .volunteer');

    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
                observer.unobserve(entry.target);
            }
        });
    }, { threshold: 0.1 });

    animatedElements.forEach(element => {
        observer.observe(element);
    });

    // Efecto hover en las imágenes de eventos
    const eventImages = document.querySelectorAll('.event-image');

    eventImages.forEach(image => {
        image.addEventListener('mouseover', () => {
            image.style.transform = 'scale(1.05)';
            image.style.transition = 'transform 0.3s ease';
        });

        image.addEventListener('mouseout', () => {
            image.style.transform = 'scale(1)';
        });
    });

    // Contador regresivo para el evento destacado
    const featuredEventDate = new Date('2025-07-15T10:00:00').getTime();
    const countdownElement = document.querySelector('.featured-event .event-date');

    function updateCountdown() {
        const now = new Date().getTime();
        const distance = featuredEventDate - now;

        const days = Math.floor(distance / (1000 * 60 * 60 * 24));
        const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));

        countdownElement.innerHTML = `<i class="far fa-calendar-alt"></i> Faltan ${days} días, ${hours} horas y ${minutes} minutos`;

        if (distance < 0) {
            clearInterval(countdownInterval);
            countdownElement.innerHTML = '<i class="far fa-calendar-alt"></i> ¡El evento ha comenzado!';
        }
    }

    const countdownInterval = setInterval(updateCountdown, 1000 * 60); 
    updateCountdown(); // 
});