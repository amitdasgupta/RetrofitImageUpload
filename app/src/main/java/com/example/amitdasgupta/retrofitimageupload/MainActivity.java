package com.example.amitdasgupta.retrofitimageupload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
   EditText editText;
    Bitmap bitmap;
    Button chooser,uploader;
    ImageView imageView;
    public final int IMAGE_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooser=(Button)findViewById(R.id.button);
        uploader=(Button)findViewById(R.id.button2);
        uploader.setEnabled(false);
        editText=(EditText)findViewById(R.id.editText);
        imageView=(ImageView)findViewById(R.id.imageView);
        chooser.setOnClickListener(this);
        uploader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==uploader)
        {
       imageUpload();
        }
        else
            if(view==chooser)
            {selectImage();

            }

    }
    void selectImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMAGE_REQUEST&&resultCode==RESULT_OK && data!=null)
        {
            Uri path=data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                chooser.setEnabled(false);
                uploader.setEnabled(true);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private String imageToString()
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagebyte=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagebyte,Base64.DEFAULT);
    }
    private void imageUpload()
    {
        String image=imageToString();
        String title=editText.getText().toString();
        ApiInterface apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        Call<ImageClass> call=apiInterface.uploadImage(title,image);
        call.enqueue(new Callback<ImageClass>() {
            @Override
            public void onResponse(Call<ImageClass> call, Response<ImageClass> response) {
                ImageClass imageClass=response.body();
                Toast.makeText(MainActivity.this,"Server response"+imageClass.getResponse(),Toast.LENGTH_LONG).show();
                imageView.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                chooser.setEnabled(true);
                uploader.setEnabled(false);

            }

            @Override
            public void onFailure(Call<ImageClass> call, Throwable t) {

            }
        });
    }
}
