package com.example.y_movies.models.serie;

import com.example.y_movies.models.movie.Production_companies;
import com.example.y_movies.models.movie.Production_countries;

import java.util.List;

public class ApiPosterSerie {
    private String poster_path;
    private String name;
    private int id;
    private String overview;
    private String first_air_date;
    private String last_air_date;
    private double vote_average;
    private int number_of_seasons;
    private int number_of_episodes;
    private List<Production_countries> production_countries;

    public ApiPosterSerie(String poster_path, String name, int id, String overview, String first_air_date, String last_air_date, double vote_average, int number_of_seasons, int number_of_episodes, List<Production_countries> production_countries) {
        this.poster_path = poster_path;
        this.name = name;
        this.id = id;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.last_air_date = last_air_date;
        this.vote_average = vote_average;
        this.number_of_seasons = number_of_seasons;
        this.number_of_episodes = number_of_episodes;
        this.production_countries = production_countries;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public List<Production_countries> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<Production_countries> production_countries) {
        this.production_countries = production_countries;
    }

}
