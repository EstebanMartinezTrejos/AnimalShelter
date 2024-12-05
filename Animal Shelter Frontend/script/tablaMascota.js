window.onload = function(){
    listMascotas();
}

//metodo para listar
let listMascotas = async() =>{
    const peticion = await  fetch("http://localhost:8081/mascotas",
    {
        method: "GET",
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json"
        }
    });

    const mascotas =await peticion.json();

    let contenidoTabla = "";

    for(let mascota of mascotas){
        let contenidoFila = ` <tr>
        <td>${mascota.idmascotas}</td>
        <td>${mascota.nombre}</td>
        <td>${mascota.raza}</td>
        <td>${mascota.edad}</td>
        <td>${mascota.peso}</td> 
        <td>
        <i onClick="editarMascotas(${mascota.idmascotas})" class="material-icons button edit">edit</i>
        <i onclick="borrarMascotas(${mascota.idmascotas})" class="material-icons button delete">delete</i>
        </td>
        </tr>
        `
        contenidoTabla += contenidoFila;
    }
    document.querySelector("#tabla tbody").outerHTML = contenidoTabla;
}

//metodo para borrar
let borrarMascotas = async(idmascotas) =>{
    const peticion = await  fetch("http://localhost:8081/mascotas/"+idmascotas,
    {
        method: "DELETE",
        headers:{
            "Accept": "application/json",
            "Content-type": "application/json"
        }
    });

    listMascotas();
    
}
//Metodo para editar
let idEditar;

let editarMascotas = async(idmascotas) =>{
mostrarFormulario();

idEditar = idmascotas;

const peticion = await  fetch("http://localhost:8081/mascotas/"+idmascotas,
{
    method: "GET",
    headers:{
        "Accept": "application/json",
        "Content-type": "application/json"
    }
});

const mascotas =await peticion.json();

document.getElementById("nombre").value = mascotas.nombre;
document.getElementById("raza").value = mascotas.raza;
document.getElementById("edad").value = mascotas.edad;
document.getElementById("peso").value = mascotas.peso;

let btnModificar = document.getElementById("btnModificar");

}

btnModificar.addEventListener("click", evento => {
    aplicarActualizacion(idEditar);
});

//aplicar la actualizacion
let aplicarActualizacion = async () =>{
    
    let campos = {};
    campos.idmascotas = idEditar;
    campos.nombre = document.getElementById("nombre").value;
    campos.raza = document.getElementById("raza").value;
    campos.edad = document.getElementById("edad").value;
    campos.peso = document.getElementById("peso").value;

    const peticion = await  fetch("http://localhost:8081/mascotas/"+idEditar,
        {
            method: "PUT",
            body: JSON.stringify(campos),
            headers:{
                "Accept": "application/json",
                "Content-type": "application/json"
            }
        });

        listMascotas();
}


//Metodo para mostrar el formulario
function mostrarFormulario() {
    let formulario = document.getElementById("formulario").style.visibility = "visible";
}
