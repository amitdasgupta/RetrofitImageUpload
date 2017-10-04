package com.example.amitdasgupta.retrofitimageupload;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AMIT DAS GUPTA on 26-09-2017.
 */

public class ApiClient {
    public static final String Base_Url="http://192.168.43.97/imageupload/";
    public static Retrofit retrofit=null;
    public static Retrofit getApiClient()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
