// Script que interactua con el html
// DOM-> Document Object Model, representacion del html como un arbol de objetos, que JS puede manipular dinamicamente.
let variable = 10;               


window.alert(variable)                        //muestra por ventana emergente(alerta)
window.prompt("Ingrese un valor")             //muestra por ventana emergente con un campo para ingresar un valor(mejor usar funcion con formulario y document)


// document es el documento, se puede camviar titulo, agregar elementos, cambiar estilos, etc.
// Selección de elementos
const submit=document.getElementById("submit")             // selecciona el elemento con la id, devuelve un objeto. Si no existe devuelve null.
const h1 = document.getElementsByClassName("H1");          // selecciona los elementos con la clase, devuelve un array. Si no existe devuelve un array vacio. Puede usarse todo(bucle) el array o un elemento por indice. 
const tag=document.getElementsByTagName("input")           // selecciona los elementos con la etiqueta, devuelve un array. Si no existe devuelve un array vacio. Uso como class.
const img= document.querySelector("img");                  // selecciona el primer elemento que coincide con el selector CSS, devuelve un objeto. Si no existe devuelve null.
// querySelectorAll()                                         selecciona todos los elementos y devuelve NodeList, array sin todos los metodos de array.
// DOM NAV-> firstElementChild, lastElementChild, nextElementSibling, previousElementSibling, parentElement, children, parentElement...

// Manipulacion de elementos
h1[0].textContent = variable;                   // Pone el valor a un elmento, el contenido q se ve. Solo a un elemento del array
img.setAttribute("id", "img1");                 // Agrega o cambia un atributo del elemento
img.src="../html/ejemplo.jpg";                  // Cambia el atributo, es ente caso src
submit.onclick = function() {                   // Al hacer click en el elemento, ejecuta la funcion. Funcion se puede definir fuera y llamar(en js_basico). Se puede en el html * o con eventos*.
    variable = document.getElementById("text").value;           //valor del atributo de un elemento  
    variable = document.getElementById("si").checked;           //valor booleano del elemento con la id, sirve para checkbox y radio button
}
for (let c of tag) {                     // *aplica a todo el array
    c.style.backgroundColor = "red";     // Cambia el estilo del elemento.
}

// Creacion de elementos
let div = document.createElement("div");     // Crea un nuevo elemento del tipo especificado.
div.textContent = "Hola";                  
document.body.append(div);                   // Agrega el elemento al final del body(prepend al inicio). Puede ser elem.append para en ese elemento(como en una caja)
let div2=div.cloneNode(true);                // Clona el elemento, con true clona tambien los hijos, con false solo el elemento sin hijos.
document.body.insertBefore(div2, submit)     // Insertar antes de un elemento especifico.

// Eliminacion de elementos
document.body.removeChild(div);  // o elemento.removeChild() o elemento.remove() 

/*ClassList-> Permite añadir y eliminar clases a elementos, normalmente para modificar estilos
* element.classList.add("clase") para añadir una clase
* element.classList.remove("clase") para eliminar una clase
* element.classList.toggle("clase") para añadir la clase si no la tiene, o eliminarla si ya la tiene
* element.classList.contains("clase") devuelve true si el elemento tiene la clase, false si no la tiene
* element.classList.replace("clase1", "clase2") para reemplazar una clase por otra
*/


// EventListener-> agrega un evento a un elemento, con el tipo de evento y la funcion a ejecutar. Se pueden agregar varios eventos al mismo elemento.
// Eventos-> click, mouseover(poner encima), mouseout(quitar), keydown(tecla presionada), keyup(tecla soltada), keypress(tecla presionada y soltada), dblclick(doble click).
listenerBtn.addEventListener("click", boton2);       // Con el evento, ejecuta la funcion
// removeEventListener("click", boton2)                 Borra el evento
document.addEventListener("keydown", tecla);       // Evento para toda la pagina, para mover objeto, seria switch en la funcion con las teclas y top, left para mover el objeto con css.



// * funcion para boton, pero con onclick en html. Mejor evento
function boton() {
    console.log("Boton pulsado");
}
// Funciones de evento con listener.
function boton2(event) {
    let valor = event.target.value;                 // event es el objeto del evento, target es el elemento que lo genero. 
    console.log("Boton pulsado", valor);
}
function tecla(event) {
    console.log("Tecla presionada", event.key);     // event.key es la tecla presionada, event.code es el codigo de la tecla, shiftKey bool si shift esta presionado, ctrlKey bool si ctrl esta presionado.
    event.preventDefault();                         // Evita la accion por defecto del evento, como enviar un formulario al hacer click en un boton submit si falta algo.
    //form.submit() para enviar el formulario manualmente, si se ha evitado el envio automatico con preventDefault.
}