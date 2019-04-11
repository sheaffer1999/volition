package com.recoveryenhancementsolutions.volition;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ActivityActivity extends AppCompatActivity {

  /**
   * OnCreate method that initializes objects and the screen to be used in the onClick methods.
   *
   * @param savedInstanceState Saved instance state of the phone
   */
  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      setContentView(R.layout.activity_activity_land);
      isPortrait = false;

    } else {
      setContentView(R.layout.activity_activity_port);
      isPortrait = true;
    }

    final Button teaButton = findViewById(R.id.TEA);
    final Button lessonButton = findViewById(R.id.Lesson);
    final Button journalButton = findViewById(R.id.Journal);
    final Button eduButton = findViewById(R.id.Edu_);
    final Button wellnessButton = findViewById(R.id.DailyWellness);
    final Button cleanButton = findViewById(R.id.CleanTracker);

    //When clicked, this button will take the user to the TEA Activity
    teaButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(
            new Intent(ActivityActivity.this, TreatmentAssessmentActivity.class));
      }
    });

    //When clicked, this button will take the user to the Lesson Activity
    lessonButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, LessonActivity.class));
      }
    });

    //When clicked, this button will take the user to the Journal Activity
    journalButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, JournalActivity.class));
      }
    });

    //When clicked, this button will take the user to EDU Activity
    eduButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, EDUActivity.class));
      }
    });

    //When clicked, this button will take the user to the Daily Wellness Activity
    wellnessButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, DailyWellnessActivity.class));
      }
    });

    //When clicked, this button will take the user to the Report Use Activity
    cleanButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(final View view) {
        startActivity(new Intent(ActivityActivity.this, ReportUseActivity.class));
      }
    });

    viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);

    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);

    bottomNavigationView = findViewById(R.id.core_navigation);
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
    CoreNavigationHandler.link(bottomNavigationView, this);

  }

  /**
   * Recreates the observer but using a testing database. Should only be used for testing.
   *
   * @param db A VolitionDatabase test object.
   */
  public void onCreateTest(final VolitionDatabase db) {
    viewModel = ViewModelProviders.of(this).get(TreatmentPlanViewModel.class);
    viewModel.setTestDatabase(db);
    viewModel.getTreatmentPlan().observe(this, treatmentPlanObserver);
  }


  /**
   * Observes the treatment plan table in the database. Replaces the local treatment plan with an
   * updated copy. Compare number of times an activity needs to be completed as per the treatment
   * plan vs the number of times the user has completed the activity. Update UI with markers
   * accordingly.
   */

  private Observer<TreatmentPlanEntity> treatmentPlanObserver = new Observer<TreatmentPlanEntity>() {
    @Override
    public void onChanged(final TreatmentPlanEntity newTreatmentPlanEntity) {
      /*
        A treatment plan entity to handle updates to the database.
       */
      try {

        int numberOfTeasFromPlan = newTreatmentPlanEntity.getNumTreatmentEffectivenessAssessment();
        int numberOfLessonsFromPlan = newTreatmentPlanEntity.getNumLessons();
        int numberOfReportUseFromPlan;
        int numberOfJournalsFromPlan;
        int numberOfEdusFromPlan;
        int numberOfDailyWellnessFromPlan;

        int numberOfUserTeasCompleted = TreatmentAssessmentActivity.numberCompleted;
        int numberOfUserLessonsCompleted = LessonActivity.numberCompleted;
        int numberOfUserReportUseCompleted = ReportUseActivity.numberCompleted;
        int numberOfUserJournalsCompleted = JournalActivity.numberCompleted;
        int numberOfUserEdusCompleted = EDUActivity.numberCompleted;
        int numberOfUserDailyWellnessCompleted = DailyWellnessActivity.numberCompleted;
        if (numberOfUserTeasCompleted >= numberOfTeasFromPlan) {
          if (isPortrait) {
            findViewById(R.id.teaCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.teaCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(R.id.teaIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.teaIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
        if (numberOfUserLessonsCompleted >= numberOfLessonsFromPlan) {
          if (isPortrait) {
            findViewById(R.id.lessonCompletedPortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.lessonCompletedLandscape).setVisibility(View.VISIBLE);
          }
        } else {
          if (isPortrait) {
            findViewById(R.id.lessonIncompletePortrait).setVisibility(View.VISIBLE);
          } else {
            findViewById(R.id.lessonIncompleteLandscape).setVisibility(View.VISIBLE);
          }
        }
      } catch (NullPointerException e) {
        Log.d("Activity Activity", "onChanged: " + Log.getStackTraceString(e));
      }


    }
  };

  /*
   * Restores the CoreNavigationHandler to it's default state for this page.
   */
  @Override
  public void onResume() {
    super.onResume();
    bottomNavigationView.setSelectedItemId(R.id.core_navigation_activity);
  }

  private TreatmentPlanViewModel viewModel;
  private boolean isPortrait = false;
  private BottomNavigationView bottomNavigationView;
}

