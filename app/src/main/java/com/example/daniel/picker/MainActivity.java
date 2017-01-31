package com.example.daniel.picker;

import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

public class MainActivity extends AppCompatActivity implements OnResultsListner {


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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<String> mThumbs = new ArrayList<>();
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // mAdapter = new MyAdapter(this,mThumbs);
        // mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Button btn = (Button) findViewById(R.id.button);


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


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        // MenuItem serchitem = menu.findItem(R.id.action_search);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    searchView.clearFocus();
                    //mtext = query;
                    //String strNoSpaces = query.replace(" ", "+");
                    //String url = "https://www.googleapis.com/customsearch/v1?q="+strNoSpaces+"&cx=" + cx + "&searchType=image&fields=items/image&key=" + key;
                    String url = "http://jsonplaceholder.typicode.com/photos?albumId=1";
                    new JsonTask(MainActivity.this).execute(url);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }



    /* @Override
    public boolean onQueryTextSubmit(String query) {
        i++;
        Log.i("log_tag", "In: onQueryTextSubmit for the "+i+" time");

        //mtext = query;
        //String strNoSpaces = query.replace(" ", "+");
        //String url = "https://www.googleapis.com/customsearch/v1?q="+strNoSpaces+"&cx=" + cx + "&searchType=image&fields=items/image&key=" + key;




        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }*/


    @Override
    public void onImagesCompleted(List<String> images) {
        // GridView gridView= (GridView) findViewById(R.id.gridView);
        //gridView.setAdapter(new ImageAdapter(this,images));
        //mThumbs.addAll(images);
        // mThumbs.add("http://wallpaper-gallery.net/images/image/image-13.jpg");
        mThumbs.add("http://kingofwallpapers.com/images/images-044.jpg");
        mThumbs.add("http://www.w3schools.com/css/img_fjords.jpg");
        mThumbs.add("http://wallpaper-gallery.net/images/image/image-18.png");
        mThumbs.add("http://www.freedigitalphotos.net/images/img/homepage/87357.jpg");
        mThumbs.add("https://s3-us-west-1.amazonaws.com/powr/defaults/image-slider2.jpg");
        mThumbs.add("https://www.smashingmagazine.com/wp-content/uploads/2016/01/07-responsive-image-example-castle-7-opt.jpg");
        mThumbs.add("http://images.freeimages.com/images/thumbs/61e/glass-of-wine-outdoor-party-image-1632473.jpg");
        mThumbs.add("https://www.nasa.gov/sites/default/files/styles/image_card_4x3_ratio/public/thumbnails/image/25126583741_d7db3f6905_o.jpg?itok=jYAcBCmb");
        mThumbs.add("http://www.online-image-editor.com//styles/2014/images/example_image.png");
        mThumbs.add("http://feelgrafix.com/data/images/images-1.jpg");
        mThumbs.add("http://feelgrafix.com/data/images/images-4.jpg");
        mThumbs.add("http://www.menucool.com/slider/jsImgSlider/images/image-slider-2.jpg");
        mThumbs.add("https://upload.wikimedia.org/wikipedia/en/b/bb/Maiden_Flight_of_Long_March_6_Rocket.jpg");
        mThumbs.add("http://www.dam7.com/Images/Cats/images/myspace-cats-images-0122.jpg");
        mThumbs.add("http://www.online-image-editor.com//styles/2014/images/example_image.png");


        mAdapter = new MyAdapter(this,mThumbs);
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        //mAdapter = new MyAdapter(this,mThumbs);
        // mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onImagesError(String error) {

    }

}
