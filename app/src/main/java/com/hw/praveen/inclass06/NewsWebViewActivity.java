package com.hw.praveen.inclass06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsWebViewActivity extends AppCompatActivity {
    String newsURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);
        newsURL=getIntent().getExtras().getString("newsURL");

        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl(newsURL);
    }
}
