package com.hw.praveen.inclass06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickView(View v) {
        String url="";
        switch (v.getId()){
            case R.id.most_popular: url = "http://feeds.foxnews.com/foxnews/most-popular";break;
            case R.id.entertainment: url = "http://feeds.foxnews.com/foxnews/entertainment";break;
            case R.id.health: url = "http://feeds.foxnews.com/foxnews/health";break;
            case R.id.lifestyle: url = "http://feeds.foxnews.com/foxnews/section/lifestyle";break;
            case R.id.opinion: url = "http://feeds.foxnews.com/foxnews/opinion";break;
            case R.id.politics: url = "http://feeds.foxnews.com/foxnews/politics";break;
            case R.id.science: url = "http://feeds.foxnews.com/foxnews/science";break;
            case R.id.sports: url = "http://feeds.foxnews.com/foxnews/sports";break;
            case R.id.tech: url = "http://feeds.foxnews.com/foxnews/tech";break;
            case R.id.travel: url = "http://feeds.foxnews.com/foxnews/internal/travel/mixed";break;
            case R.id.us: url = "http://feeds.foxnews.com/foxnews/national";break;
        }

        Log.d("MainActivity","URL selected is:"+url);
        Intent intent = new Intent(this,NewsActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
