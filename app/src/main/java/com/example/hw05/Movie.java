package com.example.hw05;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class Movie implements Serializable{

    String name;
    String description;
    int year;
    String imdb;
    int rating;

    //sort by year
    public static Comparator<Movie> sortByYear = new Comparator<Movie>() {

        @Override
        public int compare(Movie obj1, Movie obj2) {

            return obj1.year-obj2.year;

        }
    };

    public static Comparator<Movie> sortByRating = new Comparator<Movie>() {

        @Override
        public int compare(Movie obj1, Movie obj2) {

            return obj2.rating-obj1.rating;

        }
    };

    String selectedGenre;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
    public void setSelectedGenre(String selectedGenre) {
        this.selectedGenre = selectedGenre;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public String getImdb() {
        return imdb;
    }
    public String getSelectedGenre(){
        return selectedGenre;
    }


    public int getRating() {
        return rating;
    }

    public Movie(String name, String description, int year, String imdb, int rating,String selectedGenre) {
        this.name = name;
        this.description = description;
        this.year = year;
        this.imdb = imdb;
        this.rating = rating;
        this.selectedGenre= selectedGenre;
    }
    public Movie(){

    }


}
