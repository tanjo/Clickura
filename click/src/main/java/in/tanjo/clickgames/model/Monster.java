package in.tanjo.clickgames.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import in.tanjo.clickgames.click.R;

public class Monster {

  private int mY = 0;
  private int mVY = 0;
  private int mX = 0;
  private int mVX = 0;
  private boolean mIsRight = false;
  private boolean mIsUp = false;
  private Bitmap mBitmap;
  private Paint mPaint;
  private int mDisplayWidth;
  private int mDisplayHeight;
  private int mImageSize;

  public int getY() {
    return mY;
  }

  public void setY(int y) {
    mY = y;
  }

  public int getVY() {
    return mVY;
  }

  public void setVY(int VY) {
    mVY = VY;
  }

  public boolean isUp() {
    return mIsUp;
  }

  public void setUp(boolean isUp) {
    mIsUp = isUp;
  }

  public int getX() {
    return mX;
  }

  public void setX(int x) {
    mX = x;
  }

  public int getVX() {
    return mVX;
  }

  public void setVX(int VX) {
    mVX = VX;
  }

  public boolean isRight() {
    return mIsRight;
  }

  public void setRight(boolean isRight) {
    mIsRight = isRight;
  }

  public int getDisplayWidth() {
    return mDisplayWidth;
  }

  public void setDisplayWidth(int displayWidth) {
    mDisplayWidth = displayWidth;
  }

  public int getDisplayHeight() {
    return mDisplayHeight;
  }

  public void setDisplayHeight(int displayHeight) {
    mDisplayHeight = displayHeight;
  }

  public int getImageSize() {
    return mImageSize;
  }

  public void setImageSize(int imageSize) {
    mImageSize = imageSize;
  }

  public Bitmap getBitmap() {
    return mBitmap;
  }

  public void setBitmap(Bitmap bitmap) {
    mBitmap = bitmap;
  }

  public Paint getPaint() {
    return mPaint;
  }

  public void setPaint(Paint paint) {
    mPaint = paint;
  }

  public Monster(Context context) {
    mImageSize = 240;
    WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    mDisplayWidth = display.getWidth();
    mDisplayHeight = display.getHeight();
    mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.monster);
    mBitmap = Bitmap.createScaledBitmap(mBitmap, mImageSize, mImageSize, false);
    mPaint = new Paint();
  }

  public void onDraw(Canvas canvas) {
    Log.i("CLICK", mY + " " + mVY);
    if (mY > mDisplayHeight - mImageSize) {
      mIsUp = false;
    } else if (mY < - mImageSize) {
      mIsUp = true;
    }

    if (mIsUp) {
      mY += mVY * Math.random();
    } else {
      mY -= mVY * Math.random();
    }

    Log.i("CLICK", mX + " " + mVX);
    if (mX > mDisplayWidth - mImageSize) {
      mIsRight = false;
    } else if (mX < - mImageSize) {
      mIsRight = true;
    }

    if (mIsRight) {
      mX += mVX * Math.random();
    } else {
      mX -= mVX * Math.random();
    }

    canvas.drawBitmap(mBitmap, mX, mY, mPaint);
  }

  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      if (mVY > mImageSize) {
        mVY = 10;
      } else {
        mVY += mVY;
      }
      if (mVX > mImageSize) {
        mVX = 10;
      } else {
        mVX += mVX;
      }
    }
    return true;
  }

}
