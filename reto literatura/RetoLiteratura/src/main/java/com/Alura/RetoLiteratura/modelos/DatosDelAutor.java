package com.Alura.RetoLiteratura.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosDelAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer anioDeNacimiento,
        @JsonAlias("death_year") Integer anioDeFallecimiento
) {
}
