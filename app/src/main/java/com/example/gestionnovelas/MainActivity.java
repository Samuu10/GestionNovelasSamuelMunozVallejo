package com.example.gestionnovelas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNovelas;
    private AdaptadorNovela adapter;
    private ArrayList<Novela> listaNovelas;
    private RepositorioNovela repositorio;
    private Button btnAgregar, btnEliminar, btnFavoritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        listViewNovelas = findViewById(R.id.list_view_novelas);
        btnAgregar = findViewById(R.id.btn_agregar);
        btnEliminar = findViewById(R.id.btn_eliminar);
        btnFavoritas = findViewById(R.id.btn_favoritas);

        // Inicializar la lista de novelas y el repositorio
        listaNovelas = new ArrayList<>();
        adapter = new AdaptadorNovela(this, listaNovelas);
        listViewNovelas.setAdapter(adapter);
        repositorio = new RepositorioNovela(this, adapter);

        // Añadir novela
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregarNovela());

        // Eliminar novela
        btnEliminar.setOnClickListener(v -> mostrarDialogoEliminarNovela());

        // Ver detalles de la novela seleccionada
        listViewNovelas.setOnItemClickListener((adapterView, view, position, id) -> {
            Novela novela = listaNovelas.get(position);
            mostrarDetallesNovela(novela);
        });

        // Botón para ver novelas favoritas
        btnFavoritas.setOnClickListener(v -> mostrarFavoritas());
    }

    private void mostrarDialogoAgregarNovela() {
        View dialogView = getLayoutInflater().inflate(R.layout.agregar_novela, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        EditText tituloInput = dialogView.findViewById(R.id.input_titulo);
        EditText autorInput = dialogView.findViewById(R.id.input_autor);
        EditText añoInput = dialogView.findViewById(R.id.input_año);
        EditText sinopsisInput = dialogView.findViewById(R.id.input_sinopsis);

        dialogBuilder.setTitle("Agregar");
        dialogBuilder.setPositiveButton("Agregar", (dialogInterface, i) -> {
            String titulo = tituloInput.getText().toString();
            String autor = autorInput.getText().toString();
            int año = Integer.parseInt(añoInput.getText().toString());
            String sinopsis = sinopsisInput.getText().toString();

            Novela nuevaNovela = new Novela(titulo, autor, año, sinopsis);
            repositorio.agregarNovela(nuevaNovela);
            listaNovelas.add(nuevaNovela);
            adapter.notifyDataSetChanged();
        });
        dialogBuilder.setNegativeButton("Cancelar", null);

        dialogBuilder.create().show();
    }

    private void mostrarDialogoEliminarNovela() {
        View dialogView = getLayoutInflater().inflate(R.layout.eliminar_novela, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        EditText tituloInput = dialogView.findViewById(R.id.input_eliminar_titulo);

        dialogBuilder.setTitle("Eliminar");
        dialogBuilder.setPositiveButton("Eliminar", (dialogInterface, i) -> {
            String titulo = tituloInput.getText().toString();
            int index = listaNovelas.indexOf(titulo);
            if (index != -1) {
                repositorio.eliminarNovela(index);
                listaNovelas.remove(index);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Novela eliminada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Novela no encontrada", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", null);

        dialogBuilder.create().show();
    }

    private void mostrarDetallesNovela(Novela novela) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(novela.getTitulo());
        dialogBuilder.setMessage("Autor: " + novela.getAutor() + "\nAño: " + novela.getAñoPublicacion() + "\nSinopsis: " + novela.getSinopsis());
        dialogBuilder.setPositiveButton(novela.getFavorito() ? "Desmarcar Favorito" : "Marcar Favorito", (dialog, which) -> {
            novela.setFavorito(!novela.getFavorito());
            Toast.makeText(this, novela.getFavorito() ? "Marcado como favorito" : "Desmarcado de favoritos", Toast.LENGTH_SHORT).show();
        });
        dialogBuilder.setNegativeButton("Volver", null);
        dialogBuilder.create().show();
    }

    private void mostrarFavoritas() {
        ArrayList<String> favoritas = new ArrayList<>();
        for (Novela novela : repositorio.obtenerNovelasFavoritas()) {
            favoritas.add(novela.getTitulo());
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Favoritas");
        dialogBuilder.setItems(favoritas.toArray(new String[0]), null);
        dialogBuilder.setPositiveButton("Volver", null);
        dialogBuilder.create().show();
    }
}