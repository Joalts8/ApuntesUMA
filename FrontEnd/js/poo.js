// Objetos, creados con llaves, con propiedades y metodos.  
const objeto = {
    propiedad: "valor",
    metodo: function() {}
}


//Constructor de objetos, new para crear una instancia de ese constructor.
function Const(parametro) {
    this.propiedad = parametro;
    this.metodo = function() {}
}


// Clase. Tiene el constructor y los metodos definidos. Los atributos se definen en el constructor
// Static igual q java, se accede sin crear una instancia de la clase.
// Herencia como java, con extends y super llama al padre.
class Clase {
    static PI= 3.14;
    #variablePrivada=1;        // variable privada, solo accesible dentro de la clase. Tambien para metodos

    constructor(parametro) {
        this.propiedad = parametro;
    }

    static metodo() {}

    // Getters y setters, para acceder a las propiedades de forma controlada. Se definen con get y set, y se acceden como propiedades(ver abajo). Se sulen usar para poner restricciones. Afectan en el constructor. 
    get propiedad() {
        return this._propiedad;
    }

    set propiedad(valor) {
        this._propiedad = valor;
    }
}

let e = new Clase("valor");
e.propiedad;                     // accede a la propiedad con el getter
e.propiedad = "nuevo valor";     // asigna un nuevo valor a la propiedad con el setter

objeto.metodo2 = function() {};       // se pueden añadir metodos a los objetos despues de su creacion. Util con objetos de json