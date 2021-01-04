package com.example.y_movies.utils;

public class Constant {
    public static final String URL_GENRE = "https://api.themoviedb.org/3/genre/movie/list?api_key=39aaae220a288c9e722525ce192b6626&language=en-US";
    public static final String URL_MOVIE_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated?api_key=39aaae220a288c9e722525ce192b6626&language=en-US&page=%d";
    public static final String URL_SERIE_TOP_RATED = "https://api.themoviedb.org/3/tv/top_rated?api_key=39aaae220a288c9e722525ce192b6626&language=en-US&page=%d";
    public static final String URL_POSTER_MOVIE = "https://api.themoviedb.org/3/movie/%d?api_key=39aaae220a288c9e722525ce192b6626&language=en-US";
    public static final String URL_POSTER_SERIE = "https://api.themoviedb.org/3/tv/%d?api_key=39aaae220a288c9e722525ce192b6626&language=en-US";
    public static final String URL_SIMILAR_MOVIE = "https://api.themoviedb.org/3/movie/%d/similar?api_key=39aaae220a288c9e722525ce192b6626&language=en-US&page=1";
    public static final String URL_SIMILAR_SERIE = "https://api.themoviedb.org/3/tv/%d/similar?api_key=39aaae220a288c9e722525ce192b6626&language=en-US&page=1";
    public static final String URL_SEARCH_MOVIE = "https://api.themoviedb.org/3/search/movie?api_key=39aaae220a288c9e722525ce192b6626&language=en-US&query=%s&page=1";
    public static final String URL_SEARCH_SERIE = "https://api.themoviedb.org/3/search/tv?api_key=39aaae220a288c9e722525ce192b6626&language=en-US&page=1&query=%s";
    public static final String URL_IMAGE = "https://image.tmdb.org/t/p/w500%s";
}
