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
import com.example.y_movies.models.movie.ApiMovies;
import com.example.y_movies.models.movie.Results;
import com.example.y_movies.ui.adapter.AdapterMovie;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.y_movies.ui.home.HomeActivity;
import com.example.y_movies.ui.poster.PosterMovieActivity;
import com.example.y_movies.ui.search.SearchMovieActivity;
import com.example.y_movies.utils.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListingMovieActivity extends AppActivity {
    private TextView whatGenre;
    private ListView listViewDataMovie;
    private TextView numberResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_movie);


        whatGenre = findViewById(R.id.whatGenre);
        listViewDataMovie = findViewById(R.id.listViewDataMovie);
        numberResult = findViewById(R.id.numberResult);

        String GenreName = getIntent().getExtras().getString("genre_name");
        int Genre_id = getIntent().getExtras().getInt("genre_id");

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        whatGenre.setText(GenreName);
        RequestQueue queue = Volley.newRequestQueue(this);
        List<Results> resultsList = new ArrayList<>();

        for (int page = 1; page < 200; page++) {

            String url = String.format(Constant.URL_MOVIE_TOP_RATED, page);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                ApiMovies api = new Gson().fromJson(response, ApiMovies.class);
                                for (int i = 0; i < api.getResults().size(); i++){
                                    for (int j = 0; j < api.getResults().get(i).getGenre_ids().length; j++){
                                        if (api.getResults().get(i).getGenre_ids()[j] == Genre_id){
                                            resultsList.add(new Results(
                                                    api.getResults().get(i).getTitle(),
                                                    api.getResults().get(i).getPoster_path(),
                                                    api.getResults().get(i).getId()
                                            ));
                                        }
                                    }
                                }
                                String ShowNbResult = resultsList.size() + " Resultats";
                                numberResult.setText(ShowNbResult);
                                listViewDataMovie.setAdapter(
                                        new AdapterMovie(
                                                ListingMovieActivity.this,
                                                R.layout.item_movie_serie,
                                                resultsList
                                        )
                                );
                                listViewDataMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intentMovie = new Intent(ListingMovieActivity.this, PosterMovieActivity.class);

                                        int movieId = resultsList.get(position).getId();
                                        intentMovie.putExtra("movie_id", movieId);
                                        startActivity(intentMovie);
                                    }
                                });
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.e("volley", "onErrorResponse:" + error);
                    }
                });
                queue.add(stringRequest);
            }

        }
    public void goFavori(View view) {
        Intent intent = new Intent(ListingMovieActivity.this, ListingFavoriteMovie.class);
        startActivity(intent);
    }
    public void goSearch(View view) {
        Intent intent = new Intent(ListingMovieActivity.this, SearchMovieActivity.class);
        startActivity(intent);
    }
    public void goGenre(View view) {
        Intent intent = new Intent(ListingMovieActivity.this, ListingGenreActivity.class);
        intent.putExtra("isMovie", true);
        startActivity(intent);
    }
    }
