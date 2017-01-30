package com.example.daniel.picker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by daniel_giat on 1/29/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context mcontext;
    List<String> mThumbs;
    LayoutInflater inflater;
    Transformation transformation;

    public MyAdapter(Context context, List<String> Thumbs) {
        this.mcontext=context;
        this.mThumbs = Thumbs;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder vh = new ViewHolder( v );

        return vh;

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String url = mThumbs.get(position);
        //String fixurl  =url.replace("http","https");
        Transformation transformation = new RoundedTransformationBuilder()
                //.borderColor(Color.BLACK)
                //.borderWidthDp(3)
                .cornerRadiusDp(30)
                .oval(false)
                .build();
        //holder.imageview.setImageResource();
        Picasso.with(mcontext)
                .load(url)
                .transform(transformation)
                .into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return mThumbs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        public ViewHolder(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.image);

        }
    }




}
