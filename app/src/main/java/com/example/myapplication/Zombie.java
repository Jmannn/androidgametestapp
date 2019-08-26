package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.lang.Math;

public class Zombie {

    private Bitmap image;
    private int imageDimX;
    private int imageDimY;
    private int x, y;

    public Zombie(int x, int y, Bitmap img){

        image = img;
        imageDimX = image.getWidth();
        imageDimY = image.getHeight();
        this.x = x;
        this.y = y;

    }
    /* returns 1 if it has hit, 0 if not */
    public boolean bulletHasHit(int bulletX, int bulletY){

        if(bulletX<imageDimX+x && bulletX > x && bulletY<imageDimY+y && bulletY > y){
            return true;
        }
        return false;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x,y, null);
    }

}
//check position
//nullify this object in GameView if bulletHasHit returns 1