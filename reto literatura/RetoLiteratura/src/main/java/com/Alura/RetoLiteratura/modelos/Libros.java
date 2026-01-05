package com.Alura.RetoLiteratura.modelos;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")

public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private String idiomas;
    private Double descargas;
    private Integer anioDeNacimiento;
    private Integer anioDeFallecimiento;

    public Libros() {
    }


    public Libros(DatosDelLibro datosDelLibro){
        this.titulo = datosDelLibro.titulo();

        if (!datosDelLibro.autor().isEmpty()) {
            DatosDelAutor primerAutor = datosDelLibro.autor().get(0);
            this.autor = primerAutor.nombre();
            this.anioDeNacimiento = primerAutor.anioDeNacimiento();
            this.anioDeFallecimiento = primerAutor.anioDeFallecimiento();
        }

        this.idiomas = String.join(", ", datosDelLibro.idiomas());
        this.descargas = datosDelLibro.descargas();
    }

    @Override
    public String toString() {
        return   "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", descargas='" + descargas + '\'';

    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = Id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public Integer getAnioDeNacimiento() {
        return anioDeNacimiento;
    }

    public void setAnioDeNacimiento(Integer anioDeNacimiento) {
        this.anioDeNacimiento = anioDeNacimiento;
    }

    public Integer getAnioDeFallecimiento() {
        return anioDeFallecimiento;
    }

    public void setAnioDeFallecimiento(Integer anioDeFallecimiento) {
        this.anioDeFallecimiento = anioDeFallecimiento;
    }
}

