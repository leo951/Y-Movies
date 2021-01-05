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
import com.example.y_movies.models.movie.ApiPosterMovie;
import com.example.y_movies.models.movie.Results;
import com.example.y_movies.ui.adapter.AdapterMovie;
import com.example.y_movies.ui.home.HomeActivity;
import com.example.y_movies.ui.poster.PosterMovieActivity;
import com.example.y_movies.ui.search.SearchMovieActivity;
import com.example.y_movies.utils.Constant;
import com.example.y_movies.utils.Preference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListingFavoriteMovie extends AppActivity {

    private TextView status;
    private ListView listViewData;
    private List<Results> resultsList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_favorite_movie);

        status = findViewById(R.id.status);
        listViewData = findViewById(R.id.listViewData);
        RequestQueue queue = Volley.newRequestQueue(this);
        String movieId = Preference.getMovie(ListingFavoriteMovie.this);
        String[] listMovieId = movieId.split(",");
        resultsList = new ArrayList<>();
        if(!listMovieId[0].isEmpty()){
            for(int i = 0; i < listMovieId.length; i++){

                String url = String.format(Constant.URL_POSTER_MOVIE, Integer.parseInt(listMovieId[i]));

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
            status.setText("No favorite movie");
        }
    }

    private void parseJson(String json) {
        ApiPosterMovie api = new Gson().fromJson(json, ApiPosterMovie.class);
            resultsList.add(new Results(
                    api.getTitle(),
                    api.getPoster_path(),
                    api.getId()
            ));

        listViewData.setAdapter(
                new AdapterMovie(
                        ListingFavoriteMovie.this,
                        R.layout.item_movie_serie,
                        resultsList
                )
        );
        listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intentMovie = new Intent(ListingFavoriteMovie.this, PosterMovieActivity.class);

                int movieId = resultsList.get(position).getId();
                intentMovie.putExtra("movie_id", movieId);
                startActivity(intentMovie);
            }
        });
    }

    public void removeFavorite(View view) {
        Preference.setMovie(ListingFavoriteMovie.this, "");
        Intent intentMovie = new Intent(ListingFavoriteMovie.this, HomeActivity.class);
        startActivity(intentMovie);
    }
    public void goFavori(View view) {
        Intent intent = new Intent(ListingFavoriteMovie.this, ListingFavoriteMovie.class);
        startActivity(intent);
    }
    public void goSearch(View view) {
        Intent intent = new Intent(ListingFavoriteMovie.this, SearchMovieActivity.class);
        startActivity(intent);
    }
    public void goHome(View view) {
        Intent intent = new Intent(ListingFavoriteMovie.this, HomeActivity.class);
        startActivity(intent);
    }
}
