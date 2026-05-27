const initModels = require("../models/init-models");
const sequelize = require("sequelize");
const models = initModels(sequelize);

const { Op } = require("sequelize"); //Añadido por mi para las operaciones

//En la documentacion se puede mirar las operaciones para where y para ordenar:
// https://sequelize.org/docs/v6/core-concepts/model-querying-basics/
// En el pdf esta los findAll, findOne, create, update y destroy
// En el pdf esta el include y el where muy simple, en el ejemplo yo añado una uso de ambos

const controller = {};

// listar cliente tiene los res
// editar cliente req para get
// guardar cliente req para post

// Listar clientes /////////////////////////////////////////////////////////////////////////////////////////////////////
controller.listarClientes = async function (req, res, next) {
    try {
        await models.CUSTOMER
            .findAll()
            .then(async (data) => {

            //manda salida de json
            //res.json(data);
            //manda a la vista index con los atributos {atributo: valor}
            res.render("index", {clientes: data});
        });
    } catch (error) {
        //manda salida de texto
        res.send("Se ha producido un error " + error);
        //manda salida de error 
        // res.status(404).send('Producto no encontrado’)
    }
};

// Editar cliente //////////////////////////////////////////////////////////////////////////////////////////////////////
controller.editarCliente = async function (req, res, next) {
    try {
        const cliente = await models.CUSTOMER.findOne({
                where: {
                    // utiliza el parametro de la url
                    CUSTOMER_ID: req.params.id
                    //Si fuese un parametro normal se usaria
                    // CUSTOMER_ID: req.query.id
                }
            });
        const supermercados = await models.MICRO_MARKET.findAll();
        const descuentos = await  models.DISCOUNT_CODE.findAll();
        res.render("cliente", {cliente: cliente, supermercados: supermercados, descuentos: descuentos});

    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};

// Guardar cliente //////////////////////////////////////////////////////////////////////////////////////////////////////
controller.guardarCliente = async function (req, res, next) {
    try {
        if (typeof req.body.CUSTOMER_ID != "undefined") {
            const cliente = await models.CUSTOMER.findOne({
                where: {
                    // utiliza el parametro del body
                    CUSTOMER_ID: req.body.CUSTOMER_ID
                }
            });

            if (cliente) {
                await cliente.update(
                    {
                        DISCOUNT_CODE: req.body.DISCOUNT_CODE,
                        ZIP: req.body.ZIP,
                        NAME: req.body.NAME,
                        ADDRESSLINE1: req.body.ADDRESSLINE1,
                        ADDRESSLINE2: req.body.ADDRESSLINE2,
                        CITY: req.body.CITY,
                        STATE: req.body.STATE,
                        PHONE: req.body.PHONE,
                        FAX: req.body.FAX,
                        EMAIL: req.body.EMAIL,
                        CREDIT_LIMIT: req.body.CREDIT_LIMIT
                    }
                );
            }

        } else {
            await models.CUSTOMER.create(
                {DISCOUNT_CODE: req.body.DISCOUNT_CODE,
                ZIP: req.body.ZIP,
                NAME: req.body.NAME,
                ADDRESSLINE1: req.body.ADDRESSLINE1,
                ADDRESSLINE2: req.body.ADDRESSLINE2,
                CITY: req.body.CITY,
                STATE: req.body.STATE,
                PHONE: req.body.PHONE,
                FAX: req.body.FAX,
                EMAIL: req.body.EMAIL,
                CREDIT_LIMIT: req.body.CREDIT_LIMIT}
            );
        }
        res.redirect('/');

    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};
// Nuevo cliente //////////////////////////////////////////////////////////////////////////////////////////////////////
controller.nuevoCliente = async function (req, res, next) {
    const supermercados = await models.MICRO_MARKET.findAll();
    res.render("cliente", {supermercados: supermercados})
};

// Borrar cliente //////////////////////////////////////////////////////////////////////////////////////////////////////
controller.borrarCliente = async function (req, res, next) {
    await models.PURCHASE_ORDER.destroy(
        {
            where: {CUSTOMER_ID: req.params.id}
        }
    );
    const cliente = await models.CUSTOMER.findOne({
        where: {
            CUSTOMER_ID: req.params.id
        }
    });
    await cliente.destroy();
    res.redirect("/");

};
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

controller.listarClientesCustom = async function (req, res, next) {
    try {
        await models.CUSTOMER
            .findAll({
                include: [
                    {
                        model: models.DISCOUNT_CODE, //Aqui se pone el modelo al que se quiere incluir
                        as: 'relacion', //Aqui se pone el alias que se le ha dado a la relacion en el init-models
                    }
                ],
                where: {
                    atributo: {
                        [Op.like]: '%valor%' //Ejemplo de operacion, ver en documentacion
                    }
                },
                order: [
                    ['CUSTOMER_ID'], //ascendente
                    // ['CUSTOMER_ID', 'DESC'] //descendente
                ]
            })
            .then(async (data) => {
            res.render("index", {clientes: data});
        });
    } catch (error) {
        res.send("Se ha producido un error " + error);
    }
};

module.exports = controller;