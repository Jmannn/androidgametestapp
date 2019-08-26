package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Explosian {

    private Bitmap image;
    private int y;
    private int x;
    private long timeCreated;

    public Explosian(Bitmap img, int y, int soldierWidth, long timeCreated){
        image = img;
        this.y = y;
        x = soldierWidth;
        this.timeCreated = timeCreated;
    }
    public void setY(int y){
        this.y = y;
    }

    public boolean finishedExploding(long currentTime){
        if ((currentTime - timeCreated) < 100){
            Log.d("DEBUG", "finished Exploding FALSE timeCreated: " + timeCreated +
                    " currentTime: " +currentTime);
            return false;
        } else{
            Log.d("DEBUG", "finished Exploding TRUE timeCreated: " + timeCreated +
                    " currentTime: " +currentTime);
            return true;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x+200,y+280, null);
    }



}
