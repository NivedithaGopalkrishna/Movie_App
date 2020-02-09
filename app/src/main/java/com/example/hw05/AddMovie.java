package com.example.hw05;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
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


public class AddMovie extends AppCompatActivity {
   Spinner spinner ;
   private SeekBar seekBar;
   private MultiAutoCompleteTextView description;
   private EditText year;
   private EditText imdb;
   private EditText name;
   private Button addMovie;
   Movie movie = new Movie();
   Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        setTitle("Add Movie");

        spinner = findViewById(R.id.spinner);
        seekBar = findViewById(R.id.seekBar);
        description = findViewById(R.id.description);
        year = findViewById(R.id.year);
        imdb = findViewById(R.id.imdb);
        name = findViewById(R.id.name);
        addMovie = findViewById(R.id.button_addMovie);
        seekBar.setProgress(0);
        seekBar.setMax(5);


        String[] genre = {"Action","Animation","Comedy","Documentary","Family","Horror","Crime","Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genre);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
               String selectedGenre = (String) parent.getItemAtPosition(position);
               movie.setSelectedGenre(selectedGenre);
                Log.d("Genre", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddMovie.this, "Add a genre", Toast.LENGTH_SHORT).show();
            }
        });


                addMovie.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        int rating = seekBar.getProgress();
                        movie.setRating(rating);
                        Log.d("rating", Integer.toString(rating));


                        if (name.getText().toString().equals("") || name.getText().toString().length() > 50) {
                            name.setError("Invalid");
                            Toast.makeText(AddMovie.this, "Invalid name", Toast.LENGTH_LONG).show();

                        } else {
                            movie.setName(name.getText().toString());

                        }

                       if (year.getText().toString().equals("") || year.getText().length() != 4) {
                            year.setError("Invalid Year");
                            Toast.makeText(AddMovie.this, "Invalid Year", Toast.LENGTH_SHORT).show();

                        } else {
                            movie.setYear(Integer.parseInt(year.getText().toString()));
                        }


                        if (description.getText().toString().equals("") || description.getText().toString().length() > 5000) {
                            description.setError("Invalid Description");
                            Toast.makeText(AddMovie.this, "Invalid Description", Toast.LENGTH_SHORT).show();

                        } else {
                            movie.setDescription(description.getText().toString());
                        }
                        if (imdb.getText().toString().equals("")) {
                            imdb.setError("Missing");
                            Toast.makeText(AddMovie.this, "Add IMDB link", Toast.LENGTH_SHORT).show();

                        } else {
                            movie.setImdb(imdb.getText().toString());
                        }

                        Log.d("movie *******", movie.toString());

                        intent.putExtra("MovieAdded", movie);
                        setResult(001, intent);
                        finish();

                    }
                });

    }
}
