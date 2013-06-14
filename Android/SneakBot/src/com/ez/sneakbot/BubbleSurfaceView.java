package com.ez.sneakbot;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BubbleSurfaceView extends SurfaceView  
                               implements SurfaceHolder.Callback {
  private SurfaceHolder sh;
  private  BubbleThread thread;
  private Context ctx;
  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  public BubbleSurfaceView(Context context) {
    super(context);
    sh = getHolder();
    sh.addCallback(this);
    paint.setColor(Color.BLUE);
    paint.setStyle(Style.FILL);
    
    ctx = context;
    setFocusable(true); // make sure we get key event
  }
  
  public BubbleThread getThread() {
	    return thread;
	  }
  
  public void surfaceCreated(SurfaceHolder holder) {
    Canvas canvas = sh.lockCanvas();
    canvas.drawColor(Color.BLACK);
    canvas.drawCircle(100, 200, 50, paint);
    sh.unlockCanvasAndPost(canvas);
    
    thread = new BubbleThread(sh, ctx, new Handler());
    thread.setRunning(true);
    thread.start();
  }
  public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) 	  {      
  thread.setSurfaceSize(width, height);
}
  public void surfaceDestroyed(SurfaceHolder holder) {
	    boolean retry = true;
	    thread.setRunning(false);
	    while (retry) {
	      try {
	        thread.join();
	        retry = false;
	      } catch (InterruptedException e) {
	      }
	    }
	  }
  
  
}