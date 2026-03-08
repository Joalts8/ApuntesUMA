let variable = 10;               // declaracion de variable y asignacion de valor
let tipo = typeof variable;      // tipo de dato de la variable
//tipos de datos: number, string, boolean, object, undefined, null, operadores aritmeticos: +, -, *, /, % (modulo), 
// operadores de asignacion: =, +=, -=, *=, /=, ++, -- operadores de comparacion: ==, ===, !=, !==, >, <, >=, <=

console.log(variable, `del tipo ${tipo}`);     //muestra por consola. Uso de tilde invertida para mostrar con ${}

window.alert(variable)                        //muestra por ventana emergente(alerta)

document.getElementById("H1").textContent = variable;    //en el elemento con la id, muestra el valor