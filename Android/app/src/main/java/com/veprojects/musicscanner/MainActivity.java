package com.veprojects.musicscanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Bitmap> imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgs = new ArrayList<Bitmap>();
        imgs.add(BitmapFactory.decodeResource(this.getResources(),R.drawable.penta10));
        setContentView(R.layout.activity_main);
        MusicSheet ms = new MusicSheet(imgs);
        ImageView imgExpose = findViewById(R.id.imageView);
        imgExpose.setImageBitmap(ms.readLines(imgs.get(0)));


    }
}
