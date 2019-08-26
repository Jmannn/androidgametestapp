package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.os.SystemClock;
import java.util.List;
import java.util.ArrayList;

import static android.content.res.Resources.*;
import static java.lang.Math.*;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    Bullet bullet = null;
    Explosian explosian = null;
    List<Zombie> zombieList = new ArrayList<>();
    Paint paint;
    MainCharacter mainCharacter = new MainCharacter(BitmapFactory.decodeResource(getResources(),R.drawable.soldier));
    /* the score is the absolute  of the currentRemaing-startingTotal */
    private int startingTotal;



    public GameView(Context context){

        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        Zombie zombie;
        for (int i = 0; i < 9; i++){
            zombieList.add(new Zombie(700, 0 + i * 250, BitmapFactory.decodeResource(
                    getResources(),R.drawable.zombie)));
        }
        startingTotal = zombieList.size();
    }

    public void update() {
        mainCharacter.update();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        //TODO: if there is a small time distence between this one and the last one dont fire the gun
        int action = MotionEventCompat.getActionMasked(event);

        if (action == MotionEvent.ACTION_MOVE) {
            mainCharacter.setyTarget((int)event.getY()- (mainCharacter.getImageDimX()/2) );
        } else if (action == MotionEvent.ACTION_UP) {
            if (bullet == null) {
                bullet = new Bullet(BitmapFactory.decodeResource(getResources(), R.drawable.bullet), (int) mainCharacter.getY(),
                        mainCharacter.getImageDimX());
            }
            if (explosian == null) {
                explosian = new Explosian(BitmapFactory.decodeResource(getResources(), R.drawable.explosian), (int) event.getY()
                        , mainCharacter.getImageDimX() / 2, SystemClock.elapsedRealtime());
            }
        }

        return true;

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }

    }
    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);

        if (canvas!= null){
            mainCharacter.draw(canvas);
        }
        for(int i = 0; i<zombieList.size(); i++){
            if (bullet != null && zombieList.get(i).bulletHasHit(bullet.getX(), bullet.getY())){
                zombieList.remove(i);
                i--;
            } else{
                zombieList.get(i).draw(canvas);
            }
        }

        if (bullet != null && bullet.isPastXTarget() == false){
            bullet.draw(canvas);
            bullet.update();
        } else {
            bullet = null;
        }
        if (explosian != null && explosian.finishedExploding(SystemClock.elapsedRealtime()) == false){
            explosian.setY(mainCharacter.getY());
            explosian.draw(canvas);
        } else {
            explosian = null;
        }

        Log.i("SCORE", Integer.toString(zombieList.size()-startingTotal));
        //scoreDraw(abs(zombieList.size()-startingTotal), canvas);

        int x = getSystem().getDisplayMetrics().widthPixels/2;
        int y = getSystem().getDisplayMetrics().heightPixels-2;
        String score = Integer.toString(Math.abs(zombieList.size()-startingTotal));
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);

        canvas.drawText(score, x,y, paint);
        Log.i("TEXT", Integer.toString(10));

    }
}
