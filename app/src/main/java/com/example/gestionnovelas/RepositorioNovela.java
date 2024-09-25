package com.example.gestionnovelas;

import android.content.Context;

import java.util.ArrayList;

public class RepositorioNovela {

    private ArrayList<Novela> listaNovelas;
    private AdaptadorNovela adapter;

    public RepositorioNovela(Context context, AdaptadorNovela adapter) {
        this.listaNovelas = new ArrayList<>();
        this.adapter = adapter;
    }

    public void agregarNovela(Novela novela) {
        listaNovelas.add(novela);
    }

    public void eliminarNovela(int index) {
        listaNovelas.remove(index);
    }

    public Novela obtenerDetallesNovela(int index) {
        return listaNovelas.get(index);
    }

    public ArrayList<Novela> obtenerNovelasFavoritas() {
        ArrayList<Novela> favoritas = new ArrayList<>();
        for (Novela novela : listaNovelas) {
            if (novela.getFavorito()) {
                favoritas.add(novela);
            }
        }
        return favoritas;
    }
}
