let variable = 10;               // declaracion de variable y asignacion de valor
const constante = "Hola";        // declaracion de variable constante, no se puede reasignar valor. Usado para elementos html(document.get)
// tipos de datos: number, string, boolean, object, undefined, null, para envolver un valor primitivo en un objeto: Number(), String(), Boolean(), Object()
// operadores aritmeticos: +, -, *, /, % (modulo), 
// operadores de asignacion: =, +=, -=, *=, /=, ++, -- 
// operadores de comparacion: ==, ===, !=, !==, >, <, >=, <=
// ifelse, switch(case pueden ser cond), bucles igual a java
// operador ternario->  condicion ? valor_si_verdadero : valor_si_falso

let tipo = typeof variable;                    // tipo de dato de la variable
console.log(variable, `del tipo ${tipo}`);     //muestra por consola. Uso de tilde invertida para mostrar con ${}
variable=Math.floor(Math.random()*(10-1+1))+1;   //Math es un objeto con funciones matematicas, como random(), trunca(), round(), ceil(), floor(). Random entre 1 y 10, max-min+1, +min

window.alert(variable)                        //muestra por ventana emergente(alerta)
window.prompt("Ingrese un valor")             //muestra por ventana emergente con un campo para ingresar un valor(mejor usar funcion con formulario y document)

document.getElementById("H1").textContent = variable;       //en el elemento con la id, muestra el valor
document.getElementById("submit").onclick = function() {    //al hacer click en el elemento con la id, ejecuta la funcion. Funcion se puede definir fuera y llamar.
    variable = document.getElementById("text").value;       //valor del elemento con la id   
    variable = document.getElementById("si").checked;       //valor booleano del elemento con la id, sirve para checkbox y radio button
}
