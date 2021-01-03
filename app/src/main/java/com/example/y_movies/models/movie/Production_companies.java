package com.example.y_movies.models.movie;

public class Production_companies {
    private int id;
    private String name;
    private String  logo_path;

    public Production_companies(int id, String name, String logo_path) {
        this.id = id;
        this.name = name;
        this.logo_path = logo_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }
}
