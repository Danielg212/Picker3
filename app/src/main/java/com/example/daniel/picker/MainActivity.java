package com.example.daniel.picker;

import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, OnResultsListner {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    String mtext;
    // Your API key
    final private String key = "AIzaSyDPBgjk93v1UjNYK-JAeHE3A2F-A8N725w";
    // My Search Engine ID
    final private String cx = "016605007659057693979:myk5rslzoti";
   // private GridView gridView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> mThumbs = new ArrayList<String>();





        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, Thumbnails);*/

       /* gridView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                    // End has been reached
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){

            }
        });*/
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String str = eText.getText().toString();
                //result.setText("Searchin for: " + str);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thumbnails.clear();
                            // looking for
                            String strNoSpaces = str.replace(" ", "+");


                            String url = "https://www.googleapis.com/customsearch/v1?q="+strNoSpaces+"&cx=" + cx + "&searchType=image&fields=items/image&key=" + key;
                           // String url2 = "https://www.googleapis.com/customsearch/v1?key=AIzaSyDPBgjk93v1UjNYK-JAeHE3A2F-A8N725w&cx=016605007659057693979:myk5rslzoti&q=lectures";



                            String result2 = httpGet(url);

                            JSONObject jsonObj = new JSONObject(result2.toString());
                            JSONArray results = jsonObj.getJSONArray("items");

                            for(int i=0; i<results.length(); i++){
                                JSONObject json_data = results.getJSONObject(i);
                                JSONObject thumbnailImage = json_data.getJSONObject("image");
                                Thumbnails.add(thumbnailImage.getString("thumbnailLink"));
                                Log.i("log_tag", "Image: " + thumbnailImage.getString("thumbnailLink"));
                                  }



                           // result.setText(result2);


                        } catch (Exception e) {
                            System.err.println("Error1 " + e.toString());

                        }
                    }


                    private String httpGet(String urlStr) throws IOException {

                        URL url = new URL(urlStr);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        if (conn.getResponseCode() != 200) {
                            throw new IOException(conn.getResponseMessage());
                        }

                        Log.d("search", "Connection status = " + conn.getResponseMessage());

                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while ((line = rd.readLine()) != null) {

                         //   Log.d("search", "Line =" + rd.readLine());
                            sb.append(line + "\n");

                        }
                        rd.close();

                        conn.disconnect();
                        return sb.toString();
                    }
                });

                thread.start();
                gridView.setAdapter(new ImageAdapter(MainActivity.this,Thumbnails));
            }
        });*/


        // gridView.setAdapter(new ImageAdapter(this,urls));

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem serchitem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(serchitem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        mtext = query;
        String strNoSpaces = query.replace(" ", "+");
        String url = "https://www.googleapis.com/customsearch/v1?q="+strNoSpaces+"&cx=" + cx + "&searchType=image&fields=items/image&key=" + key;
        new JsonTask(this).execute(url);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    @Override
    public void onImagesCompleted(List<String> images) {
        GridView gridView= (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this,images));

    }

    @Override
    public void onImagesError(String error) {

    }
}
