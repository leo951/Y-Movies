package com.example.y_movies;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.y_movies.ui.intro.MovieOrSerie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSupportActionBar() != null) {
            if(!(this instanceof MovieOrSerie)) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
