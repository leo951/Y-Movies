package com.example.y_movies.ui.poster;

import android.content.Intent;
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
import com.example.y_movies.models.serie.Results;
import com.example.y_movies.models.serie.ApiPosterSerie;
import com.example.y_movies.models.serie.ApiSeries;
import com.example.y_movies.ui.adapter.AdapterSerie;
import com.example.y_movies.utils.Constant;
import com.example.y_movies.utils.Preference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class PosterSerieActivity extends AppActivity {

    private ImageView imageSerie;
    private TextView noteSerie;
    private TextView dateSerie;
    private TextView countrySerie;
    private TextView nbSeason;
    private TextView nbEpisode;
    private TextView nameSerie;
    private TextView descSerie;
    private TextView statusSimilar;
    private ListView similarList;
    private RequestQueue queue;
    private ApiPosterSerie api;
    private int serieId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster_serie);

        imageSerie = findViewById(R.id.imageSerie);
        noteSerie = findViewById(R.id.noteSerie);
        dateSerie = findViewById(R.id.dateSerie);
        countrySerie = findViewById(R.id.countrySerie);
        nbSeason = findViewById(R.id.nbSeason);
        nbEpisode = findViewById(R.id.nbEpisode);
        nameSerie = findViewById(R.id.nameSerie);
        descSerie = findViewById(R.id.descSerie);
        statusSimilar = findViewById(R.id.statusSimilar);
        similarList = findViewById(R.id.similarList);
        serieId = getIntent().getExtras().getInt("serie_id");
        queue = Volley.newRequestQueue(this);

        mainRequest();
        similarRequest();
    }

    private void mainRequest() {
        String url = String.format(Constant.URL_POSTER_SERIE, serieId);

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

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void similarRequest() {
        String urlSimilar = String.format(Constant.URL_SIMILAR_SERIE, serieId);
        StringRequest stringRequestSimilary = new StringRequest(Request.Method.GET, urlSimilar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        parseJsonSimilary(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                parseJson(new String(error.networkResponse.data));

            }
        });
        queue.add(stringRequestSimilary);

    }

    private void parseJson(String json) {
        api = new Gson().fromJson(json, ApiPosterSerie.class);
        nameSerie.setText(api.getName());
        descSerie.setText(api.getOverview());
        dateSerie.setText(api.getFirst_air_date().substring(0, 4) + "-" + api.getLast_air_date().substring(0, 4));
        noteSerie.setText("Recommended " + api.getVote_average()*10 + "%");
        nbSeason.setText(api.getNumber_of_seasons() + " Seasons");
        nbEpisode.setText(api.getNumber_of_episodes() + " Episodes");
        if(api.getProduction_countries().isEmpty()){
            countrySerie.setText("");
        }else {
            countrySerie.setText(api.getProduction_countries().get(0).getName());
        }
        String url = String.format(Constant.URL_IMAGE, api.getPoster_path());
        Picasso.get()
                .load(url)
                .into(imageSerie);
    }

    private void parseJsonSimilary(String json) {
        List<Results> resultsList = new ArrayList<>();
        ApiSeries api = new Gson().fromJson(json, ApiSeries.class);
        if (api.getResults().isEmpty()){
            statusSimilar.setText("No Similar movie.");
        }
        else {
            statusSimilar.setText("Similar movie:");
        }
        for (int i = 0; i < api.getResults().size(); i++){
            resultsList.add(new Results(
                    api.getResults().get(i).getOriginal_name(),
                    api.getResults().get(i).getPoster_path(),
                    api.getResults().get(i).getId()
            ));
        }

        similarList.setAdapter(
                new AdapterSerie(
                        PosterSerieActivity.this,
                        R.layout.item_movie_serie,
                        resultsList
                )
        );
        similarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intentSerie = new Intent(PosterSerieActivity.this, PosterSerieActivity.class);

                int serieId = resultsList.get(position).getId();
                intentSerie.putExtra("serie_id", serieId);
                startActivity(intentSerie);
            }
        });
    }

    public void listSerie(View view) {
        super.onBackPressed();
    }
}
