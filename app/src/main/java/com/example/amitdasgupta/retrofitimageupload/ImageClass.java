package com.example.amitdasgupta.retrofitimageupload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMIT DAS GUPTA on 26-09-2017.
 */

public class ImageClass {
    @SerializedName("Name")
    String name;
    @SerializedName("path")
    String path;
    @SerializedName("response")
    String response;

    public String getResponse() {
        return response;
    }
}
