package com.Alura.RetoLiteratura.Principal;

import com.Alura.RetoLiteratura.API.APIConsumer;
import com.Alura.RetoLiteratura.API.DataConverter;
//import com.Alura.RetoLiteratura.repository.LibrosRepository;
import com.Alura.RetoLiteratura.modelos.DatosDelLibro;
import com.Alura.RetoLiteratura.modelos.Libros;
import com.Alura.RetoLiteratura.modelos.Resultados;
import com.Alura.RetoLiteratura.repository.LibrosRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@Component
public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private APIConsumer consumer = new APIConsumer();
    private DataConverter converter = new DataConverter();
    private Scanner scanner = new Scanner(System.in);
    private String json;
    private LibrosRepository repositorio;






    public Principal(LibrosRepository repository) {
        this.repositorio = repository;
    }


    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {

            String menu = """
                    **********************************************
                    Elija la opción que desea seleccionar:
                    1. Buscar y guardar libro por el título.
                    2. Listar libros registrados.
                    3. Listar autores registrados.
                    4. Listar autores vivos en un año determinado.
                    5. Listar libros por idiomas.
                    0. Salir.
                    **********************************************
                    """;

            System.out.println(menu);

            String entrada = scanner.nextLine();

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("❌ Debes ingresar un número válido");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    EncontrarLibroPorTitulo();
                    break;
                                case 2:
                                    ListarLibrosRegistrados();
                                    break;
                                case 3:
                                    ListarAutoresRegistrados();
                                    break;
                                case 4:
                                    ListarAutoresVivos();
                                    break;
                                case 5:
                                    ListarLibrosPorIdiomas();
                                    break;
                case 0:
                    System.out.println("Cerrando la aplicación.");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }




    private void EncontrarLibroPorTitulo() {
        System.out.println("Digita el título del libro que deseas buscar:");
        String teclado = scanner.nextLine();
        json = consumer.getData(URL_BASE + "?search=" + teclado.replace(" ", "+"));
        DatosDelLibro datosDelLibro = getLibros(teclado);

        if (datosDelLibro != null) {
            mostrarLibroBonitoAPI(datosDelLibro);

            //verifica si existe en la base de datos
            Optional<Libros> libroExistente = repositorio.findByTitulo(datosDelLibro.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("⚠️ Este libro ya está guardado en la base de datos.");
            } else {
                Libros libros = new Libros(datosDelLibro);
                repositorio.save(libros);
                System.out.println("✅ Libro guardado correctamente.");
            }

        } else {
            System.out.println("No se encontró ningún libro con ese título.");
        }

    }


    private DatosDelLibro getLibros(String teclado) {

        Resultados datos = converter.getData(json, Resultados.class);
        return datos.listaDatosLibros().stream()
                .filter(libro -> libro.titulo().toUpperCase().contains(teclado.toUpperCase()))
                .findFirst().orElse(null);
    }

    private void mostrarLibroBonitoAPI(DatosDelLibro libro) {
        System.out.println("🔥------Libro------🔥");
        System.out.println("""
                ┌──────────────────────────────┐
                │ 📖 Título   : %s
                │ ✍️ Autor    : %s
                │ 🌍 Idioma   : %s
                │ ⭐ Descargas: %.0f
                └──────────────────────────────┘
                """.formatted(
                libro.titulo(),
                libro.autor().get(0).nombre(),
                libro.idiomas(),
                libro.descargas()
        ));
    }

    private void ListarLibrosRegistrados() {
        List<Libros> libros = repositorio.findAll();

        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros registrados.");
            return;
        }

        for (Libros libro : libros) {
            mostrarLibroBonitoRegistrado(libro);
        }

    }

    private void ListarAutoresRegistrados() {
        List<Libros> libros = repositorio.findAll();

        libros.stream()
                .map(Libros::getAutor)
                .distinct()
                .forEach(autor ->
                        System.out.println("✍️ Autor: " + autor)
                );
    }

    private void mostrarLibroBonitoRegistrado(Libros libro) {
        System.out.println("🔥------Libro Registrado------🔥");
        System.out.println("""
            ┌──────────────────────────────┐
            │ 📖 Título   : %s
            │ ✍️ Autor    : %s
            │ 🌍 Idioma   : %s
            │ ⭐ Descargas: %.0f
            └──────────────────────────────┘
            """.formatted(
                libro.getTitulo(),
                libro.getAutor(),
                libro.getIdiomas(),
                libro.getDescargas()
        ));
    }

    private void ListarAutoresVivos() {
        System.out.println("Ingresa el año que deseas consultar:");
        int anio;
        try {
            anio = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Debes ingresar un número válido.");
            return;
        }

        List<Libros> autoresVivos = repositorio.findAutoresVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año.");
            return;
        }

        autoresVivos.forEach(this::mostrarAutorBonito);
    }

    private void mostrarAutorBonito(Libros libros) {
        System.out.println("🔥------Autor Registrado------🔥");

        // Convertimos los Integer a String, si son null mostramos "Desconocido"
        String anioNacimiento = libros.getAnioDeNacimiento() != null
                ? String.valueOf(libros.getAnioDeNacimiento())
                : "Desconocido";

        String anioFallecimiento = libros.getAnioDeFallecimiento() != null
                ? String.valueOf(libros.getAnioDeFallecimiento())
                : "Desconocido";

        System.out.println("""
            ┌──────────────────────────────┐
            │ ✍️ Autor             : %s
            │ 🗓 Año Nacimiento    : %s
            │ ⚰️ Año Fallecimiento : %s
            └──────────────────────────────┘
            """.formatted(
                libros.getAutor(),
                anioNacimiento,
                anioFallecimiento
        ));
    }
    private void ListarLibrosPorIdiomas() {
        System.out.println("Ingresa el idioma para filtrar libros (ejemplo: en, es, fr, pt):");
        String idiomaBuscado = scanner.nextLine().trim().toLowerCase();

        List<Libros> libros = repositorio.findAll(); // Traemos todos los libros

        List<Libros> filtrados = libros.stream()
                .filter(libro -> libro.getIdiomas() != null &&
                        libro.getIdiomas().toLowerCase().contains(idiomaBuscado))
                .toList();

        if (filtrados.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
            return;
        }

        System.out.println("🔥------Libros en el idioma: " + idiomaBuscado + "------🔥");
        for (Libros libro : filtrados) {
            mostrarLibroBonitoRegistrado(libro);
        }
    }
}




