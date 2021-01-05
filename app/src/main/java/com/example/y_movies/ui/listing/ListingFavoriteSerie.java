package com.example.y_movies.ui.listing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.y_movies.AppActivity;
import com.example.y_movies.R;
import com.example.y_movies.models.serie.Results;
import com.example.y_movies.models.serie.ApiPosterSerie;
import com.example.y_movies.ui.adapter.AdapterSerie;
import com.example.y_movies.ui.home.HomeActivity;
import com.example.y_movies.ui.poster.PosterSerieActivity;
import com.example.y_movies.ui.search.SearchMovieActivity;
import com.example.y_movies.utils.Constant;
import com.example.y_movies.utils.Preference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListingFavoriteSerie extends AppActivity {

    private TextView status;
    private ListView listViewData;
    private List<Results> resultsList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_favorite_serie);

        status = findViewById(R.id.status);
        listViewData = findViewById(R.id.listViewData);
        RequestQueue queue = Volley.newRequestQueue(this);
        String serieId = Preference.getSerie(ListingFavoriteSerie.this);
        System.out.println(serieId);
        String[] listSerieId = serieId.split(",");
        resultsList = new ArrayList<>();
        if(!listSerieId[0].isEmpty()){
            for (int i = 0; i < listSerieId.length; i++) {

                String url = String.format(Constant.URL_POSTER_SERIE, Integer.parseInt(listSerieId[i]));

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                parseJson(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        parseJson(new String(error.networkResponse.data));

                    }
                });
                queue.add(stringRequest);
            }
        }else{
            status.setText("No favorite serie");
        }
    }

    private void parseJson(String json) {
        ApiPosterSerie api = new Gson().fromJson(json, ApiPosterSerie.class);
            resultsList.add(new Results(
                    api.getName(),
                    api.getPoster_path(),
                    api.getId()
            ));

        listViewData.setAdapter(
                new AdapterSerie(
                        ListingFavoriteSerie.this,
                        R.layout.item_movie_serie,
                        resultsList
                )
        );
        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intentSerie = new Intent(ListingFavoriteSerie.this, PosterSerieActivity.class);

                int serieId = resultsList.get(position).getId();
                intentSerie.putExtra("serie_id", serieId);
                startActivity(intentSerie);
            }
        });
    }
    public void favoriMovie(View view) {
        Intent intent = new Intent(ListingFavoriteSerie.this, ListingFavoriteMovie.class);
        startActivity(intent);
    }

    public void removeFavorite(View view) {
        Preference.setSerie(ListingFavoriteSerie.this, "");
        Intent intent = new Intent(ListingFavoriteSerie.this, HomeActivity.class);
        startActivity(intent);
    }
    public void goFavori(View view) {
        Intent intent = new Intent(ListingFavoriteSerie.this, ListingFavoriteMovie.class);
        startActivity(intent);
    }
    public void goSearch(View view) {
        Intent intent = new Intent(ListingFavoriteSerie.this, SearchMovieActivity.class);
        startActivity(intent);
    }
    public void goHome(View view) {
        Intent intent = new Intent(ListingFavoriteSerie.this, HomeActivity.class);
        startActivity(intent);
    }
}
