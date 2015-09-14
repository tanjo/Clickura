package in.tanjo.clickgames.model;

public class Position {
  private float mX;
  private float mY;

  public Position(float x, float y) {
    mX = x;
    mY = y;
  }

  public float getX() {
    return mX;
  }

  public void setX(float x) {
    mX = x;
  }

  public float getY() {
    return mY;
  }

  public void setY(float y) {
    mY = y;
  }
}
