package com.example.y_movies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preference {
    private static final String PREFERENCE_MOVIE = "";
    private static final String PREFERENCE_SERIE = "";

    private static SharedPreferences getPreferenceMovie(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    private static SharedPreferences getPreferenceSerie(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setMovie(Context context, String movie){
        getPreferenceMovie(context)
                .edit()
                .putString(PREFERENCE_MOVIE, movie)
                .apply();
    }

    public static String getMovie(Context context){
        return getPreferenceMovie(context).getString(PREFERENCE_MOVIE, null);
    }

    public static void setSerie(Context context, String serie){
        getPreferenceSerie(context)
                .edit()
                .putString(PREFERENCE_SERIE, serie)
                .apply();
    }

    public static String getSerie(Context context){
        return getPreferenceSerie(context).getString(PREFERENCE_SERIE, null);
    }
}
