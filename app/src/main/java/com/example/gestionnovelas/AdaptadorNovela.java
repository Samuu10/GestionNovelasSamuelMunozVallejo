package com.example.gestionnovelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class AdaptadorNovela extends ArrayAdapter<Novela> {

    //Constructor que hereda de ArrayAdapter y recibe una lista de novelas
    public AdaptadorNovela(Context context, List<Novela> novelas) {
        super(context, 0, novelas);
    }

    //Método para obtener la vista de cada elemento de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obtener la novela actual
        Novela novela = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.novela_item, parent, false);
        }

        //Referenciar los TextViews y asignar valores
        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvAutor = convertView.findViewById(R.id.tvAutor);

        //Establecer los valores en los TextViews
        if (novela != null) {
            tvTitulo.setText(novela.getTitulo());
            tvAutor.setText(novela.getAutor());
        }

        return convertView;
    }
}