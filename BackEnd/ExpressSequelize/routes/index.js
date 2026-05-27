const express = require("express");
const router = express.Router();

const clientesController = require('../controllers/clientes');//carga el controlador

// rutas usando el controlador.funcion
router.get("/", clientesController.listarClientes);
// los parametros se pasan como parte de la url(/id) o como parametros de url (?id=) 
router.get("/:id",clientesController.editarCliente);
router.get("/nuevo",clientesController.nuevoCliente);
// Los parametros del body se pasan a través de un formulario.
router.post ("/guardar", clientesController.guardarCliente);
router.get("/borrar/:id", clientesController.borrarCliente);

module.exports = router;
