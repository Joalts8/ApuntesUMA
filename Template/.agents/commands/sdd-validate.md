---
description: SDD · Valida la spec RF por RF.  (uso - /sdd-validate NNN)
---
Recorre `/feature/$1-/spec.md` requisito por requisito. Para cada RF indica qué test lo cubre y el resultado de ejecutarlo. Usa la skill sdd.
Si algún RF no está cubierto o falla, dilo claramente. NO arregles nada todavía. Apuntalo en el archivo auxiliar `repare.md`. Y da la validación por fallada.
Revisa todo el código relacionado con la implementación. Si falla algo apuntalo en el archivo auxiliar `repare.md`. Y da la validación por fallada.
Después comprueba los criterios de finalización y dame un veredicto:
¿la spec está cumplida?
Finalmente, si se cumple todos los criterios, crea el archibo `qa-note.md`.
Si no se cumplen, haz una lista de lo que falla en el archivo auxiliar `repare.md`, desmarca las tareas que fallán y da la validación por fallada.