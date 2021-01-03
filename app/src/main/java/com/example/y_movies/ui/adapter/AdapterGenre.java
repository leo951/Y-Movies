package com.example.y_movies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.y_movies.R;
import com.example.y_movies.models.genre.Genres;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterGenre extends ArrayAdapter<Genres> {

    private int resId;

    public AdapterGenre(@NonNull Context context, int resource, @NonNull List<Genres> objects) {
        super(context, resource, objects);

        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // déclaration du ViewHolder
        ViewHolder myViewHolder;

        if(convertView == null) {
            myViewHolder = new ViewHolder(); // instance

            // 1) chargement du layout R.layout.item_restaurant
            convertView = LayoutInflater.from(getContext()).inflate(resId, null);

            // 2) récupération des vues (élements graphiques)
            myViewHolder.textViewCategory = convertView.findViewById(R.id.textViewCategory);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        // 3) données (Restaurant)
        Genres item = getItem(position);

        // 4) affichage (setText)
        myViewHolder.textViewCategory.setText(item.getName());

        return convertView;
    }

    class ViewHolder {
        TextView textViewCategory;
    }

}
