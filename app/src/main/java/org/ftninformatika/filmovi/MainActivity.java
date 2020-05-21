package org.ftninformatika.filmovi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ftninformatika.filmovi.adapter.MyAdapter;
import org.ftninformatika.filmovi.fragment.DetaljiFragment;
import org.ftninformatika.filmovi.net.MyService;
import org.ftninformatika.filmovi.net.model2.Example;
import org.ftninformatika.filmovi.net.model2.Search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnSearch;
    EditText movieName;
    int position = 0;
    private DetaljiFragment detaljiFragment;

    public static String KEY  = "KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.btn_search);
        movieName = findViewById(R.id.ime_filma);
        recyclerView = findViewById(R.id.rvLista);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Search movie = adapter.get(position);
                Intent i = new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra(KEY,movie.getImdbID());
                startActivity(i);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        search();
    }

    private void search() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovieByName(movieName.getText().toString());
            }
        });
    }

    private void getMovieByName(String name) {
        Map<String, String> query = new HashMap<String, String>();
        query.put("apikey", "fd705d15");
        query.put("s", name);

        Call<Example> call = MyService.apiInterface().getMovieByName(query);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.code() == 200) {
                    Example searches = response.body();


                    ArrayList<Search> search = new ArrayList<>();

                    for (Search e : searches.getSearch()) {
                        search.add(e);
                    }


                    searches.getResponse();

                    layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);

                    adapter = new MyAdapter(MainActivity.this, search);
                    recyclerView.setAdapter(adapter);



                    Toast.makeText(MainActivity.this, "Prikaz filmova", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Nema filmova", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("position", position);
    }

}