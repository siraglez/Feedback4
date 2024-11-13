# Feedback4
 
Link al repositorio: https://github.com/siraglez/Feedback4.git

# Gestión de Novelas

Este proyecto es una aplicación Android para gestionar novelas, permitiendo a los usuarios registrar, ver detalles, marcar como favoritas y reseñar novelas. La aplicación soporta temas claro y oscuro, que pueden ser configurados por el usuario.

## Estructura y Componentes Principales
### Actividades
1. *LoginActivity:* Permite a los usuarios iniciar sesión o registrarse. Usa `UsuarioDatabaseHelper` para verificar credenciales y registrar nuevos usuarios.
2. *MainActivity:* Es la actividad principal que muestra una lista de novelas. Aquí, el usuario puede cambiar entre temas y acceder a la configuración.
3. *ConfiguracionActivity:* Proporciona opciones para cambiar el tema, realizar copias de seguridad y restaurar datos. También incluye la opción de cerrar sesión.

### Fragments
1. *ListaNovelasFragment:* Muestra la lista de novelas, permitiendo a los usuarios seleccionar una para ver sus detalles.
2. *DetallesNovelaFragment:* Muestra los detalles de una novela seleccionada. Permite marcarla como favorita, agregar reseñas, y eliminarla.
3. *AgregarNovelaFragment:* Permite al usuario agregar una nueva novela ingresando título, autor, año de publicación y sinopsis.
4. *AgregarResenaFragment:* Facilita la adición de una reseña a una novela seleccionada.

### Base De Datos
1. *NovelaDatabaseHelper:* Gestiona la base de datos de novelas, permitiendo agregar, eliminar, actualizar y obtener novelas y sus reseñas.
2. *UsuarioDatabaseHelper:* Maneja la base de datos de usuarios para verificar el inicio de sesión, registrar nuevos usuarios, y almacenar la preferencia de tema.

### Adaptadores y Widgets
1. *NovelaAdapter:* Presenta cada novela en la lista con su título, autor y una indicación si es favorita.
2. *NovelasFavoritasWidget:* Muestra las novelas marcadas como favoritas en un widget de la pantalla de inicio.

## Funcionalidades Clave
- *Gestión de Novelas:* Los usuarios pueden agregar, ver detalles, reseñar, y eliminar novelas.
- *Favoritos:* Cada novela puede marcarse como favorita, y estas se reflejan en el widget.
- *Reseñas:* Posibilidad de agregar múltiples reseñas a cada novela.
- *Temas:* Soporte para tema claro y oscuro, configurable desde ConfiguracionActivity.
- *Copia de Seguridad:* Opción para realizar y restaurar copias de seguridad de la información de los usuarios.

## Configuración De Tema
La aplicación soporta temas claro y oscuro, que se almacenan en las preferencias del usuario y se aplican en todas las pantallas.

## Gestión De Usuarios
La funcionalidad de registro y autenticación permite a los usuarios mantener sus preferencias y temas almacenados individualmente.
