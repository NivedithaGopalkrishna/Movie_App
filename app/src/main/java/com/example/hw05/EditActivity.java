package com.example.hw05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

public class EditActivity extends AppCompatActivity {
    Spinner spinner ;
    private SeekBar seekBar;
    private MultiAutoCompleteTextView description;
    private EditText year;
    private EditText imdb;
    private EditText name;
    private Button saveMovie;
    Movie movie = new Movie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Edit Movie");
        name = findViewById(R.id.name_edit);
        year = findViewById(R.id.year_edit);
        imdb= findViewById(R.id.imdb_edit);
        description = findViewById(R.id.description_edit);
        spinner = findViewById(R.id.spinner_edit);
        seekBar = findViewById(R.id.seekBar_edit);
        saveMovie= findViewById(R.id.button_editMovie);

        Intent intent = getIntent();
       final Movie selectedMovie = (Movie)intent.getExtras().getSerializable("EditMovie");
        Log.d("demo edit", selectedMovie.getName());
        name.setText(selectedMovie.getName());
        year.setText(Integer.toString(selectedMovie.getYear()));
        imdb.setText(selectedMovie.getImdb());
        description.setText(selectedMovie.getDescription());
        seekBar.setMax(5);


        String[] genre = {"Action","Animation","Comedy","Documentary","Family","Horror","Crime","Others"};
        final HashMap<String,Integer> mapSet = new HashMap<String,Integer>();
        mapSet.put("Action",0);
        mapSet.put("Animation",1);
        mapSet.put("Comedy",2);
        mapSet.put("Documentary",3);
        mapSet.put("Family",4);
        mapSet.put("Horror",5);
        mapSet.put("Crime",6);
        mapSet.put("Others",7);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genre);
        spinner.setAdapter(adapter);
        spinner.setSelection(mapSet.get(selectedMovie.getSelectedGenre()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                Log.d("Genre",selectedMovie.getSelectedGenre());
                String selectedGenre = (String) parent.getItemAtPosition(position);
                movie.setSelectedGenre(selectedGenre);
                Log.d("Genre", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(EditActivity.this, "Add a genre", Toast.LENGTH_SHORT).show();
            }
        });
        String genreSelected = movie.getSelectedGenre();
//        spinner.setSelection(mapSet.get(genreSelected));
        seekBar.setProgress(selectedMovie.getRating());

        saveMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                movie.setName(name.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                movie.setDescription(description.getText().toString());
                movie.setImdb(imdb.getText().toString());
                movie.setRating(seekBar.getProgress());
                intent.putExtra("MovieAdded",movie);
                setResult(002,intent);
                finish();
            }
        });


    }
}
