package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

public class ClinicalScreenActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_clinical_screen);
    enterName = findViewById(R.id.nameBox);
    enterDate = findViewById(R.id.dateBox);
    enterDaysClean = findViewById(R.id.cleanDaysBox);
    dataViewModel = ViewModelProviders.of(this).get(DemographicDataViewModel.class);
    userActivityViewModel = ViewModelProviders.of(this).get(UserActivityViewModel.class);
    observeName();
    observeLastUseDate();
    Spinner spinner = findViewById(R.id.clinicalSpinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.months_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(onItemSelectedListener);
  }

  private Spinner.OnItemSelectedListener onItemSelectedListener = new Spinner.OnItemSelectedListener() {

    public void onItemSelected(AdapterView<?> parent, View view,
        int pos, long id) {
      String option = parent.getItemAtPosition(pos).toString();
      switch (option) {
        case "January":
          displayMonth(1);
          break;
        case "February":
          displayMonth(2);
          break;
        case "March":
          displayMonth(3);
          break;
        case "April":
          displayMonth(4);
          break;
        case "May":
          displayMonth(5);
          break;
        case "June":
          displayMonth(6);
          break;
        case "July":
          displayMonth(7);
          break;
        case "August":
          displayMonth(8);
          break;
        case "September":
          displayMonth(9);
          break;
        case "October":
          displayMonth(10);
          break;
        case "November":
          displayMonth(11);
          break;
        case "December":
          displayMonth(12);
          break;
        case "Year to Date":
          displayYear();
        default:
          break;
      }
    }

    public void onNothingSelected(AdapterView<?> parent) {
      // Another interface callback
    }
  };

  /**
   * method to be used in testing to access ViewModel to change database from actual to test
   * database
   *
   * @return returns the userActivityViewModel which stores all activities that have been completed
   */
  protected UserActivityViewModel getUserViewModel() {
    return userActivityViewModel;
  }

  /**
   * method to be used in testing to access ViewModel to change database from actual to test
   * database
   *
   * @return returns the demographicDataViewModel which stores user entered data about the user
   */
  protected DemographicDataViewModel getDataViewModel() {
    return dataViewModel;
  }

  private void displayMonth(int month) {

  }

  /**
   * searches database for all activities stored in database and prints them all
   */
  private void displayYear() {
    userActivityViewModel.getAllActivities().observe(this, yearObserver);
  }


  private Observer<List<UserActivityEntity>> yearObserver = new Observer<List<UserActivityEntity>>() {
    @Override
    public void onChanged(final List<UserActivityEntity> s) {
      enterActivities = findViewById(R.id.listOfActivities);
      enterActivities.setText("Activities to date: \n");
      if (!s.isEmpty()) {
        for (int i = 0; i < s.size(); i++) {
          enterActivities.append(s.get(i).toString());
          enterActivities.append("\n");
          Log.e("s: ", s.get(i).toString());
        }
      } else {
        enterActivities.setText(R.string.noActivities);
        Log.e("s: ", s.toString());
      }

    }
  };

  /**
   * Retrieves the liveData string for name and sends it to be displayed in UI
   */
  private void observeName() {
    dataViewModel.returnName().observe(this, new Observer<String>() {
      @Override
      public void onChanged(@Nullable String name) {
        showNameInUI("Name: " + name);
      }
    });
  }

  /**
   * Displays received string into enter name text view
   *
   * @param name string containing Name: and the user name
   */
  private void showNameInUI(final @Nullable String name) {
    enterName.setText(name);
/*    enterName.setText(R.string.name);
    enterName.append(" " + name);
     enterName.setText("tester");
    Log.e("name ", name);*/
  }

  /**
   * retrieves date from database and observes any changes in it to display in UI
   */
  private void observeLastUseDate() {
    dataViewModel.getLastCleanDate().observe(this, new Observer<Date>() {
      @Override
      public void onChanged(@Nullable Date date) {
        showDateInUI(date);
      }
    });
  }

  /**
   * Displays date of last use in UI as well as calculates total days clean and displays that in UI
   * as well
   *
   * @param date the date of last use
   */
  private void showDateInUI(final @Nullable Date date) {
    final int days;
    final String newDays;
    if (date != null) {
      enterDate.append("Date of last use: " + date.toString());
      days = DateConverter.daysBetween(date.getTime(), new Date().getTime());
      newDays = Integer.toString(days);
    } else {
      enterDate.append("Date of last use: " + null);
      newDays = null;
    }
    enterDaysClean.append("Days clean: " + newDays);
  }

  private UserActivityViewModel userActivityViewModel;
  private DemographicDataViewModel dataViewModel;
  private TextView enterName;
  private TextView enterDate;
  private TextView enterDaysClean;
  private TextView enterActivities;
}

