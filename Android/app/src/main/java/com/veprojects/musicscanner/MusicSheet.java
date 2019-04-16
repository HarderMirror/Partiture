package com.veprojects.musicscanner;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.util.TimingLogger;
import android.widget.ImageView;

import java.util.ArrayList;

public class MusicSheet {

    private ArrayList<Bitmap> imgs;


    public MusicSheet(ArrayList<Bitmap> imgs){
        this.imgs = imgs;


    }

    //GETTERS AND SETTERS

    public ArrayList<Bitmap> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<Bitmap> imgs) {
        this.imgs = imgs;
    }

    //METHODS

    public void convertToDigital(){

        this.readLines(this.getImgs().get(0));
    }
    public Bitmap readLines(Bitmap img){

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap newImg = Bitmap.createBitmap(img.getWidth(),img.getHeight(),conf);
        int[] pixels = new int[img.getWidth()];
        int lineas = 0;
        for(int y = 0; y < img.getHeight() ; y++){
            img.getPixels(pixels,0,img.getWidth(),0,y,img.getWidth(),1);
            float prob = probabilityOfLine(pixels);
            //System.out.printf("Fila %d: %f\n",y,prob);

            if(prob > 20) {

                for(int x = 0; x < img.getWidth(); x++){
                    newImg.setPixel(x,y,Color.argb(Color.alpha(pixels[x]),255,0,0));

                }
                lineas++;

            } else {
                for(int x = 0; x < img.getWidth(); x++){
                    newImg.setPixel(x,y,Color.argb(Color.alpha(pixels[x]),0,0,255));
                }
            }


            /*if(this.isBlack(pixel)){


            }
            newImg.setPixel(x,y,pixel);
            */


        }

        System.out.println("Lineas totales: " +lineas);
        return newImg;
    }
    private float probabilityOfLine(int[] pixels){
        float prob = 0;


        for(int x = 0; x < pixels.length-1; x+=2){

            if(this.isBlack(pixels[x]) && this.isBlack(pixels[x+1])) {
                prob+=2;
            } else {
                prob--;
            }

        }
        //System.out.printf("Final:%d   Init:%d\n",finalPixel,init);
        return  prob < 0 ? 0 : prob/pixels.length*100;
    }
    private boolean isBlack(int pixel){
        int media = (Color.red(pixel) + Color.green(pixel) + Color.blue(pixel)) /3 + (255-Color.alpha(pixel));

        return media <= 140;
    }
}
