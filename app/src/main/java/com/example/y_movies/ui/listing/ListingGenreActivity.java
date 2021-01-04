package com.example.y_movies.ui.listing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.y_movies.models.genre.ApiGenres;
import com.example.y_movies.models.genre.Genres;
import com.example.y_movies.ui.adapter.AdapterGenre;
import com.example.y_movies.utils.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListingGenreActivity extends AppActivity {

    private TextView movieOrSerie;
    private ListView listGenre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_genre);


        movieOrSerie = findViewById(R.id.movieOrSerie);
        listGenre = findViewById(R.id.listGenre);

        boolean isMovie = getIntent().getExtras().getBoolean("isMovie");
        if(isMovie){
            movieOrSerie.setText(R.string.genre_movie);
        }else{
            movieOrSerie.setText(R.string.genre_serie);
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format(Constant.URL_GENRE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e("volley", "onErrorResponse:" + response);

                            parseJson(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("volley", "onErrorResponse:" + error);
                }
            });
            queue.add(stringRequest);
        }

    private void parseJson(String json) {

        ApiGenres api = new Gson().fromJson(json, ApiGenres.class);

        List<Genres> genreList = new ArrayList<>();


        for (int i = 0; i < api.getGenres().size(); i++){
            genreList.add(new Genres(
                    api.getGenres().get(i).getName()
            ));
        }
        listGenre.setAdapter(
                new AdapterGenre(
                        ListingGenreActivity.this,
                        R.layout.item_genre,
                        genreList
                )
        );
        listGenre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int item = api.getGenres().get(position).getId();
                String name = api.getGenres().get(position).getName();
                if(getIntent().getExtras() != null) {
                    boolean isMovie = getIntent().getExtras().getBoolean("isMovie");

                    if (isMovie) {
                        Intent intentGenres = new Intent(ListingGenreActivity.this, ListingMovieActivity.class);
                        intentGenres.putExtra("genre_id", item);
                        intentGenres.putExtra("genre_name", name);
                        startActivity(intentGenres);
                    } else {
                        Intent intentGenres = new Intent(ListingGenreActivity.this, ListingSerieActivity.class);
                        intentGenres.putExtra("genre_id", item);
                        intentGenres.putExtra("genre_name", name);
                        startActivity(intentGenres);
                    }
                }

            }
        });

    }
}