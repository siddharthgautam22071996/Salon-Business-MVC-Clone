package com.abhi.fabkutbusiness;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.Utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class complain_activity extends AppCompatActivity {
    TextView feedback,ft_submit;
    ImageView screenshot;
    String img = "";
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

    }

    public void findviewbyid(){
        feedback=findViewById(R.id.feed_back);
        screenshot=findViewById(R.id.srcschot);
        ft_submit=findViewById(R.id.ft_submit);



    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.srcschot:
                boolean rs = Utility.checkCameraPermission(complain_activity.this);
                System.out.println("::::::::::::::::: :: " + rs);
                if (rs) {
                    galleryIntent();
                }

                break;
            case R.id.feed_back:
                break;
        }
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        img = getStringImage(bm);
        setPhoto(bm);

    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }


    private void setPhoto(Bitmap bitmap) {
        screenshot.setImageBitmap(bitmap);
        System.out.println("imageee :" + img);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
               /* if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals("Take Photo"))
                        cameraIntent();
                    else if (userChoosenTask.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }*/
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                }
                break;

            default:

                if (resultCode == Activity.RESULT_OK) {
                    if (requestCode == SELECT_FILE){
                        onSelectFromGalleryResult(data);

                    }


                }

                break;


        }

    }

}
