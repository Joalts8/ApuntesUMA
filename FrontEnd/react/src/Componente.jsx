// Componente.jsx, componentes auxiliares. 

// Creacion del componente Componente, que da una lista usando un array
function Componente({gente}) { //Los parametros se pasan desde el componente padre como atributos de la etiqueta con nombre del parametro. Param entre {}, sino param.tal, varios parametros.
    
    // funcion para dado un array de nombres, devolver una lista html.
    function List({ gente }) {
        const listElementos = gente.map(function(persona) {
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