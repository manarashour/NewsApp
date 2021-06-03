package com.example.a5baaar;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5baaar.model.Article;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class NewsAdpter extends RecyclerView.Adapter<NewsAdpter.NewsViewHolder> {
    private List<Article> list;
    private onNewsClickLisener listener;

    public NewsAdpter(List<Article> list, onNewsClickLisener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        final Article current = list.get(position);
        holder.name.setText(current.getTitle());
        holder.desc.setText(current.getDescription());
        Picasso.get().load(current.getUrlToImage()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
                Intent i = new Intent(holder.itemView.getContext(), InfoActivity.class);
                i.putExtra("data", (Serializable) current);
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public interface onNewsClickLisener {
        void onItemClick(int position);
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView name, desc;
        private ImageView img;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.des);
            img = itemView.findViewById(R.id.img);


        }
    }
}
