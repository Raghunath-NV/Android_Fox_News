package com.hw.praveen.inclass06;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

public class NewsUtil {


static public class NewsPullParser {


    static ArrayList<News> parseNews(InputStream in) throws XmlPullParserException, IOException {
     Boolean flag =false;
        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(in, "UTF-8");
        ArrayList<News> newsList = new ArrayList<News>();
        News news = null;
        int event = parser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT)
        {

            switch (event)
            {
                case XmlPullParser.START_TAG:
                    Log.d("txt",parser.getName().toString());
                    if(parser.getName().equals("item"))
                    {;
                        news = new News();
                        flag =true;

                    }
                    else if(parser.getName().equals("link") && flag)
                    {
                        news.setNews_Item_URL(parser.nextText().trim());
                    }
                    else if(parser.getName().equals("media:thumbnail") && flag)
                    {
                        news.setNews_Image_URL(parser.getAttributeValue(null,"url"));
                    }
                    else if(parser.getName().equals("title")&& flag)
                    {
                        news.setTitle(parser.nextText().trim());
                    }
                    else if(parser.getName().equals("pubDate")&& flag)
                    {
                        try {
                            news.setDate(parser.nextText().trim());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(parser.getName().equals("description") && flag)
                    {
                        news.setDescription(parser.nextText().trim());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("item"))
                    {
                        newsList.add(news);
                        news =null;
                    }

            }
            event=parser.next();
        }
            return newsList;
        }


    }
}




