package in.tanjo.clickgames.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import in.tanjo.clickgames.model.Monster;

public class SampleView extends View {
  Monster mMonster;

  public SampleView(Context context) {
    super(context);
    mMonster = new Monster(context);
    mMonster.setY(0);
    mMonster.setVY(10);
    mMonster.setX(0);
    mMonster.setVX(10);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mMonster.onDraw(canvas);
    invalidate();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (mMonster != null && event != null &&
        mMonster.getX() < event.getX() && event.getX() < mMonster.getX() + mMonster.getWidth() &&
        mMonster.getY() < event.getY() && event.getY() < mMonster.getY() + mMonster.getHeight()) {
      mMonster.onTouchEvent(event);
    }
    return true;
  }
}
