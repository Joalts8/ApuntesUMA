const express = require("express");
const router = express.Router();

const clientesController = require('../controllers/clientes');//carga el controlador

// rutas usando el controlador.funcion
router.get("/", clientesController.listarClientes);
router.get("/:id",clientesController.editarCliente);
router.get("/nuevo",clientesController.nuevoCliente);
router.post ("/guardar", clientesController.guardarCliente);
router.get("/borrar/:id", clientesController.borrarCliente);

module.exports = router;
