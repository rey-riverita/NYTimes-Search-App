package com.codepath.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.codepath.nytimessearch.Article;
import com.codepath.nytimessearch.ArticleArrayAdapter;
import com.codepath.nytimessearch.ArticleFilter;
import com.codepath.nytimessearch.EndlessScrollListener;
import com.codepath.nytimessearch.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity  {



    private final int REQUEST_CODE = 20;
    GridView gvResults;

    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;
    SearchView searchView;

    ArticleFilter filter;
    String query;
    Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle();
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.nytimeslogo);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setupViews();
        filter = new ArticleFilter();




        // Attach the listener to the AdapterView onCreate
        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                customLoadMoreDataFromApi(searchView.getQuery().toString(), page);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });


    }



    // Append more data into the adapter


    public void setupViews() {
        gvResults = (GridView) findViewById(R.id.gvResults);
        articles = new ArrayList<>();
        adapter = new ArticleArrayAdapter(this, articles);
        gvResults.setAdapter(adapter);
//      searchView = new SearchView()

        // hook up listener for grid click
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // create an intent to display the article
                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);

                // get the article to display
                Article article = articles.get(position);

                // pass in the article into intent
                i.putExtra("article", article);

                // launch the activity
                startActivity(i);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search, menu);
        //return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                SearchActivity.this.query = query;
                // perform query here
                customLoadMoreDataFromApi(query, 0);
                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

   /*
        public void onArticleSearch(View view) {
        String query = etQuery.getText().toString();
        }
   */

        // Toast.makeText(this, "Searching for " + query, Toast.LENGTH_LONG).show();

    // Called when ENTER is hit
    public void customLoadMoreDataFromApi(String query, int offset) {

        if (offset == 0){

            adapter.clear();


            gvResults.setOnScrollListener(new EndlessScrollListener() {
                @Override
                public boolean onLoadMore(int page, int totalItemsCount) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to your AdapterView
                    customLoadMoreDataFromApi(searchView.getQuery().toString(), page);
                    // or customLoadMoreDataFromApi(totalItemsCount);
                    return true; // ONLY if more data is actually being loaded; false otherwise.
                }
            });        }

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
        // https://api.nytimes.com/svc/search/v2/articlesearch.json?begin_date=20160112&sort=oldest&fq=news_desk:(Arts,%20Sports)&api-key=227c750bb7714fc39ef1559ef1bd8329

        RequestParams params = new RequestParams(); // To pass in query parameters
        params.put("api-key", "936a2fd33d264d1cb6d092d994e40b35");
        params.put("page", offset);
        params.put("q", query);


        if (filter.beginDate != null){
            params.put("begin_date", filter.getBeginDate());
        }
        if (filter.sort != null){
            params.put("sort", filter.getSort());

        }
        if (filter.newsDeskValue != null){
            params.put("fq", filter.getNewsDeskValue());
        }

        Log.d("SearchActivity", url + "?" + params);

        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) { // Strings together url + params and then uses a JsonHttpResponseHandler to handle Json objects
                Log.d("DEBUG", response.toString());
                JSONArray articleJsonResults = null;


                try {
                    //adapter.clear();
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
                    adapter.addAll(Article.fromJSONArray(articleJsonResults)); // Performs both lines commented out below



                    //articles.addAll(Article.fromJSONArray(articleJsonResults));
                    //adapter.notifyDataSetChanged();
                    Log.d("DEBUG", articles.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onSettingsClick(MenuItem item) {
        Intent intent = new Intent(SearchActivity.this, SettingsActivity.class);
        intent.putExtra("intent", filter);
        intent.putExtra("calendar", c);

        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            filter = (ArticleFilter) data.getExtras().getSerializable("applied_filters");
            c = (Calendar) data.getExtras().getSerializable("calendar");
            Log.d("CALENDAR", c.toString());
            // Toast the name to display temporarily on screen
            //Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
//            Log.d("SearchActivity", url + "?" + params);
            customLoadMoreDataFromApi(query, 0);
        }
    }




}

