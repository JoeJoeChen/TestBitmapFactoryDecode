package com.example.testbitmapfactorydecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.example.testbitmapfactorydecode.common.BitmapDecondeUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity
{
    private static final int MAXNUMOFPIXELS = 128;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
//        Bitmap bitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.xx));
//        iv.setImageBitmap(bitmap);

        BitmapFactory.Options opts = new BitmapFactory.Options();

        String imageFile = "/mnt/sdcard/xx.jpg";
        
        File f = new File(imageFile);
        try
        {
            InputStream in = new FileInputStream(f);
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, opts);
        opts.inSampleSize = BitmapDecondeUtil.computeSampleSize(opts, -1, MAXNUMOFPIXELS * MAXNUMOFPIXELS);
        opts.inJustDecodeBounds = false;
        
        try
        {
            Bitmap bmp = BitmapFactory.decodeFile(imageFile, opts);
            iv.setImageBitmap(bmp);
        } catch (OutOfMemoryError err)
        {
            err.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
