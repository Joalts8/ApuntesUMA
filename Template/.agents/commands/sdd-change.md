---
name: sdd-hange
description: SDD · Nuevo requisito - primero la spec, luego el código
---
Nuevo requisito para la spec specs/$1/ (el texto que va después del nombre de
la carpeta): $ARGUMENTS
NO toques código. Usa la skill sdd.
1. Actualiza specs/$1/spec.md: nuevo RF (o cambio de uno existente) en EARS,
sus casos límite y lo que queda fuera de alcance.
2. Indica qué partes de plan.md y tasks.md habría que cambiar después.
3. Muéstrame el diff de la spec y espera mi aprobación.

# Validación de una Spec

## Objetivo
Validación de una spec revisando los test, que cumpla los requisitos y los criterios de aceptación

## Entradas
- Número de la feature, nombre o ambas cosas.

## Salidas
- En caso de éxito en la validación, se genera `qa-note.md`.
- En caso de fallo en la validación, se genera el archivo auxiliar `repare.md`.

## Proceso
1. Recorre `/feature/NNN-nombre/spec.md` requisito por requisito. Para cada RF indica qué tests lo cubren y el resultado de ejecutarlo. 
2. Si algún RF no está cubierto o falla, dilo claramente y da la validación por fallada y continua.
3. Revisa los requisitos no funcionales, y en caso de que uno no se cumpla, da por fallida la validación.
4. Revisa todo el código relacionado con la implementación. Si falla algo, da la validación por fallada.
5. Después comprueba los criterios de finalización y dame un veredicto: ¿la spec está cumplida?
6. Si no se cumplen, desmarca las tareas que fallán y da la validación por fallada.

## Reglas
- Si algo falla en alguno de los pasos del proceso, es decir, se dar por fallada la validación, NO arregles nada todavía. Apuntalo en el archivo auxiliar `repare.md` y continua el siguiente paso.
- Usa la skill sdd.