package com.example.y_movies.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

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
import com.example.y_movies.ui.home.HomeActivity;
import com.example.y_movies.ui.listing.ListingFavoriteMovie;
import com.example.y_movies.ui.poster.PosterMovieActivity;
import com.example.y_movies.utils.Constant;
import com.example.y_movies.utils.FastDialog;
import com.example.y_movies.utils.Network;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class SearchMovieActivity extends AppActivity {

    private EditText editText;
    private ListView listViewDataMovie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_movie);

        editText = findViewById(R.id.editText);
        listViewDataMovie = findViewById(R.id.listViewDataMovie);
    }

    public void submit(View view) {
        if(editText.getText().toString().isEmpty()){
            FastDialog.showDialog(
                    SearchMovieActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "You must enter a movie."
            );
            return;
        }
        if(!Network.isNetworkAvailable(SearchMovieActivity.this)){
            FastDialog.showDialog(
                    SearchMovieActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "You don't have a network."
            );
            return;
        }
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format(Constant.URL_SEARCH_MOVIE, editText.getText().toString());
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                                parseJson(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
    }

    private void parseJson(String json) {
        List<Results> resultsList = new ArrayList<>();
        ApiMovies api = new Gson().fromJson(json, ApiMovies.class);
        if(api.getResults().isEmpty()){
            FastDialog.showDialog(
                    SearchMovieActivity.this,
                    FastDialog.SIMPLE_DIALOG,
                    "Sorry no result."
            );
            return;
        }
        for (int i = 0; i < api.getResults().size(); i++){
            resultsList.add(new Results(
                    api.getResults().get(i).getTitle(),
                    api.getResults().get(i).getPoster_path(),
                    api.getResults().get(i).getId()
            ));
        }

        listViewDataMovie.setAdapter(
                new AdapterMovie(
                        SearchMovieActivity.this,
                        R.layout.item_movie_serie,
                        resultsList
                )
        );
        listViewDataMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intentMovie = new Intent(SearchMovieActivity.this, PosterMovieActivity.class);
                    int movieId = resultsList.get(position).getId();
                    intentMovie.putExtra("movie_id", movieId);
                    startActivity(intentMovie);
            }
        });
    }

    public void searchSerie(View view) {
        Intent intentMovie = new Intent(SearchMovieActivity.this, SearchSerieActivity.class);

        startActivity(intentMovie);
    }
    public void goFavori(View view) {
        Intent intentFavori = new Intent(SearchMovieActivity.this, ListingFavoriteMovie.class);
        startActivity(intentFavori);
    }
    public void goSearch(View view) {
        Intent intentFavori = new Intent(SearchMovieActivity.this, SearchMovieActivity.class);
        startActivity(intentFavori);
    }
    public void goHome(View view) {
        Intent intentFavori = new Intent(SearchMovieActivity.this, HomeActivity.class);
        startActivity(intentFavori);
    }
}
