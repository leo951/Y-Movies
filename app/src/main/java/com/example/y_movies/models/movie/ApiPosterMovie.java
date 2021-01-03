package com.example.y_movies.models.movie;

import java.util.List;

public class ApiPosterMovie {
    private String poster_path;
    private String title;
    private int id;
    private String overview;
    private String release_date;
    private double vote_average;
    private int runtime;
    private List<Production_countries> production_countries;
    private List<Production_companies> production_companies;


    public ApiPosterMovie(String poster_path, String title, int id, String overview, String release_date, double vote_average, int runtime, List<Production_countries> production_countries, List<Production_companies> production_companies) {
        this.poster_path = poster_path;
        this.title = title;
        this.id = id;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.runtime = runtime;
        this.production_countries = production_countries;
        this.production_companies = production_companies;
    }

    public List<Production_companies> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<Production_companies> production_companies) {
        this.production_companies = production_companies;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Production_countries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<Production_countries> production_countries) {
        this.production_countries = production_countries;
    }
}
