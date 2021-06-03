package com.example.a5baaar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a5baaar.model.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdFragment extends Fragment {

    RecyclerView rc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = view.findViewById(R.id.rcve);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));

        NewsClient.getService().getNews("fr", "c4652d58322344a783a6cea9e37e0707").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                NewsAdpter adpter = new NewsAdpter(response.body().getArticles(), new NewsAdpter.onNewsClickLisener() {
                    @Override
                    public void onItemClick(int position) {
                    }
                });
                rc.setAdapter(adpter);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

                Toast.makeText(getContext(), "No Internet", Toast.LENGTH_LONG).show();

            }


        });
    }
}
