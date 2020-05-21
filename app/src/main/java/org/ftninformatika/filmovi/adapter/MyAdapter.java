package org.ftninformatika.filmovi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ftninformatika.filmovi.MainActivity;
import org.ftninformatika.filmovi.R;
import org.ftninformatika.filmovi.SecondActivity;
import org.ftninformatika.filmovi.net.model2.Example;
import org.ftninformatika.filmovi.net.model2.Search;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    Context context;
    ArrayList<Search> searchItem;




    public MyAdapter(Context context, ArrayList<Search> searchItem ) {
        this.context = context;
        this.searchItem = searchItem;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvTitle.setText(searchItem.get(position).getTitle());
        holder.tvYear.setText(searchItem.get(position).getYear());
        holder.tvType.setText(searchItem.get(position).getType());
        Picasso.with(context).load(searchItem.get(position).getPoster()).into(holder.ivMalaSlika);

    }

    @Override
    public int getItemCount() {
        return searchItem.size();
    }

    public Search get(int position) {
        return searchItem.get(position);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView tvTitle;
        private TextView tvYear;
        private TextView tvType;

        private ImageView ivMalaSlika;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMalaSlika = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvType = itemView.findViewById(R.id.tvType);


        }

    }

}
