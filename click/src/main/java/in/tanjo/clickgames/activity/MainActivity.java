package in.tanjo.clickgames.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import in.tanjo.clickgames.click.R;
import in.tanjo.clickgames.view.SampleView;

public class MainActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    setContentView(new SampleView(this, Color.WHITE));
  }
}
