package com.example.gestionnovelas;

import java.util.ArrayList;
import java.util.List;

public class Novela {

    //Atributos
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private String sinopsis;
    private List<String> reseñas;
    private boolean favorito;

    //Constructor
    public Novela(String titulo, String autor, int añoPublicacion, String sinopsis) {
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.sinopsis = sinopsis;
        this.reseñas = new ArrayList<>();
        this.favorito = false;
    }

    //Getters & Setters
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
    public int getAñoPublicacion() {
        return añoPublicacion;
    }
    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public List<String> getReseñas() {
        return reseñas;
    }
    public void setReseñas(List<String> reseñas) {
        this.reseñas = reseñas;
    }
    public boolean getFavorito() {
        return favorito;
    }
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}