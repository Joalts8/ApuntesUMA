import { useState } from 'react'
import './App.css'
import Component from './Componente.jsx'

// Componente principal de la aplicacion.

// Creacion del componente App
function App() {
  // Codigo de js del componente: variables, funciones, hooks, etc.
  let gente = ["Jorge", "Maria", "Pepe"];


  // Return del componente, html y componentes hijos(<Component />). Entre <> y </> se pueden incluir varios elementos.
  // Si se usa js dentro del html, se debe usar {}. 
  return (
    <>
      <div>
        <p>Hola</p>
      </div>
      <Component gente={gente} />
    </>
  )
}

// Exportacion del componente App para su uso en otros archivos.
export default App
