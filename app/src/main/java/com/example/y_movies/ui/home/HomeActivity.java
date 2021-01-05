package com.example.y_movies.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.y_movies.AppActivity;
import com.example.y_movies.R;
import com.example.y_movies.ui.listing.ListingFavoriteMovie;
import com.example.y_movies.ui.listing.ListingGenreActivity;
import com.example.y_movies.ui.search.SearchMovieActivity;

public class HomeActivity extends AppActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showMovie(View view) {
        Intent intentMovie = new Intent(HomeActivity.this, ListingGenreActivity.class);

        intentMovie.putExtra("isMovie", true);

        startActivity(intentMovie);
    }

    public void showSerie(View view) {
        Intent intentSerie = new Intent(HomeActivity.this, ListingGenreActivity.class);

        intentSerie.putExtra("isMovie", false);

        startActivity(intentSerie);
    }

    public void search(View view) {
        Intent intentSearch = new Intent(HomeActivity.this, SearchMovieActivity.class);

        startActivity(intentSearch);
    }

    public void goFavori(View view) {
        Intent intent = new Intent(HomeActivity.this, ListingFavoriteMovie.class);
        startActivity(intent);
    }
    public void goSearch(View view) {
        Intent intent = new Intent(HomeActivity.this, SearchMovieActivity.class);
        startActivity(intent);
    }
}
