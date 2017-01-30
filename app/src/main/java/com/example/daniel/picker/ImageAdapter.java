package com.example.daniel.picker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Daniel on 1/27/2017.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int itemBackground;


    List<String> thumbnails;

    public ImageAdapter(Context c, List<String> results)
    {
        mContext=c;
        thumbnails=results;
    }
    @Override
    public int getCount() {
        return thumbnails.size();
    }

    @Override
    public String getItem(int position) {
        return thumbnails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(240, 240));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        String url = getItem(position);
        String urlfix =url.replace("http","https");
       /* Picasso.with(mContext).load(url).centerCrop().into(imageView);*/

        Picasso.with(mContext)
                .load(urlfix)
                .placeholder(R.drawable.common_full_open_on_phone)
                .fit()
                .centerCrop().into(imageView);
        return imageView;
    }
}
