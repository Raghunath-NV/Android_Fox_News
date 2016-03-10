package com.hw.praveen.inclass06;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsDetailsActivity extends AppCompatActivity {
    String title,date,imageURL,newsURL,content;
    ProgressDialog pd,pd1;
    TextView dateTextView,titleTextView,contentTextView;
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        date =i.getStringExtra("date");
        imageURL = i.getStringExtra("imageURL");
        newsURL = i.getStringExtra("newsURL");
        content =i.getStringExtra("desc");
        Log.d("date",date);


        pd = new ProgressDialog(this);
        pd.setMessage("Loading Image ...");
        pd.setCancelable(false);
        titleTextView =(TextView)findViewById(R.id.storyTitleTextView);
        titleTextView.setText(title);
        dateTextView =(TextView)findViewById(R.id.storyPubDate);
        contentTextView =(TextView)findViewById(R.id.descriptionContent);
        contentTextView.setText(content);

        iv1 = (ImageView)findViewById(R.id.imageView);

        iv1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NewsDetailsActivity.this,NewsWebViewActivity.class);
                        intent.putExtra("newsURL",newsURL);
                        startActivity(intent);
                    }
                }
        );


        SimpleDateFormat formatter = new SimpleDateFormat("E,dd MMM yyyy HH:mm:ss");
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");


        try {
            Date parsedDate = formatter.parse(date);
            dateTextView.setText(formatter1.format(parsedDate));
        } catch (ParseException e) {
            e.printStackTrace();
            dateTextView.setText(e.toString());
        }




        new GetImageData().execute(imageURL);


    }

    class GetImageData extends AsyncTask<String,Integer,Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream is1=null;

            for(Integer i=0;i<23000000;i++){
                i=i+1;
            }

            try {
                //Given URL, return bitmap
                URL url = new URL(params[0]);

                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setRequestMethod("GET");
                is1 = urlConn.getInputStream();
                Bitmap image = BitmapFactory.decodeStream(is1);
                return image;

            }catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    if(is1!=null){
                        is1.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
            if(aVoid != null){
                iv1.setImageBitmap(aVoid);
            }
            else{
                Log.d("NewsDetailsActivity","image null");
            }


        }
    }

}

