package com.hw.praveen.inclass06;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Raghu on 2/22/16.
 */
public class News implements  Comparable<News>
{
    String news_Item_URL,news_Image_URL,title,description,dateStr;
    Date date;





    @Override
    public String toString() {
        return "News{" +
                "news_Item_URL='" + news_Item_URL + '\'' +
                ", news_Image_URL='" + news_Image_URL + '\'' +
                ", title='" + title + '\'' +
                ", decription='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getNews_Item_URL() {
        return news_Item_URL;
    }

    public void setNews_Item_URL(String news_Item_URL) {
        this.news_Item_URL = news_Item_URL;
    }

    public String getNews_Image_URL() {
        return news_Image_URL;
    }

    public void setNews_Image_URL(String news_Image_URL) {
        this.news_Image_URL = news_Image_URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date1) throws ParseException {

        dateStr = date1;
        SimpleDateFormat formatter = new SimpleDateFormat("E,dd MMM yyyy HH:mm:ss");
        this.date = formatter.parse(date1);

    }

    public String getDateStr(){return dateStr;}





    @Override
    public int compareTo(News another) {
        return another.getDate().compareTo(this.getDate());
    }
}