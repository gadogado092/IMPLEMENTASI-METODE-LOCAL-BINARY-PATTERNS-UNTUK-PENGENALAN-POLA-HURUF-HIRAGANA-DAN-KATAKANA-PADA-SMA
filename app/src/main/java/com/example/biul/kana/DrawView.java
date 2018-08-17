package com.example.biul.kana;

/**
 * Created by Biul on 25/04/2016.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.R.color.black;

public class DrawView extends View implements OnTouchListener {


    private Canvas  mCanvas;
    private Path    mPath;
    private Paint       mPaint;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();


    private Bitmap mBitmap;
    public DrawView(Context context)
    {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        setBackgroundColor(Color.WHITE);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(15);
        //15 dan 10
        mCanvas = new Canvas();

        mPath = new Path();
        paths.add(mPath);

        //im=BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher);


    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (Path p : paths){
            canvas.drawPath(p, mPaint);
        }
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 1;

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath = new Path();
        paths.add(mPath);
    }

    public void onClickUndo () {
        if (paths.size()>1)
        {
            //Log.d("Pesan","i paths="+paths.size()+"");
            //undonePaths.add(paths.remove(paths.size()-2));
            paths.remove(paths.size()-2);
            //Log.d("Pesan","i pathssetelah="+paths.size()+"");
            invalidate();
        }
        else
        {
            //Toast.makeText(getContext(),"HABIS",Toast.LENGTH_SHORT).show();

        }
        //toast the user
    }
    public void onClickClear () {

        //int a=paths.size();
        //paths.clear();

        //undonePaths.clear();
        paths.clear();
        invalidate();
        //touch_start(0,0);
        touch_up();
    }
    public void onClickRedo (){
        if (undonePaths.size()>1)
        {

            paths.add(undonePaths.remove(undonePaths.size()-1));

            invalidate();
        }
        else
        {

        }
        //toast the user
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }


}