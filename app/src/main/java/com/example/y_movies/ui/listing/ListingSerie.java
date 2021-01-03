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
import com.example.y_movies.models.serie.ApiSeries;
import com.example.y_movies.models.serie.Results;
import com.example.y_movies.ui.adapter.AdapterSerie;
import com.example.y_movies.ui.introduction.MovieOrSerie;
import com.example.y_movies.ui.poster.PosterSerie;
import com.example.y_movies.utils.Constant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class ListingSerie extends AppActivity {
    private TextView whatGenre;
    private ListView listViewData;
    private TextView numberResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_serie);


        whatGenre = findViewById(R.id.whatGenre);
        listViewData = findViewById(R.id.listViewData);
        numberResult = findViewById(R.id.numberResult);

        String GenreName = getIntent().getExtras().getString("genre_name");
        int Genre_id = getIntent().getExtras().getInt("genre_id");

        whatGenre.setText(GenreName);
            RequestQueue queue = Volley.newRequestQueue(this);
            List<Results> resultsList = new ArrayList<>();
            for (int page = 1; page < 200; page++) {
                String url = String.format(Constant.URL_SERIE_TOP_RATED, page);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                ApiSeries api = new Gson().fromJson(response, ApiSeries.class);
                                for (int i = 0; i < api.getResults().size(); i++){
                                    for (int j = 0; j < api.getResults().get(i).getGenre_ids().length; j++){
                                        if (api.getResults().get(i).getGenre_ids()[j] == Genre_id){
                                            resultsList.add(new Results(
                                                    api.getResults().get(i).getOriginal_name(),
                                                    api.getResults().get(i).getPoster_path(),
                                                    api.getResults().get(i).getId()
                                            ));
                                        }
                                    }
                                }
                                listViewData.setAdapter(
                                        new AdapterSerie(
                                                ListingSerie.this,
                                                R.layout.item_movie_serie,
                                                resultsList
                                        )
                                );
                                String ShowNbResult = resultsList.size() + " Resultats";
                                numberResult.setText(ShowNbResult);
                                listViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Intent intentSerie = new Intent(ListingSerie.this, PosterSerie.class);
                                        int serieId = resultsList.get(position).getId();
                                        intentSerie.putExtra("serie_id", serieId);
                                        startActivity(intentSerie);
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
    }

