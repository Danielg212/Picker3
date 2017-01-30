package com.example.daniel.picker;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//import static com.example.daniel.picker.R.id.gridView;

/**
 * Created by Daniel on 1/29/2017.
 */

public class JsonTask extends AsyncTask<String,String,String> {
    private OnResultsListner mListner;
    List<String> mThumbs = new ArrayList<String>();

    public JsonTask(OnResultsListner onresultslistner){
        mListner=onresultslistner;
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);


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
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

       return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
           // mThumbs.clear();
            JSONArray results  = new JSONArray(s);
            /*JSONObject jsonObj = new JSONObject(s);
            JSONArray results = jsonObj.getJSONArray("items");*/

            for (int i = 0; i < results.length(); i++) {
                JSONObject json_data = results.getJSONObject(i);
              //  JSONObject thumbnailImage = json_data.getJSONObject("image");
               mThumbs.add(json_data.getString("url"));
                Log.i("log_tag", "Image: " + json_data.getString("thumbnailUrl"));
            }
            if (mThumbs != null) {
                mListner.onImagesCompleted(mThumbs);
            }
        } catch (Exception e) {
            System.err.println("Error1 " + e.toString());
            //gridView.(new ImageAdapter(this,urls));

        }
    }
}
