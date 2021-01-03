package com.example.y_movies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.y_movies.R;
import com.example.y_movies.models.serie.Results;
import com.example.y_movies.utils.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterSerie extends ArrayAdapter<Results> {

    private int resId;

    public AdapterSerie(@NonNull Context context, int resource, @NonNull List<Results> objects) {
        super(context, resource, objects);

        resId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder myViewHolder;

        if(convertView == null) {
            myViewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(resId, null);
            myViewHolder.imageMovie = convertView.findViewById(R.id.imageMovie);
            myViewHolder.nameMovie = convertView.findViewById(R.id.nameMovie);

            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (ViewHolder) convertView.getTag();
        }
        Results item = getItem(position);
        String url = String.format(Constant.URL_IMAGE, item.getPoster_path());
        myViewHolder.nameMovie.setText(item.getOriginal_name());
        Picasso.get()
                .load(url)
                .into(myViewHolder.imageMovie);
        return convertView;
    }

    class ViewHolder {
        TextView nameMovie;
        ImageView imageMovie;

    }

}
