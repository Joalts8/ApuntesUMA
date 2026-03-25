// Web storage
localStorage.setItem("nombre", "Christian");    // Almacena un valor en el almacenamiento local, de forma key-value, ambos deben ser strings. Permanente, no se borra al cerrar el navegador.
let nombre = localStorage.getItem("nombre");    // Obtiene un valor del almacenamiento local, devuelve null si no existe la clave.
localStorage.removeItem("nombre");              // Elimina un valor del almacenamiento local, si no existe la clave no hace nada.
localStorage.clear();                          // Elimina todos los valores del almacenamiento local.
// Si se usa sessionStorage en lugar de localStorage, el almacenamiento es temporal, se borra al cerrar la pestaña del navegador.

// IndexedDB-> Base de datos local en el navegador, permite almacenar grandes cantidades de datos estructurados. Key-value
const request = indexedDB.open("Nombre", 1);    // Crea/abre la BD con el nombre "Nombre" y versión 1. Si la versión es mayor que la existente, se actualiza.
let bd;

// Creación de la base de datos
//evento al crear o actualizar la BD(Cambiar versión)
request.addEventListener("upgradeneeded", function(e){       
    bd = e.target.result;       // Asociar una referencia a la base de datos abierta

    const objectStore = bd.createObjectStore("tabla",        // Crear un objectStore llamado "os_notas", es una tabla en bd. Especificamos PK con keyPath, y autoIncrement para que se genere automáticamente
        {keyPath: "id", autoIncrement: true});
    objectStore.createIndex("IndiceT", "titulo", {unique: false});   //Crear un índice para el campo "titulo", no es único, permite duplicados. Define columna, pero son props de objetos. Indice, propiedad, {opciones}

    console.log("Establecimiento completo de la base de datos");
});

// evento al abrir la BD con éxito 
request.addEventListener("success", function(e) {           
    bd = e.target.result; 
    console.log("Base de datos abierta y lista para usarse.");
});

// evento al abrir la BD con error
request.addEventListener("error", function() {              
    console.error("Error al abrir la base de datos");
});


// Agregacion de datos
function agregarProducto(titulo, db) {
    const nuevoProducto = { titulo };                                       // Crear un nuevo objeto normal

    const transaction = db.transaction(["tabla"], "readwrite");         // Iniciar una transacción a realizar, especificando tablas y modo(readwrite, readonly, versionchange)
    const tabla = transaction.objectStore("tabla");                     // Obtener la tabla a la que se va a acceder, con su nombre.
    const query = tabla.add(nuevoProducto);                             // Agregar el nuevo producto a la tabla. 

    // evento de éxito al agregar el producto
    query.onsuccess = () => console.log("Dato añadido");

    // evento al completar la transacción
    transaction.addEventListener("complete", () => {
        console.log("Transacción completada: modificación de la base de datos finalizada.");
    });

    // evento al error en la transacción
    transaction.addEventListener("error", () =>
        console.log("Transacción no abierta debido a un error"),
    );
}


// Lectura de datos
// Leer sin cursor, con get o getAll, por clave primaria o índice. Devuelve un objeto o un array de objetos.
function lectura(id, db, titulo) {
    const transaction = db.transaction("tabla", "readonly");    
    const tabla = transaction.objectStore("tabla");

    let query = tabla.get(id);        // Obtener un registro por su clave primaria (ID) mejor const, pero let para este ejemplo
    query=tabla.getAll();             // Obtener todos los registros de la tabla, devuelve un array con los objetos.
    query = tabla.get(titulo);        // Obtener un registro por un índice, devuelve el primer registro que coincida con el valor del índice. Si no existe devuelve undefined.
    query = tabla.getAll(titulo);     // Obtener todos los registros que coincidan con el valor del índice, devuelve un array con los objetos. Si no existe devuelve un array vacío.

    // evento de éxito al obtener el producto esta error etc.
    query.onsuccess = () => console.log("Encontrado:", query.result); // Acceso al dato con query.result.
}

// Leer con Cursor
function leerTodos(db) {
    const transaction = db.transaction("tabla", "readonly");
    const tabla = transaction.objectStore("tabla");
    const cursorRequest = tabla.openCursor();

    // evento de éxito al abrir el cursor, en el cv sale un ejemplo mucho mayor, con esto recorre todo.
    cursorRequest.addEventListener("success", function(e) {
        const cursor = e.target.result;
        if (cursor) {
            console.log("Producto:", cursor.value);
            cursor.continue();                          // Continuar al siguiente registro, si no se llama a continue, se detiene.
        } else {
            console.log("No hay más registros.");
        }
    });
}


// Actualización de datos
function actualizarPrecio(id, nuevoPrecio) {
    const transaction = db.transaction("tabla", "readonly");    
    const tabla = transaction.objectStore("tabla");
    let query = tabla.get(id);// Obtener el producto

    query.addEventListener("success", function() {
        const data = getRequest.result;
        data.precio = nuevoPrecio;              // Modificamos el campo
        const updateRequest = store.put(data);  // Guardamos los cambios

        // Evento de éxito al actualizar el producto, existe error ...
        updateRequest.onsuccess = () => console.log("Precio actualizado");
    });
}


// Eliminación de datos
function eliminarProducto(id) {
    const transaction = db.transaction("tabla", "readonly");    
    const tabla = transaction.objectStore("tabla");
    
    const query = tabla.delete(id);     // Eliminar un registro por su clave primaria (ID). Si no existe no hace nada.

    // Evento de éxito al eliminar el producto, existe error ...
    query.onsuccess = () => console.log("Producto eliminado con éxito");
}