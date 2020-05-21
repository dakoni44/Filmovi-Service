package org.ftninformatika.filmovi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.ftninformatika.filmovi.net.MyService;
import org.ftninformatika.filmovi.net.model.Detalji;
import org.ftninformatika.filmovi.net.model.Rating;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity  extends AppCompatActivity {

    private void getDetail(String imdbKey){
        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("apikey", "fd705d15");
        queryParams.put("i", imdbKey);


        Call<Detalji> call = MyService.apiInterface().getMovieData(queryParams);
        call.enqueue(new Callback<Detalji>() {
            @Override
            public void onResponse(Call<Detalji> call, Response<Detalji> response) {
                if (response.code() == 200){
                    Log.d("REZ", "200");

                    Detalji resp = response.body();
                    if(resp != null){



                        ImageView image = SecondActivity.this.findViewById(R.id.detalji_slika);

                        Picasso.with(SecondActivity.this).load(resp.getPoster()).into(image);

//                        RatingBar ratingBar = SecondActivity.this.findViewById(R.id.detalji_rating);
//                        String rating = resp.getImdbRating();
//                        ratingBar.setRating(Float.parseFloat(rating));

                        TextView tvRating = SecondActivity.this.findViewById(R.id.detalji_rating);
                        tvRating.setText("IMDB Rating: " + resp.getImdbRating() + "/10");



                        TextView title = SecondActivity.this.findViewById(R.id.detalji_title);
                        title.setText(resp.getTitle());

                        TextView year = SecondActivity.this.findViewById(R.id.detalji_year);
                        year.setText("(" + resp.getYear() + ")");

                        TextView runtime = SecondActivity.this.findViewById(R.id.detalji_runtime);
                        runtime.setText(resp.getRuntime());

                        TextView genre = SecondActivity.this.findViewById(R.id.detalji_genre);
                        genre.setText(resp.getGenre());

                        TextView writer = SecondActivity.this.findViewById(R.id.detalji_writer);
                        writer.setText(resp.getWriter());

                        TextView director = SecondActivity.this.findViewById(R.id.detalji_director);
                        director.setText(resp.getDirector());

                        TextView actors = SecondActivity.this.findViewById(R.id.detalji_actors);
                        actors.setText(resp.getActors());

                        TextView plot = SecondActivity.this.findViewById(R.id.detalji_plot);
                        plot.setText(resp.getPlot());



                    }
                }
            }

            @Override
            public void onFailure(Call<Detalji> call, Throwable t) {
                Toast.makeText(SecondActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String imdbKey = getIntent().getStringExtra(MainActivity.KEY);
        getDetail(imdbKey);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

}
