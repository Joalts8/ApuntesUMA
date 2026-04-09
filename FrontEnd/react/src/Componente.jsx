// Componente.jsx, componente auxilair. 

function Componente(gente) { //Los parametros se pasan desde el componente padre como atributos de la etiqueta.
    
    // funcion para dado un array de nombres, devolver una lista html.
    function List(gente) {
        const listElementos = gente.map(function(persona) {
            return <li>{persona}</li>
        });

        return <ul>{listElementos}</ul>
    }


    return (
        <article>
            <h1>Mi primer componente</h1>
            <ol>
                <List gente={gente} />
            </ol>
        </article>
    );
}
export default Componente;