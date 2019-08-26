package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.lang.Math;

public class Bullet {
    private final int BULLET_VELOCITY = 50;
    //gonna make all sprites 100 and want it to hit center
    private final int XTARGET = Resources.getSystem().getDisplayMetrics().widthPixels-50;
    private boolean pastXTarget = false;
    private Bitmap image;
    private int x, y;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public Bullet(Bitmap img, int y, int soldierWidth){
        image = img;
        this.y = y+315;
        x = x + soldierWidth/2 + 200;
    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x,y, null);
    }
    public void update(){
        x += BULLET_VELOCITY;
        if (x>XTARGET){
            pastXTarget = true;
        }
    }
    public boolean isPastXTarget(){
        return pastXTarget;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
}
