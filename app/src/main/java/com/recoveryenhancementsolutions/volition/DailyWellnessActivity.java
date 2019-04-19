package com.recoveryenhancementsolutions.volition;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;

/**
 * Allows users to report on their daily wellness with a rating from 0 through 10. Users are
 * provided instant feedback by a TextView at the bottom of the screen with Verbose output of their
 * current selection.
 */
public class DailyWellnessActivity extends AppCompatActivity {

  /**
   * Retrieves the text stored in dailyWellnessResultsView. Only needed for testing.
   *
   * @return A String object containing the text inside dailyWellnessResultsView.
   */
  public String getDailyWellnessResultsText() {
    return dailyWellnessResultsView.getText().toString();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_daily_wellness_land);
    } else {
      setContentView(R.layout.activity_daily_wellness_port);
    }

    final NumberPicker np = findViewById(R.id.daily_wellness_number_picker);
    np.setMinValue(1);
    np.setMaxValue(10);
    np.setValue(5);
    np.setOnValueChangedListener(onValueChangeListener);

    if (lastKnownValue != -1) {
      np.setValue(lastKnownValue);
    }

    outputs = getResources().getStringArray(R.array.daily_wellness_result_strings);
    dailyWellnessResultsView = findViewById(R.id.daily_wellness_results);
    dailyWellnessResultsView.setText(getWellnessString(np.getValue()));

    final BottomNavigationView bottomNavigationView = findViewById(R.id.activity_back_navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

    // Required for the OnNavigationItemSelectedListener.
    // TODO: Replace with an "ActivityNavigationHandler" if approved by Jackson and/or Brady.
    context = this;
  }

  private String getWellnessString(final int rating) {
    lastKnownValue = rating;
    return getResources().getString(R.string.daily_wellness_rating) + " " + outputs[rating - 1];
  }

  private OnNavigationItemSelectedListener onNavigationItemSelectedListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(final @NonNull MenuItem item) {
      startActivity(new Intent(context, ActivityActivity.class));
      return true;
    }
  };

  private OnValueChangeListener onValueChangeListener = new OnValueChangeListener() {

    @Override
    public void onValueChange(final NumberPicker np, final int oldVal, final int newVal) {
      dailyWellnessResultsView.setText(getWellnessString(np.getValue()));
    }
  };

  private Context context;
  private TextView dailyWellnessResultsView;
  private String[] outputs;
  private static int lastKnownValue = -1;
  public static int numberCompleted;
}
