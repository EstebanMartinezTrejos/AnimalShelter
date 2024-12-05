/*Formulario de mascota*/

let boton = document.getElementById("btnRegistrar");

boton.addEventListener("click", async (e) => {
    e.preventDefault();
    await registrarMascota();
});

let registrarMascota = async () => {
    let campos = {}
    
    campos.nombre = document.getElementById("nombre").value;
    campos.raza = document.getElementById("raza").value;
    campos.edad = document.getElementById("edad").value;
    campos.peso = document.getElementById("peso").value;

    try {
        const peticion = await fetch("http://localhost:8081/mascotas", {
            method: "POST",
            body: JSON.stringify(campos),
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        });
        
        if (peticion.ok) {
            alert("Mascota registrada con éxito");
        } else {
            alert("Error al registrar la mascota");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Error al conectar con el servidor");
    }
}

