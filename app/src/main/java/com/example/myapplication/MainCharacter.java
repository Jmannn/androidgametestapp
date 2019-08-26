package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.lang.Math;

public class MainCharacter {
    private final int MOVE_VELOCITY = 20;
    private Bitmap image;
    private int imageDimX;
    private int imageDimY;
    private int x, y;
    private int xTarget, yTarget;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public MainCharacter(Bitmap img){
        image = img;
        imageDimX = image.getWidth();
        imageDimY = image.getHeight();
        x = 0;
        y = 0;
    }
    public int getImageDimX(){
        return imageDimX;
    }

    public int getY(){
        return y;
    }
    public void setxTarget(int x) {
        this.xTarget = x;
    }
    public void setyTarget(int y){
        this.yTarget = y;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x,y, null);
    }
    public void update(){
        if(Math.abs(y-yTarget) < MOVE_VELOCITY/2){
            //Do nothing
        } else if (y >yTarget){
            y-= MOVE_VELOCITY;
        } else if (y < yTarget){
            y+= MOVE_VELOCITY;
        }
    }
}
