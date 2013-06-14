package com.ez.sneakbot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

class BubbleThread extends Thread {
	  private int canvasWidth = 200;
	  private int canvasHeight = 400;
	  private static final int SPEED = 2;
	  private boolean run = false;
	    
	  private float bubbleX;
	  private float bubbleY;
	  private float headingX;
	  private float headingY;
	  
	  private SurfaceHolder sh;
	  private Context ctx;
	  
	  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  
	  public BubbleThread(SurfaceHolder surfaceHolder, Context context,
	         Handler handler) {
	    sh = surfaceHolder;
	    handler = handler;
	    ctx = context;
	  }
	  public void doStart() {
	    synchronized (sh) {
	      // Start bubble in centre and create some random motion
	      bubbleX = canvasWidth / 2;
	      bubbleY = canvasHeight / 2;
	      headingX = (float) (-1 + (Math.random() * 2));
	      headingY = (float) (-1 + (Math.random() * 2));
	    }
	  }
	  public void run() {
	    while (run) {
	      Canvas c = null;
	      try {
	        c = sh.lockCanvas(null);
	        synchronized (sh) {
	          doDraw(c);
	        }
	      } finally {
	        if (c != null) {
	          sh.unlockCanvasAndPost(c);
	        }
	      }
	    }
	  }
	    
	  public void setRunning(boolean b) { 
	    run = b;
	  }
	  public void setSurfaceSize(int width, int height) {
	    synchronized (sh) {
	      canvasWidth = width;
	      canvasHeight = height;
	      doStart();
	    }
	  }
	  private void doDraw(Canvas canvas) {
	    bubbleX = bubbleX + (headingX * SPEED);
	    bubbleY = bubbleY + (headingY * SPEED);
	    canvas.restore();
	    canvas.drawColor(Color.BLACK);
	    canvas.drawCircle(bubbleX, bubbleY, 50, paint);
	  }
	}