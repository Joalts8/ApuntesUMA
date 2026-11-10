---
name: sdd-status
description: Usa esta skill cuando se pregunte por fase actual y siguiente paso de una spec al usar sdd.
---
# Estado de una Spec

## Objetivo
Consultar y resumir la fase actual del flujo SDD, el progreso de tareas hechas y pendientes, y el siguiente paso o comando exacto a ejecutar para una feature.

## Entradas
- Número de la feature, nombre o ambas cosas.

## Proceso
Lee la información de la feature y `roadmap.md` y di en pocas líneas:
1. En qué fase del flujo SDD está esta spec y su estado. Indica también si está en implementación tras validación fallada (si existe `repair.md`).
2. Tareas hechas y pendientes (x de y).
3. El siguiente paso exacto, con el comando /sdd-* que debo ejecutar.

## Reglas
- No modifiques ningún archivo.
- Usa la skill sdd.