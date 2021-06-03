package com.example.a5baaar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a5baaar.model.Article;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView t1 = findViewById(R.id.name);
        TextView t2 = findViewById(R.id.des);
        TextView t3 = findViewById(R.id.dese);
        ImageView I1 = findViewById(R.id.img);
        if (getIntent() != null) {
            Article a = (Article) getIntent().getSerializableExtra("data");
            t1.setText(a.getTitle());
            t2.setText(a.getPublishedAt());
            t3.setText(a.getDescription());
            Picasso.get().load(a.getUrlToImage()).into(I1);

        }

    }
}