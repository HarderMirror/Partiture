package com.veprojects.musicscanner;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Bitmap;
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
        for(int y = 0; y < img.getHeight() ; y++){
            img.getPixels(pixels,0,img.getWidth(),0,y,img.getWidth(),1);
            float prob = probabilityOfLine(pixels);
            //System.out.printf("Fila %d: %f\n",y,prob);

            if(prob > 1000) {
                for(int x = 0; x < img.getWidth(); x++){
                    newImg.setPixel(x,y,Color.argb(Color.alpha(pixels[x]),255,0,0));

                }
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
        return newImg;
    }
    private float probabilityOfLine(int[] pixels){
        float prob = 0;
        int cont = pixels.length-1;
        while(cont > 0 &&!this.isBlack(pixels[cont])) {
            cont--;
        }
        int finalPixel = cont;
        int init = -1;
        for(int x = 0; x <= finalPixel; x++){
            if(this.isBlack(pixels[x])){
                init = x;
            }
            if(init!=-1){
                if(this.isBlack(pixels[x])) {
                    prob++;
                } else {
                    prob--;
                }
            }
        }
        return  prob;
    }
    private boolean isBlack(int pixel){
        int media = (Color.red(pixel) + Color.green(pixel) + Color.blue(pixel)) /3 + (255-Color.alpha(pixel));

        return media < 254;
    }
}
