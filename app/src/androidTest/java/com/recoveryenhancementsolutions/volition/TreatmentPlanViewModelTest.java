package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "Treatment Plan" viewModel
 */
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanViewModelTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Loads the ViewModel and sets it to use a temporary database for testing
   */
  @Before
  public void loadViewModel() {
    //Set the ViewModel to use a test database instead of the app's real database
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();

    //Fill in supplementary database entries
    final MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
    medicationChoiceEntity.medication = "ABSTAIN";
    db.medicationChoiceDAO().insertMedication(medicationChoiceEntity);

    final QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
    questionnaireEntity.setSeverityLevel("MODERATE");
    db.questionnaireDao().insertQuestionnaire(questionnaireEntity);

    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(TreatmentPlanViewModel.class);

    viewModel.setTestDatabase(db);
    viewModel.generateTreatmentPlan();
    db.treatmentPlanDao().insertTreatmentPlanEntity(viewModel.getTreatmentPlan());
  }

  /**
   * Performs several test involving the Treatment Plan ViewModel
   */
  @Test
  public void testTreatmentPlanViewModel() {

    //Check the values of the treatment plan. Should match the moderate abstinence plan.
    try {
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumCounseling());
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumSupportMeeting());
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumLessons());
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumTreatmentEffectivenessAssessment());
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumOutcomeMeasures());
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumTimeTracking());
      assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumReadingResponse());
      assertEquals(0, LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getNumMedManagement());
      assertEquals("MONTHLY", LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getMedManagementFrequency());
      assertEquals("WEEKLY", LiveDataTestUtility.getNestedLiveDataObj(db.treatmentPlanDao()
          .getTreatmentPlan()).getOutcomeMeasureFrequency());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

  }

  private TreatmentPlanViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "TreatmentPlanViewModelTest";
}
