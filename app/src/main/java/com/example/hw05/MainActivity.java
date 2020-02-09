package com.example.hw05;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button addMovie;
    private Button editMovie;
    private Button deleteMovie;
    private Button showListByYear;
    private Button showListByRating;
    AlertDialog.Builder mBuilder;

    ArrayList<Movie> moviesList = new ArrayList<Movie>();
    HashMap<String,Movie> movieSet = new HashMap<String,Movie>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==001)
        {
            Movie movie= (Movie) data.getExtras().getSerializable("MovieAdded");
            moviesList.add(movie);
            movieSet.put(movie.getName(),movie);
            Log.d("Movie Size", Integer.toString(moviesList.size()));
        }
        if(requestCode==002)
        {
            Movie movie= (Movie) data.getExtras().getSerializable("MovieAdded");
            moviesList.add(movie);
            movieSet.put(movie.getName(),movie);
            Log.d("Mov", Integer.toString(moviesList.size()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Favorite Movies");

        addMovie = findViewById(R.id.button_add);
        editMovie = findViewById(R.id.button_edit);
        deleteMovie = findViewById(R.id.button_delete);
        showListByYear = findViewById(R.id.button_year);
        showListByRating = findViewById(R.id.button_rating);

        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMovie.class);
                startActivityForResult(intent, 001);
            }
        });




        editMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // creating alert dialog

                if (moviesList.size() > 0) {
                 AlertDialog.Builder editDialog = buildAlertDialogEdit();
                    AlertDialog dialog = editDialog.create();
                    dialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "No movie added to edit", Toast.LENGTH_SHORT).show();
                }
            }
        });


        deleteMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (moviesList.size() > 0) {
                    AlertDialog.Builder editDialog = buildAlertDialogDelete();
                    AlertDialog dialog = editDialog.create();
                    dialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "No movie added to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showListByYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moviesList.size()>0)
                {
                Collections.sort(moviesList,Movie.sortByYear);
                Intent intent = new Intent("com.example.hw05.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("moviesByYear",moviesList);
                startActivity(intent);}
                else {
                    Toast.makeText(MainActivity.this, "No Movies in the List", Toast.LENGTH_SHORT).show();
                }
            }
        });

       showListByRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(moviesList.size()>0){
                Collections.sort(moviesList,Movie.sortByRating);
                Intent intent = new Intent("com.example.hw05.intent.action.SEND");
                intent.putExtra("moviesByYear",moviesList);
                startActivity(intent);}
               else {
                   Toast.makeText(MainActivity.this, "No Movies in the List", Toast.LENGTH_SHORT).show();
               }
            }
        });






    }

private  AlertDialog.Builder buildAlertDialogDelete(){
    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);


    mBuilder.setTitle("Pick A Movie");
    ArrayList<String> movieNames = new ArrayList<String>();

    for (int i=0 ; i<moviesList.size(); i++){
        movieNames.add(moviesList.get(i).getName());
    }

    final CharSequence[] cs = movieNames.toArray(new CharSequence[movieNames.size()]);
    Log.d("names movie *******",cs.toString());
    mBuilder.setItems(cs, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Log.d("Selected Movie",moviesList.get(i).getName());
            String movieName = moviesList.get(i).getName();
            moviesList.remove(i);
            Toast.makeText(MainActivity.this, movieName+"  deleted", Toast.LENGTH_LONG).show();

        }
    });
    return mBuilder;


}


    private  AlertDialog.Builder buildAlertDialogEdit(){
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);


        mBuilder.setTitle("Pick A Movie");
        ArrayList<String> movieNames = new ArrayList<String>();

        for (int i=0 ; i<moviesList.size(); i++){
            movieNames.add(moviesList.get(i).getName());
        }

        final CharSequence[] cs = movieNames.toArray(new CharSequence[movieNames.size()]);
        Log.d("names movie *******",cs.toString());
        mBuilder.setItems(cs, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Selected Movie",moviesList.get(i).getName());

                Intent editIntent = new Intent(MainActivity.this,EditActivity.class);
                editIntent.putExtra("EditMovie",movieSet.get(moviesList.get(i).getName()));
                startActivityForResult(editIntent, 002);
                dialogInterface.dismiss();
                moviesList.remove(i);
                //  movieSet.remove(moviesList.get(i).getName());

            }
        });
        return mBuilder;


    }

}
