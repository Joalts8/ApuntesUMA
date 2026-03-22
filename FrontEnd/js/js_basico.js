// En caso de ser tipo modulo en el html, se puede importar funciones o variables de otros JS(export let ejemplo)
// import {ejemplo} from "./modulo.js";   


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
variable=Math.floor(Math.random()*(10-1+1))+1;      // Math es un objeto con funciones matematicas, como random(), trunca(), round(), ceil(), floor(). Random entre 1 y 10, max-min+1, +min
console.log(variable, `del tipo ${tipo}`);          // muestra por consola. Uso de tilde invertida para mostrar con ${}
setTimeout(ejemplo, 1000, variable);                // ejecuta la funcion despues de un tiempo especificado en milisegundos. Si se pasan argumentos a la funcion, se ponen despues del tiempo. Lo posterior se ejecuta antes de la funcion(Asincrono).
//Para seguir orden, callback hell-> funciones dentro de funcion que espera. Ahora se usan promesas y async await para evitarlo.

//Array
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

let fecha= new Date(2033, 11);              // fecha y hora actual. Parametros: año, mes(1-12), dia(1-31), hora(0-23), minuto(0-59), segundo(0-59), milisegundo(0-999). Si se omite=actual. Si solo un param es ms, varios pones los retantes a 0.


// Destructuracion 
let a=4, b=1;
[a,b]=[b,a];                                      // Intercambia los valores
[array[0], array[1]] = [array[1], array[3]];      // Intercambia los valores de un array
let [x, y] = array;                               // Asigna los valores del array a las variables, desde [0], con {} para objetos usando las propiedades como claves


// Definicion de funcion. 
// Parametros puede ser ... name, recibe cualquier cantidad de argumentos y los convierte en un array; si llama a name muestra el array, si llama a ... name muestra los argumentos separados por coma. 
// Se puede tener callback como parametro, se pasa una funcion a la q se le llama dentro de esta.
// Puede declararse al ejecutarse con function(){}
// Se puede pasar un objeto y poner {parametros}, accediendo a ellos(desestructuracion)
function ejemplo(Parametro) {
    console.log(Parametro);
    return Parametro;       // Valor de retorno, no es obligatorio.
}


//Captura de errores
try {// codigo que puede generar un error
    throw new Error("Mensaje de error"); // lanza un error con un mensaje personalizado
} catch (error) {// codigo para manejar el error
    console.error(error.message); // muestra el mensaje de error por consola
} finally {// codigo que se ejecuta siempre, haya o no error
}


// Promesas-> para manejar operaciones asincronas. 
function promesa() {
    return new Promise(function(resolve, reject)  { //Define una nueva promesa, con una funcion que recibe dos parametros
        let exito = true; // Simula una condicion de exito o fracaso
        if (exito) {
            resolve("Operacion exitosa"); // Resuelve la promesa y devuelve uno o varios valores
        } else {
            reject("Operacion fallida"); // Rechaza la promesa con un motivo
        }
    });
}
promesa().then(ejemplo).catch(function(error) {         // Maneja el resultado de la promesa, con then para exito y catch para error. Si varias, funcion en then hace return de otra funcion. Solo 1 catch, pero 1 then por cada promesa.
    console.error(error); 
});

// Async/Await-> para manejar operaciones asincronas de forma mas legible
async function asincrona() {            // Define una funcion asincrona, que devuelve una promesa
    try {
        let resultado = await promesa();        // Espera a que la promesa se resuelva y asigna el resultado a la variable
        ejemplo(resultado);                     // Lo que se haga en el then, si varias promesas, se hace await con cada una.
    } catch (error) {                           // maneja el reject
        console.error(error); 
    }   
}
asincrona();


// Json-> formato de datos, con clave-valor.
// Fetch-> para hacer peticiones a servidores y obtener datos(o json local). Promesa. Exito devuelve un objeto Response, convertible a JS Object, si no se resuelve con un error.
async function fetchData() {
    try {
        const response = await fetch("json.json");          // Hace la peticion al servidor, parametro{opciones}, como Method, por defecto GET.
        if (!response.ok) {                                 // Si la respuesta no es exitosa, lanza un error con el status
            throw new Error("Error en la peticion: " + response.status);
        }
        let data = await response.json();                   // Convierte la respuesta a un objeto JS, devuelve una promesa que se resuelve con el objeto

        let datajson = JSON.stringify(data);                // Convierte el objeto a una cadena JSON, para enviarlo a un servidor o guardarlo en un archivo
        let dataobj = JSON.parse(datajson);                 // Convierte la cadena JSON a un objeto JS, para manipularlo en el codigo

    } catch (error) {
        console.error(error);
    }
}