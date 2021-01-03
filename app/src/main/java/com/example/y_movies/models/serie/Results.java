package com.example.y_movies.models.serie;

public class Results {
    private int[] genre_ids;
    private String original_name;
    private String poster_path;
    private int id;

    public Results(String original_name, String poster_path, int id) {
        this.original_name = original_name;
        this.poster_path = poster_path;
        this.id = id;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
