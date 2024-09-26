## GESTIÓN DE NOVELAS

# Objetivos
El objetivo de la aplicación "Gestión de Novelas" es proporcionar una plataforma sencilla y eficaz para que los usuarios puedan gestionar su colección de novelas.
Los usuarios pueden agregar, eliminar y marcar novelas como favoritas.
La aplicación también permite ver detalles de cada novela, mejorando la experiencia de gestión de la biblioteca personal de libros.
Esta apliación está desarrollada en Android Studio e implementada con Java.
Para que la aplicación fuera completamente funcional habría que vincularla a una base de datos.
Por el momento funciona correctamente pero cuando la aplicación se reinicia, los datos de las listas de novelas también lo hacen.

# Estructura (clases java)

 - 1. MainActivity.java
Esta es la clase principal de la aplicación, donde se gestionan las interacciones del usuario.
Inicializa la lista de novelas.
Permite agregar y eliminar novelas mediante diálogos.
Muestra los detalles de una novela seleccionada.
Muestra una lista de novelas favoritas.

 - 2. Novela.java
Esta clase representa una novela individual, con sus atributos, constructor, getters y setters

 - 3. RepositorioNovela.java
Esta clase actúa como un repositorio para gestionar las novelas.
Permite agregar, eliminar y obtener novelas.
Proporciona métodos para gestionar las novelas favorita

 - 4. AdaptadorNovela.java
Proporciona un adaptador para gestionar la visualización de las novelas en un ListView.
Se encarga de inflar el diseño de cada elemento de la lista y de enlazar los datos de las novelas a las vistas correspondientes.

# Estructura (archivos xml)

 - 1. activity_main.xml: 
Define la estructura de la pantalla principal, que incluye el título "Tus Novelas", la lista de novelas y los botones para agregar, eliminar y ver favoritas.

 - 2. agregar_novela.xml: 
Contiene el diseño del cuadro de diálogo para agregar una nueva novela, con campos para título, autor, año y sinopsis.

 - 3. eliminar_novela.xml: 
Contiene el diseño del cuadro de diálogo para eliminar una novela, con un campo para ingresar el título de la novela a eliminar.

 - 4. novela_item.xml: 
Define el diseño de cada elemento en la lista de novelas, mostrando el título y autor de manera atractiva.

 - 5. detalles_novela.xml:
Diseña la pantalla que muestra los detalles de una novela seleccionada.
Incluye campos para mostrar el título, autor, año de publicación, sinopsis y un botón para marcarla como favorita.

  - 6. novelas_favoritas.xml:
Diseña la pantalla que muestra la lista de novelas marcadas como favoritas.
Presenta un diseño similar al de la lista principal, pero solo incluye las novelas que el usuario ha marcado como favoritas.



Link al repositorio: https://github.com/Samuu10/GestionNovelasSamuelMunozVallejo.git
