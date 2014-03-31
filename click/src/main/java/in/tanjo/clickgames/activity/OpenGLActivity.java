package in.tanjo.clickgames.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLActivity extends Activity implements GLSurfaceView.Renderer {

  private GLSurfaceView mView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mView = new GLSurfaceView(this);
    mView.setRenderer(this);

    setContentView(mView);
  }

  @Override
  protected void onResume() {
    super.onResume();
    mView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mView.onPause();
  }

  @Override
  public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    // ディザを無効化
    gl.glDisable(GL10.GL_DITHER);
    // カラーとテクスチャ座標の補完制度を最も効率的なものにする
    gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
    // バッファクリア時のカラー情報をセット
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    // カリング無効化
    gl.glDisable(GL10.GL_CULL_FACE);
    // 深度テスト無効化
    gl.glDisable(GL10.GL_DEPTH_TEST);
    // アルファ有効化
    gl.glEnable(GL10.GL_BLEND);
    gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    // スムースシェーディングモード
    gl.glShadeModel(GL10.GL_SMOOTH);
  }

  @Override
  public void onSurfaceChanged(GL10 gl, int width, int height) {
    Rect rect = new Rect();
    mView.getWindowVisibleDisplayFrame(rect);

    final float drawWidth = 400;
    final float drawHeight = 225;
    float drawScaleRate = 1.0f;
    {
      // 拡大率更新
      final float widthScale = width / drawWidth;
      final float heightScale = height / drawHeight;

      drawScaleRate = Math.min(widthScale, heightScale);
    }

    final int viewWidth = (int)(drawWidth * drawScaleRate);
    final int viewHeight = (int)(drawHeight * drawScaleRate);
    int drawOffsetX = 0;
    int drawOffsetY = 0;
    {
      // 描画オフセット更新
      drawOffsetX = (int)(width * 0.5f - viewWidth * 0.5f);
      drawOffsetY = (int)(height * 0.5f - viewHeight * 0.5f);
    }
    gl.glViewport(drawOffsetX, drawOffsetY, viewWidth, viewHeight);

    gl.glMatrixMode(GL10.GL_PROJECTION);
    gl.glLoadIdentity();
    gl.glOrthof(0.0f, drawWidth, drawHeight, 0.0f, 1.0f, 10.f);
  }

  @Override
  public void onDrawFrame(GL10 gl) {
    // 描画開始設定
    {
      // クリア
      gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
      // テクスチャ有効化
      gl.glActiveTexture(GL10.GL_TEXTURE0);
      // カメラ座標設定
      GLU.gluLookAt(gl, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
      // 頂点情報有効化
      gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
      gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
    }
  }
}
