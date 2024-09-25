package com.example.gestionnovelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gestionnovelas.Novela;

import java.util.List;

public class AdaptadorNovela extends ArrayAdapter<Novela> {

    public AdaptadorNovela(Context context, List<Novela> novelas) {
        super(context, 0, novelas);
    }

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

        if (novela != null) {
            tvTitulo.setText(novela.getTitulo());
            tvAutor.setText(novela.getAutor());
        }

        return convertView;
    }
}
