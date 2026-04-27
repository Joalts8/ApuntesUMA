// Componente donde se ponen ejemplos de manejadores onclick, onChange y onSubmit.
// Tambien se usaran los hooks useState, useEffect, useRef y useContext.
// En react, se usan pocos event listener (solo globales con window), se suelen usar manejadores como onClick para botones, onChange para inputs, onSubmit para formularios. 
// Y se usan hooks para manejar estados, como useEfect para elementods del DOM.

import {useContext, useEffect, useRef, useState } from "react";
import { TemaContext } from "./App.jsx";

function Manejadores() {
	//*Hooks*: nunca en if/bucles, siempre en el nivel superior del componente
    // useContext: usar variables de otro componente sin necesidad de pasar props. Se usa con un contexto creado con createContext y un Provider que lo envuelve en el componente padre.
	const tema = useContext(TemaContext);

	// useState: se usa para manejar estados en componentes, tiene un valor y una funcion set para actualizarlo (rerender). se da el valor inicial como parametro y puede ser de cualquier tipo. 
    // Set se puede usar con un valor nuevo, !bool, o con una funcion (VER ABAJO EN ONCLICK). Para arryos y objetos, ver al final.
	const [nombre, setNombre] = useState("");   // usado para onchange
	const [contador, setContador] = useState(0);// usado para onclick y useEffect
	const [mensaje, setMensaje] = useState(""); // usado para onSubmit
    const [bool, setBool] = useState(true);     // usado en onClick

	// useRef: se utiliza para guardar una referencia a un elemento del DOM o a un valor mutable que no causa rerender al cambiar. Se accede a la referencia con ref.current.
	const inputRef = useRef(null);

	// useEffect: Hay 3 tipos: ()=>{} -> se ejecuta con cada rerender. ()=>{},[] -> se ejecuta solo al montar el componente(refresh o con APis). ()=>{},[contador] -> se ejecuta al montar y cada vez que contador cambie.
	useEffect(() => {
		document.title = `Contador: ${contador}`;
        // Funcion de limpieza, util para eventListener, set Interval, etc. Se ejecuta al desmontar el componente o antes de ejecutar el efecto de nuevo. 
        return () => {}
	}, [contador]);

	// useReducer: se usa para manejar estados complejos con una funcion reductora. Se da la funcion reductora y el estado inicial como parametros.
	const [tareas, dispatch] = useReducer(funcionReducer, ["estado", "inicial"]);
	// Funcion reductora, recibe el estado actual y una accion, y devuelve el nuevo estado. accion tiene tipo y parametro, tipo es el tipo de accion y parametro es el valor que se quiere actualizar.
	const funcionReducer = (state, action) => {
			switch (action.type) {
				case "tal":
					return [...state, action.parametro];
			}
	};
	// Funcion para dispatch, se llama con el tipo de accion y el parametro. Se hacen tantas como se necesiten. Manejador
	const handleAddTarea = (parm) => {
		dispatch({
			type: 'tal',
			parametro: parm
		});
	}


    // Funciones de los manejadores
	const manejarClick = () => {
        // Ejemplo de cambio con operacion, no recomendado porque no se garantiza el valor actual del estado, se recomienda usar la funcion con el estado previo (VER ABAJO). Si varias seguidas, solo se aplicaria la ultima.
        //setContador(contador + 2);
		// Update function (forma recomendada con estado previo). Mas seguro y asincrono, se garantiza el valor actual del estado. Si varias seguidas, se aplican todas.
		setContador((prevContador) => prevContador + 1);
        setContador((prevContador) => prevContador + 1);

        // Ejemplo de cambio de estado con el valor contrario al actual.
        setBool(!bool);

        // Ejemplo de uso de useRef para acceder a un elemento del DOM.
        inputRef.current.style.backgroundColor = "";
	};

	const manejarCambio = (e) => {
        // Uso del set con nuevo valor.
		setNombre(e.target.value);
	};

	const manejarSubmit = (e) => {
		e.preventDefault();	//evita el comportamiento por defecto del formulario.
        // Uso del set con nuevo valor.
		setMensaje(`Formulario enviado. Hola ${nombre || "anonimo"}`);
        // Ejemplo de uso de useRef para acceder a un elemento del DOM.
        inputRef.current.focus();
        inputRef.current.style.backgroundColor = "yellow";
	};

	const manejadorAnidaodo = (e) => {
		// Ejemplo de manejador anidado, se puede usar para manejar eventos dentro de otros eventos, como un click dentro de un div.
		e.stopPropagation(); // para evitar que el evento se propague al padre. y solo se ejecute el manejador del hijo.
		alert("Manejador anidado");
	}


	return (
		<section>
			<h2>Ejemplos de manejadores y hooks</h2>
			<p>Tema actual (useContext): {tema}</p> 

            {/*Manejador onclick, para ejecutar accion al hacer clic.*/}
			<button onClick={manejarClick}>Pulsame</button>
			<p>Clicks: {contador}</p>
            <p>Estado booleano: {bool.toString()}</p>

            {/*Manejador onSubmit, para manejar el envio de formularios.*/}
			<form onSubmit={manejarSubmit}>
                {/* Manejador onChange, para manejar cambios en imputs, textarea y otros elementos de form.
                    ref para acceder a la referencia del elemento del DOM */}
				<input type="text" placeholder="Escribe tu nombre" value={nombre} onChange={manejarCambio} ref={inputRef} />
				<input type="submit" value="Enviar" />
			</form>

			<p>Valor actual (onChange): {nombre}</p>
			<p>{mensaje}</p>

			<div onClick={() => alert("Div clickeado")}>
				<button onClick={manejadorAnidaodo}>Manejador anidado</button>
			</div>
		</section>
	);
}


export default Manejadores;

// Update function para objetos y arrays:
// Para objetos como se cambia una propiedad sin modificar el objeto con set((obj) => ({...obj, prop: nuevoValor})). normalmente event.target.value.
// Para arrays, se puede usar set((arr) => [...arr, nuevoElemento]) para añadir un elemento al final, o set((arr) => arr.filter(el, i => i !== index)) para eliminar un elemento.
// Para ambos, on change para cambiar estados de props de el objeto añadir y al pulsar boton se añade con esas propiedades. 