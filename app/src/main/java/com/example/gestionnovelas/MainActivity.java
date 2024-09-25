package com.example.gestionnovelas;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Atributos
    private ListView listViewNovelas;
    private AdaptadorNovela adapter;
    private ArrayList<Novela> listaNovelas;
    private RepositorioNovela repositorio;
    private Button btnAgregar, btnEliminar, btnFavoritas;

    //Método onCreate para inicializar la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar componentes
        listViewNovelas = findViewById(R.id.list_view_novelas);
        btnAgregar = findViewById(R.id.btn_agregar);
        btnEliminar = findViewById(R.id.btn_eliminar);
        btnFavoritas = findViewById(R.id.btn_favoritas);

        //Inicializar la lista de novelas y el repositorio
        listaNovelas = new ArrayList<>();
        adapter = new AdaptadorNovela(this, listaNovelas);
        listViewNovelas.setAdapter(adapter);
        repositorio = new RepositorioNovela(this, adapter);

        //Añadir novela
        btnAgregar.setOnClickListener(v -> mostrarDialogoAgregarNovela());

        //Eliminar novela
        btnEliminar.setOnClickListener(v -> mostrarDialogoEliminarNovela());

        //Ver detalles de la novela seleccionada
        listViewNovelas.setOnItemClickListener((adapterView, view, position, id) -> {
            Novela novela = listaNovelas.get(position);
            mostrarDetallesNovela(novela);
        });

        //Botón para ver novelas favoritas
        btnFavoritas.setOnClickListener(v -> mostrarFavoritas());
    }

    //Método para mostrar el diálogo de agregar novela
    private void mostrarDialogoAgregarNovela() {
        //Crear el diálogo (un dialogo es una ventana que se muestra en pantalla)
        View dialogView = getLayoutInflater().inflate(R.layout.agregar_novela, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        //Referenciar los elementos del diálogo
        EditText tituloInput = dialogView.findViewById(R.id.input_titulo);
        EditText autorInput = dialogView.findViewById(R.id.input_autor);
        EditText añoInput = dialogView.findViewById(R.id.input_año);
        EditText sinopsisInput = dialogView.findViewById(R.id.input_sinopsis);

        //Configurar el diálogo
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

    //Método para mostrar el diálogo de eliminar novela
    private void mostrarDialogoEliminarNovela() {
        //Crear el diálogo (un dialogo es una ventana que se muestra en pantalla)
        View dialogView = getLayoutInflater().inflate(R.layout.eliminar_novela, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        //Referenciar el elemento del diálogo
        EditText tituloInput = dialogView.findViewById(R.id.input_eliminar_titulo);

        //Configurar el diálogo
        dialogBuilder.setTitle("Eliminar");
        dialogBuilder.setPositiveButton("Eliminar", (dialogInterface, i) -> {
            String titulo = tituloInput.getText().toString().trim().toLowerCase(); // Quitar espacios y convertir a minúsculas
            boolean encontrada = false;

            // Bucle para encontrar el título (ignorando mayúsculas y minúsculas)
            for (int index = 0; index < listaNovelas.size(); index++) {
                Novela novela = listaNovelas.get(index);
                if (novela.getTitulo().trim().toLowerCase().equals(titulo)) {
                    //Eliminar la novela del repositorio
                    repositorio.eliminarNovela(index);
                    //Eliminar de la lista visual
                    listaNovelas.remove(index);
                    //Actualizar el adaptador de la lista
                    adapter.notifyDataSetChanged();
                    encontrada = true;
                    Toast.makeText(this, "Novela eliminada", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            //Si no se encuentra la novela, mostrar un mensaje de error
            if (!encontrada) {
                Toast.makeText(this, "Novela no encontrada", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", null);
        dialogBuilder.create().show();
    }


    //Método para mostrar los detalles de la novela seleccionada
    private void mostrarDetallesNovela(Novela novela) {
        //Crear el diálogo (un dialogo es una ventana que se muestra en pantalla)
        View dialogView = getLayoutInflater().inflate(R.layout.detalles_novelas, null);
        TextView tvTitulo = dialogView.findViewById(R.id.tvTitulo);
        TextView tvAutor = dialogView.findViewById(R.id.tvAutor);
        TextView tvAnio = dialogView.findViewById(R.id.tvAnio);
        TextView tvSinopsis = dialogView.findViewById(R.id.tvSinopsis);

        //Establecer los valores en los TextViews
        tvTitulo.setText(novela.getTitulo());
        tvAutor.setText("Autor: " + novela.getAutor());
        tvAnio.setText("Año: " + novela.getAñoPublicacion());
        tvSinopsis.setText("Sinopsis: " + novela.getSinopsis());

        //Configurar el diálogo
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setPositiveButton(novela.getFavorito() ? "Desmarcar Favorito" : "Marcar Favorito", (dialog, which) -> {
            novela.setFavorito(!novela.getFavorito());
            Toast.makeText(this, novela.getFavorito() ? "Marcado como favorito" : "Desmarcado de favoritos", Toast.LENGTH_SHORT).show();
        });
        dialogBuilder.setNegativeButton("Volver", null);
        dialogBuilder.create().show();
    }

    //Método para mostrar las novelas favoritas
    private void mostrarFavoritas() {
        //Obtener las novelas favoritas
        ArrayList<Novela> favoritas = repositorio.obtenerNovelasFavoritas();

        //Crear el diálogo (un dialogo es una ventana que se muestra en pantalla)
        View dialogView = getLayoutInflater().inflate(R.layout.novelas_favoritas, null);
        ListView listViewFavoritas = dialogView.findViewById(R.id.list_view_favoritas);
        ArrayAdapter<String> adapterFavoritas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                obtenerTitulosFavoritas(favoritas));
        listViewFavoritas.setAdapter(adapterFavoritas);

        //Configurar el diálogo
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setPositiveButton("Volver", null);
        dialogBuilder.create().show();
    }

    //Método para obtener los títulos de las novelas favoritas
    private String[] obtenerTitulosFavoritas(ArrayList<Novela> favoritas) {
        String[] titulos = new String[favoritas.size()];
        //Bucle para obtener los títulos de las novelas favoritas recorriendo la lista
        for (int i = 0; i < favoritas.size(); i++) {
            titulos[i] = favoritas.get(i).getTitulo();
        }
        return titulos;
    }
}