// Componente.jsx, componentes auxiliares. 

// Creacion del componente Componente, que da una lista usando un array
// Param entre {}, sino param.tal, varios parametros(ver en list). si par=val es el valor por defecto.
function Componente({gente}) { //Los parametros se pasan desde el componente padre como atributos de la etiqueta con nombre del parametro. 
    
    // funcion para dado un array, devolver una lista html. 
    //En este caso usa props, se pasan varios parametros y se accede a ellos con props.tal.
    function List(props) {
        const listElementos = props.gente.map(function(persona) {
            return <li>{persona}</li>;
        });

        return <ul>{listElementos}</ul>
    }


    return (
        <article>
            <h1>Mi primer componente</h1>
            <List gente={gente} />
        </article>
    );
}
export default Componente;