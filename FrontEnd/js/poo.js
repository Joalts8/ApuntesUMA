// Objetos, creados con llaves, con propiedades y metodos.  
const objeto = {
    propiedad: "valor",
    metodo: function() {}
}

//Constructor de objetos, new para crear una instancia de ese constructor.
function Clase(parametro) {
    this.propiedad = parametro;
    this.metodo = function() {}
}

// Clase. Tiene el constructor y los metodos definidos.
class Clase {
    constructor(parametro) {
        this.propiedad = parametro;
    }
    metodo() {}
}