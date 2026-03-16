let variable = 10;               // declaracion de variable y asignacion de valor
const constante = "Hola";        // declaracion de variable constante, no se puede reasignar valor. Usado para elementos html(document.get)
// tipos de datos: number, string, boolean, object, undefined, null, para envolver un valor primitivo en un objeto: Number(), String(), Boolean(), Object()
// operadores aritmeticos: +, -, *, /, % (modulo), 
// operadores de asignacion: =, +=, -=, *=, /=, ++, -- 
// operadores de comparacion: ==, ===(=estricto, valor y tipo), !=, !==, >, <, >=, <=
// ifelse, switch(case pueden ser cond), bucles igual a java(-foreach es for of*)
// operador ternario->  condicion ? valor_si_verdadero : valor_si_falso


let tipo = typeof variable;                         // tipo de dato de la variable
let formato = variable.toFixed(2);                  // formato de numero a 2 decimales
variable=Math.floor(Math.random()*(10-1+1))+1;      //Math es un objeto con funciones matematicas, como random(), trunca(), round(), ceil(), floor(). Random entre 1 y 10, max-min+1, +min
console.log(variable, `del tipo ${tipo}`);          //muestra por consola. Uso de tilde invertida para mostrar con ${}

let array= [1, 2, 3, 4, 5];                    // declaracion de array
variable= array[0];                            // acceso a elemento del array por indice 0...n-1
variable= array.indexOf(3);                    // indice del elemento en el array, -1 si no se encuentra
variable= array.length;                        // longitud del array
array.sort();                                  // ordena el array, por defecto como string. Para numeros sort((a,b)=>a-b) o ((a,b)=>b-a) para descendente. tambien .reverse() para invertir el orden del array
array.push(6);                                 // agrega un elemento al final del array, con pop()`elimina el ultimo
array.unshift(0);                              // agrega un elemento al inicio del array, con shift() elimina el primero 
for(let c of array) {}                         // * bucle for each
array.forEach(ejemplo)                         // * bucle for each con funcion definida. Normalmente recibe elemento, indice y array como parametros.;
let arr= [... constante];                      // operador de propagacion, convierte string en array de caracteres. 
variable=arr.join("-");                        // convierte un array en un string, con el separador especificado. Por defecto es coma.
ejemplo(arr)                                   // sin ... pasa el array, con ... pasa los elementos del array como argumentos separados por coma. 
let arr2= arr.map(ejemplo)                     // aplica la funcion a cada elemento del array original.
arr2= arr.filter(ejemplo)                      // devuelve un nuevo array con los elementos que cumplen la condicion de la funcion.
arr2= arr.reduce(ejemplo)                      // aplica la funcion a cada elemento acumulando el resultado. El primer parametro es el acumulador, el segundo es el elemento actual. El valor inicial del acumulador 
                                               // es el primer elemento del array, o se puede especificar como segundo parametro de reduce()


// Definicion de funcion. 
// Parametros puede ser ... name, recibe cualquier cantidad de argumentos y los convierte en un array; si llama a name muestra el array, si llama a ... name muestra los argumentos separados por coma. 
// Se puede tener callback como parametro, se pasa una funcion a la q se le llama dentro de esta.
// Puede declararse al ejecutarse con function(){}
function ejemplo(Parametro) {
    return Parametro;       // Valor de retorno, no es obligatorio.
}