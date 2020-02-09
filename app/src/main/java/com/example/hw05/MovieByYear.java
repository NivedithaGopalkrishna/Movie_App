package com.example.hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieByYear extends AppCompatActivity {
    private TextView title;
    private TextView description;
    private TextView rating;
    private TextView genre;
    private TextView imdb;
    private TextView year;
    ArrayList<Movie> movieList;
    int counter =0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_by_year);
        setTitle("Movie By Year");
        title = findViewById(R.id.editText_name);
        description= findViewById(R.id.editText_des);
        rating = findViewById(R.id.editText3);
        genre= findViewById(R.id.editText_genre);
        imdb= findViewById(R.id.editText_imdb);
        year= findViewById(R.id.editText_year);

        ImageButton last = findViewById(R.id.imageButton);
        ImageButton next = findViewById(R.id.imageButton2);
        ImageButton first = findViewById(R.id.imageButton3);
        ImageButton previous = findViewById(R.id.imageButton5);
        Intent intent = getIntent();
       movieList= (ArrayList<Movie>) intent.getExtras().getSerializable("moviesByYear");
        Log.d("MovieList", Integer.toString(movieList.size()));

        setMovie(0);



        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter =  movieList.size()-1;
                setMovie(counter);

            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter =  0;
                setMovie(counter);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 counter ++;
                 if(counter<movieList.size()-1){
                setMovie(counter);}
                 else{
                     setMovie(movieList.size()-1);
                 }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter --;
                 if(counter <0){
                     setMovie(0);
                     counter = 0;
                 }
                    setMovie(counter);


            }
        });


        findViewById(R.id.button_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }


    private void setMovie(int index){
        Movie selectedMovie = movieList.get(index);
        title.setText(selectedMovie.getName());
        year.setText(Integer.toString(selectedMovie.getYear()));
        imdb.setText(selectedMovie.getImdb());
        description.setText(selectedMovie.getDescription());
        genre.setText(selectedMovie.getSelectedGenre());
        rating.setText(Integer.toString(selectedMovie.getRating()));
    }
}
