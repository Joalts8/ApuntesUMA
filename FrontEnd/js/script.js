// Script que interactua con el html
let variable = 10;               


window.alert(variable)                        //muestra por ventana emergente(alerta)
window.prompt("Ingrese un valor")             //muestra por ventana emergente con un campo para ingresar un valor(mejor usar funcion con formulario y document)

document.getElementById("H1").textContent = variable;       //en el elemento con la id, muestra el valor
document.getElementById("submit").onclick = function() {    //al hacer click en el elemento con la id, ejecuta la funcion. Funcion se puede definir fuera y llamar(en js_basico).
    variable = document.getElementById("text").value;           //valor del elemento con la id   
    variable = document.getElementById("si").checked;           //valor booleano del elemento con la id, sirve para checkbox y radio button
}




