package com.abhi.fabkutbusiness.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.abhi.fabkutbusiness.billing.view.InvoiceActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by siddharthgautam on 05/08/18.
 */

public class PdfGenrate {
    private static File pictureFile;

    public static File saveBitMap(Context context, View drawView){
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Fabkut");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("ATG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() +File.separator+"/1.jpg";
        pictureFile = new File(filename);
        Bitmap bitmap =getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        //scanGallery( context,pictureFile.getAbsolutePath());
        return pictureFile;
    }
    //create bitmap from view and returns it
    private static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
    //
    private static void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[] { path },null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void imageToPDF(Context context) throws FileNotFoundException {
        String dirpath;


        //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        try {

            Document document = new Document();
            File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Fabkut");
            String temp= String.valueOf(System.currentTimeMillis());
            dirpath = pictureFileDir.toString();
            PdfWriter.getInstance(document, new FileOutputStream(dirpath + "/"+temp+".pdf")); //  Change pdf's name.
            document.open();
            Image img = Image.getInstance(pictureFileDir.getPath() + File.separator + "/1.jpg");
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / img.getWidth()) * 100;
            img.scalePercent(scaler);
            img.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            document.add(img);
            document.close();
            Toast.makeText(context, "Slip saved successfully!..", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, NavigationMainActivity.class));
            File delete = new File(pictureFileDir.getPath() + File.separator + "/1.jpg");
            if (delete.exists()){
                delete.delete();
            }

            ((Activity)context).finish();
        } catch (Exception e) {
            Toast.makeText(context, "Unable To Save!..", Toast.LENGTH_SHORT).show();
        }
    }

}
