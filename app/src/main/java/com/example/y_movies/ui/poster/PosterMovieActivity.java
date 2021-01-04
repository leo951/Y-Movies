package com.example.y_movies.ui.poster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.example.y_movies.models.movie.ApiPosterMovie;
import com.example.y_movies.models.movie.Results;
import com.example.y_movies.ui.adapter.AdapterMovie;
import com.example.y_movies.utils.Constant;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class PosterMovieActivity extends AppActivity {

    private ImageView imageMovie;
    private TextView nameMovie;
    private TextView noteMovie;
    private TextView dateMovie;
    private TextView timeMovie;
    private TextView countryMovie;
    private TextView descMovie;
    private ListView similarList;
    private RequestQueue queue;
    private int movieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster_movie);

        imageMovie = findViewById(R.id.imageMovie);
        nameMovie = findViewById(R.id.nameMovie);
        noteMovie = findViewById(R.id.noteMovie);
        dateMovie = findViewById(R.id.dateMovie);
        timeMovie = findViewById(R.id.timeMovie);
        countryMovie = findViewById(R.id.countryMovie);
        descMovie = findViewById(R.id.descMovie);
        similarList = findViewById(R.id.similarList);
        movieId = getIntent().getExtras().getInt("movie_id");
        queue = Volley.newRequestQueue(this);

        mainRequest();
        similarRequest();
    }

    private void mainRequest() {
        String url = String.format(Constant.URL_POSTER_MOVIE, movieId);

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
                parseJson(new String(error.networkResponse.data));

            }
        });
        queue.add(stringRequest);
    }

    private void similarRequest() {
        String urlSimilar = String.format(Constant.URL_SIMILAR_MOVIE, movieId);
        StringRequest stringRequestSimilary = new StringRequest(Request.Method.GET, urlSimilar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("volley", "onErrorResponse:" + response);

                        parseJsonSimilary(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", "onErrorResponse:" + error);
                parseJson(new String(error.networkResponse.data));

            }
        });
        queue.add(stringRequestSimilary);

    }

    private void parseJson(String json) {
        ApiPosterMovie api = new Gson().fromJson(json, ApiPosterMovie.class);
        nameMovie.setText(api.getTitle());
        descMovie.setText(api.getOverview());
        dateMovie.setText(api.getRelease_date().substring(0, 4));
        noteMovie.setText("Recommended " + api.getVote_average()*10 + "%");
        timeMovie.setText(minuteByHour(api.getRuntime()));
        countryMovie.setText(api.getProduction_countries().get(0).getName());
        String url = String.format(Constant.URL_IMAGE, api.getPoster_path());
            Picasso.get()
                    .load(url)
                    .into(imageMovie);
        }

    public void parseJsonSimilary(String json) {
        List<Results> resultsList = new ArrayList<>();
        ApiMovies api = new Gson().fromJson(json, ApiMovies.class);
        if (api.getResults().isEmpty()){

        }
        for (int i = 0; i < api.getResults().size(); i++){
            resultsList.add(new Results(
                    api.getResults().get(i).getTitle(),
                    api.getResults().get(i).getPoster_path(),
                    api.getResults().get(i).getId()
            ));
        }

        similarList.setAdapter(
                new AdapterMovie(
                        PosterMovieActivity.this,
                        R.layout.item_movie_serie,
                        resultsList
                )
        );
        similarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intentMovie = new Intent(PosterMovieActivity.this, PosterMovieActivity.class);

                int movieId = resultsList.get(position).getId();
                intentMovie.putExtra("movie_id", movieId);
                startActivity(intentMovie);
            }
        });
    }

    private String minuteByHour(int min){
        if (min >= 60){
            int reste = min % 60;
            int countHour = (min - reste) / 60;
            if (reste == 0){
                return String.format("%dh", countHour);
            }
            else {
                return String.format("%dh%d", countHour, reste);
            }

        }else {
            return String.format("%dmin", min);
        }
    }

    public void addPreference(View view) {

    }
}
