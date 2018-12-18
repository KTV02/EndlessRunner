package com.example.lkrem.endlessrunnergame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    public GamePanel(Context context) {


        super(context);

        //add the calbback to the surfaceholder to intercept events eg touch

        getHolder().addCallback(this);

        thread=new MainThread(getHolder(),this);

        //makes gamPeanl focusable so it can handle envents
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int heith) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e) {
           e.printStackTrace();
            }
            retry=false;
        }
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    //@Override
    public boolean onTouchEevnt(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void update() {

    }
}





