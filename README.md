## Universidades (Angular y Spring)

### Descripción del proyecto:

El proyecto consiste en una aplicación web desarrollada con Angular en el frontend y Spring Boot en el backend. Esta aplicación está diseñada para gestionar universidades, permitiendo a los usuarios ver una lista de universidades, agregar nuevas universidades, ver detalles de cada universidad y eliminarlas si es necesario.

### Puntos de Rúbrica implementados:

#### Angular:

La aplicación posee tres pantallas, la cual es cpaz de listar universidades y añadir nuevas.

- Hay una  barra de búsqueda el cual filta universidades.
- Posee una pantalla que consulta los datos de una uversidad.
- Tiene un formulario la cual permite modifcar los datos de una universidad.
- También existe un boton de eliminación en listado de las universidades y en los detalles de cada universidad.

#### Spring: 

Esta aplicación se conecta con una base de datos en este caso MySQL, donde existe una tabla universidad con los siguientes campos: id, nombre, ubicación, estado, foto, disponibilidad.

- Se agrego una funcionalidad  GET mediante spring para recuperar una universidad en BBDD.
- De la misma forma se añadio una funcionalidad POST que crea una nueva universidad.
- También esta la funcionalidad PUT que modifica una universidad.
- Y por último la funcionalidad DELETE que elimina la universidad.
- Se añadio paginación , ordenación y filtrado al verbo GET.
- Se realizo varios tests unitarios a cada verbo para comprobar su funcionalidad.
   
### Puntos de Rúbrica no implementados:

#### Angular:

- Falta El widget de selección para los valores en las pantallas de creación/modificación.
- Falta la paginación.
- No se han agregado Test Unitarios.

#### Spring:

- Falta el controlador para los valores con verbo GET para recuperarlos.
- Falta añadir una tabla adicional.
- Falta consultas de búsqueda con filtrado. 

### Video proyecto final

- https://eepmad-my.sharepoint.com/:v:/g/personal/salexis-simbana1_eep-igroup_com/EXewMtv7V4xDqsYRIOuIH0QBHylm87pu8_O87_OpUEKMrA?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D&e=E8UQF3