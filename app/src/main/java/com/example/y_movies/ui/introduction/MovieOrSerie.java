package com.example.y_movies.ui.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.y_movies.AppActivity;
import com.example.y_movies.R;
import com.example.y_movies.ui.listing.ListingGenre;

public class MovieOrSerie extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_or_serie);
    }

    public void showMovie(View view) {
        Intent intentMovie = new Intent(MovieOrSerie.this, ListingGenre.class);

        intentMovie.putExtra("isMovie", true);

        startActivity(intentMovie);
    }

    public void showSerie(View view) {
        Intent intentSerie = new Intent(MovieOrSerie.this, ListingGenre.class);

        // passage d'un paramètre
        intentSerie.putExtra("isMovie", false);

        startActivity(intentSerie);
    }
}
