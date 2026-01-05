# Reto-Literatura
Aplicación desarrollada para buscar libros a través de una API externa, registrar los resultados obtenidos y almacenar dicha información en una base de datos PostgreSQL.
Además, permite consultar y mostrar los libros previamente buscados, facilitando el acceso y la persistencia de los datos.

#Caracteristicas:

- Uso de HttpRequest y HttpResponse para enviar y recibir datos.
- Transformación de datos JSON a objetos Java con Gson.
- Salida clara en consola mostrando el resultado de la conversión.
- Búsqueda de libros mediante una API externa.
- Registro de libros y autores en una base de datos PostgreSQL.
- Consulta de libros almacenados.
- Consulta de autores registrados.
- Filtrado de autores por año de publicación.
- Filtrado de autores por idioma.

#Funciones:

- Se digita un número correspondiente a las opciones mostradas por consola.
- Al seleccionar la opción 1, se busca un libro mediante la API y se guarda en PostgreSQL.
- Al seleccionar las demás opciones, se muestran en pantalla los datos registrados en PostgreSQL según la opción elegida.
- El programa se ejecuta de forma cíclica hasta que el usuario seleccione la opción de salir.

#Tecnologias utilizadas:

- Java 21+
- Gson (Google)
- [API de Libros (https://gutendex.com/books/)
- psAdmin4

#Ejecucion del programa y recomendacion:

- Clona este repositorio o descarga los archivos.
- Abre el proyecto en tu IDE (Eclipse, IntelliJ, VS Code, etc.).
- Abra psAdmin4.

- A continuación, agregue sus credenciales de conexión en el servidor para establecer la conexión entre la aplicación Java y pgAdmin 4, como se muestra a continuación:

![](https://github.com/FabioTochoy/Reto-Literatura/blob/main/reto%20literatura/imagen%201.jpg)

- Al iniciar el programa, se muestran seis opciones disponibles, como se observa a continuación:

![](https://github.com/FabioTochoy/Reto-Literatura/blob/main/reto%20literatura/imagen%202.jpg)

- En este paso se selecciona la opción 1. Una vez que el libro es encontrado mediante la API, se guarda automáticamente en la base de datos.

![](https://github.com/FabioTochoy/Reto-Literatura/blob/main/reto%20literatura/imagen%203.jpg)

- En este paso se selecciona la opción 2, la cual muestra los libros registrados en la base de datos.

![](https://github.com/FabioTochoy/Reto-Literatura/blob/main/reto%20literatura/imagen%204.jpg)

- Las demás opciones muestran en pantalla la información correspondiente, según los datos almacenados en la base de datos.

#Notas Adicionales:

- La aplicación está diseñada para no cerrarse al ingresar datos no válidos.
- Los libros duplicados no se vuelven a registrar; esta validación no afecta la ejecución del programa.
- En caso de error, se muestra el mensaje correspondiente en pantalla y el programa continúa en ejecución.

#Aprendizaje

- Utilizar y comprender el funcionamiento de una API.
- Aplicar `HttpRequest` para enviar solicitudes y `HttpResponse` para recibir datos.
- Crear la conexión entre Java y una base de datos.
- Almacenar información en una base de datos SQL.

# Licencia

-Este proyecto fue creado con fines educativos como parte de la formación de ONE con Alura Latam.


  




  
