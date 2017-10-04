package com.example.amitdasgupta.retrofitimageupload;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by AMIT DAS GUPTA on 26-09-2017.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("upload.php")
    Call<ImageClass> uploadImage(@Field("title") String title,@Field("image") String image);

}
