package com.hw.praveen.inclass06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements GetNewsAsync.INews, View.OnClickListener{

    LinearLayout feed;
    ArrayList<News> newsList;
    LinearLayout.LayoutParams params;
    public static ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        feed = (LinearLayout) findViewById(R.id.news);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("NewsActivity","URL retrieved: "+url);
        pd = new ProgressDialog(this);
        pd.setMessage("Loading Image ...");
        pd.setCancelable(false);
        new GetNewsAsync(this).execute(url);
    }



    @Override
    public void getNews(ArrayList<News> NList)
    {
        newsList = NList;
        Collections.sort(newsList);
        Log.d("main", newsList.toString());
        int i=0;
        for (News news: newsList) {
            TextView text = new TextView(this);
            text.setText(news.getTitle());
            Log.d("demo", news.getTitle());
            text.setTextSize(getResources().getDimension(R.dimen.textsize));
            text.setTag((Integer) i);
            text.setLayoutParams(params);
            text.setOnClickListener(this);
            feed.addView(text);
            Log.d("ddd",i+"");
            i++;
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,NewsDetailsActivity.class);
        int i = (Integer)v.getTag();
        News selectedNews = newsList.get(i);
        intent.putExtra("title",selectedNews.getTitle());
        intent.putExtra("date",selectedNews.getDateStr());
        intent.putExtra("imageURL",selectedNews.getNews_Image_URL());
        intent.putExtra("newsURL",selectedNews.getNews_Item_URL());
        intent.putExtra("desc", selectedNews.getDescription());
        Log.d("title", selectedNews.getTitle());
        Log.d("date",selectedNews.getDateStr());
        Log.d("imageURL",selectedNews.getNews_Image_URL());
        Log.d("newsURL",selectedNews.getNews_Item_URL());
        Log.d("desc",selectedNews.getDescription());
        startActivity(intent);
    }
}


