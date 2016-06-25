package com.codepath.nytimessearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by riverita on 6/20/16.
 */
public class Article implements Serializable {

    public String getWebUrl() {
        return webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    String webUrl;
    String headline;
    String thumbNail;

    public Article (JSONObject jsonObject) {
        try{
            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia"); // Extract out multimedia from API

            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0); // Just get first one if there is more than one entry in multimedia
                this.thumbNail = "http://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbNail = "";
            }

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    public static ArrayList<Article> fromJSONArray(JSONArray array) { // Makes an arraylist of type Article from a Json Array
        ArrayList<Article> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try{
                results.add(new Article(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

}
