package com.example.y_movies.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.y_movies.AppActivity;
import com.example.y_movies.R;
import com.example.y_movies.ui.listing.ListingGenreActivity;
import com.example.y_movies.ui.search.SearchMovieActivity;

public class MovieOrSerie extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_or_serie);
    }

    public void showMovie(View view) {
        Intent intentMovie = new Intent(MovieOrSerie.this, ListingGenreActivity.class);

        intentMovie.putExtra("isMovie", true);

        startActivity(intentMovie);
    }

    public void showSerie(View view) {
        Intent intentSerie = new Intent(MovieOrSerie.this, ListingGenreActivity.class);

        intentSerie.putExtra("isMovie", false);

        startActivity(intentSerie);
    }

    public void search(View view) {
        Intent intentSearch = new Intent(MovieOrSerie.this, SearchMovieActivity.class);

        startActivity(intentSearch);
    }
}
