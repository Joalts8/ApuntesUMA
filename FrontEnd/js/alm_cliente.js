// Web storage
localStorage.setItem("nombre", "Christian");    // Almacena un valor en el almacenamiento local, de forma key-value, ambos deben ser strings. Permanente, no se borra al cerrar el navegador.
let nombre = localStorage.getItem("nombre");    // Obtiene un valor del almacenamiento local, devuelve null si no existe la clave.
localStorage.removeItem("nombre");              // Elimina un valor del almacenamiento local, si no existe la clave no hace nada.
localStorage.clear();                          // Elimina todos los valores del almacenamiento local.
// Si se usa sessionStorage en lugar de localStorage, el almacenamiento es temporal, se borra al cerrar la pestaña del navegador.

// IndexedDB-> Base de datos local en el navegador, permite almacenar grandes cantidades de datos estructurados
