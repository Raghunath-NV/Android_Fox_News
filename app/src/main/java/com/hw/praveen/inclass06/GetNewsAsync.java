package com.hw.praveen.inclass06;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class GetNewsAsync extends AsyncTask<String,Void,ArrayList<News>> {

    INews activity;


    public GetNewsAsync(INews activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        NewsActivity.pd.show();
    }

    @Override
    protected ArrayList<News> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Log.d("Async task", "before connection");
            con.connect();
            Log.d("Async task", "after connection");
            int statusCode = con.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_OK) {
                InputStream in = con.getInputStream();

                return NewsUtil.NewsPullParser.parseNews(in);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return null;

    }

    protected void onPostExecute(ArrayList<News> NewsList) {


        if (NewsList != null) {
            NewsActivity.pd.dismiss();
            activity.getNews(NewsList);
            super.onPostExecute(NewsList);

        }


    }
    static public interface INews
    {
        public void getNews(ArrayList<News> NList);
    }
}
