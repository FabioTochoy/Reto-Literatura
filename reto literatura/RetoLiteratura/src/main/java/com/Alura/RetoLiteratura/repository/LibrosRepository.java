package com.Alura.RetoLiteratura.repository;


import com.Alura.RetoLiteratura.modelos.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface LibrosRepository extends JpaRepository<Libros,Long>{

    // Este método permite buscar un libro por título
    Optional<Libros> findByTitulo(String titulo);

    @Query("SELECT l FROM Libros l WHERE CAST(l.anioDeNacimiento AS int) <= :anio AND (l.anioDeFallecimiento IS NULL OR CAST(l.anioDeFallecimiento AS int) >= :anio)")
    List<Libros> findAutoresVivosEnAnio(@Param("anio") Integer anio);


}
