package com.Alura.RetoLiteratura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosDelLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosDelAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count")  Double descargas

) {

}
