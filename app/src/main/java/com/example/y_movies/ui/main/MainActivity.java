package com.example.y_movies.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.example.y_movies.AppActivity;
import com.example.y_movies.R;
import com.example.y_movies.ui.introduction.MovieOrSerie;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppActivity {

    // déclaration
    private Timer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO : lancement de HomeActivity
                Intent myIntent = new Intent(MainActivity.this, MovieOrSerie.class);
                startActivity(myIntent);
                finish();
            }
        }, 1000);
    }
}