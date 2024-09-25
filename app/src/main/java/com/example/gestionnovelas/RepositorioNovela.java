package com.example.gestionnovelas;

import android.content.Context;
import java.util.ArrayList;

public class RepositorioNovela {

    //Atributos
    private ArrayList<Novela> listaNovelas;
    private AdaptadorNovela adapter;

    //Constructor
    public RepositorioNovela(Context context, AdaptadorNovela adapter) {
        this.listaNovelas = new ArrayList<>();
        this.adapter = adapter;
    }

    //Método para agregar una novela a la lista
    public void agregarNovela(Novela novela) {
        listaNovelas.add(novela);
    }

    //Método para eliminar una novela de la lista
    public void eliminarNovela(int index) {
        listaNovelas.remove(index);
    }

    //Método para obtener las novelas favoritas
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
