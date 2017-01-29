package com.example.daniel.picker;

import java.util.List;

/**
 * Created by daniel_giat on 1/29/2017.
 */
public interface OnResultsListner {

    public void onImagesCompleted(List<String> images);
    public void onImagesError(String error);
}
