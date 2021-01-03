package com.example.y_movies.models.movie;

import java.util.List;

public class Results {
    private int[] genre_ids;
    private String title;
    private String poster_path;
    private int id;

    public Results(String title, String poster_path, int id) {
        this.title = title;
        this.poster_path = poster_path;
        this.id = id;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
