package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import java.util.ArrayList;

/**
 * This class abstracts the database away from the view severity level activity and allows it to
 * view LiveData objects taken from the database.
 */
public class QuestionnaireActivityViewModel extends AndroidViewModel {

  /**
   * allows the activity access to the private variable displayState stored in view model to protect
   * from activity reconstruction
   *
   * @return int representing how many questions of the questionnaire have been answered
   */
  public int getDisplayState() {
    return displayState;
  }

  /**
   * allows for the activity to change the value of the display state
   *
   * @param displayState int representing how many questions have been answered
   */
  public void setDisplayState(int displayState) {
    this.displayState = displayState;
  }

  /**
   * allows the activity access to the private variable yesAnswers stored in view model to protect
   * from activity reconstruction
   *
   * @return int representing how many questions of the questionnaire have been answered with the
   * answer yes
   */
  public int getYesAnswers() {
    return yesAnswers;
  }

  /**
   * allows for the activity to keep track of how many answers have been said yes to
   *
   * @param yesAnswers int representing how many questions have been answered with the answer yes
   */
  public void setYesAnswers(int yesAnswers) {
    this.yesAnswers = yesAnswers;
  }

  /**
   * Fills the answer array list with temp values to be changed later, mostly to set the length of
   * the array to have values set later
   */
  public void fillQuestionnaireAnswers() {
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
    questionnaireAnswers.add(false);
  }

  /**
   * Sets a specific location in the questionnaireAnswers array list to a value given by user and
   * sent by the activity
   *
   * @param value boolean representing yes or no given by user
   */
  public void setQuestionnaireAnswers(Boolean value) {
    questionnaireAnswers.set(displayState, value);
  }

  /**
   * This method creates a task to query the database from an asynchronous thread.
   *
   * @param db connection to the database class
   */
  public static void populateAsync(final VolitionDatabase db) {

    final PopulateDbAsync task = new PopulateDbAsync(db);
    task.execute();
  }

  /**
   * This method creates the database.
   *
   * @param application passes in the application to set the view model.
   */
  public QuestionnaireActivityViewModel(Application application) {
    super(application);
    createDb();
  }

  /**
   * This method sets the test database when we run the test code.
   *
   * @param db passes in the database.
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.modelDB = db;
  }

  /**
   * Creates the database.
   */

  public void createDb() {
    modelDB = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Receives boolean variables of answers to each questions in the questionnaire and stores into
   * the entity.
   *
   * @param yesAnswers number of yes answers in the questionnaire.
   * @param severityString name of the severity level based on yes answers in the questionnaire.
   */
  public static void addQuestionnaire(final ArrayList<Boolean> questionnaireAnswers,
      final int yesAnswers, final String severityString) {
    QuestionnaireActivityEntity questionnaireActivityEntity = new QuestionnaireActivityEntity();
    questionnaireActivityEntity.setQ1(questionnaireAnswers.get(0));
    questionnaireActivityEntity.setQ2(questionnaireAnswers.get(1));
    questionnaireActivityEntity.setQ3(questionnaireAnswers.get(2));
    questionnaireActivityEntity.setQ4(questionnaireAnswers.get(3));
    questionnaireActivityEntity.setQ5(questionnaireAnswers.get(4));
    questionnaireActivityEntity.setQ6(questionnaireAnswers.get(5));
    questionnaireActivityEntity.setQ7(questionnaireAnswers.get(6));
    questionnaireActivityEntity.setQ8(questionnaireAnswers.get(7));
    questionnaireActivityEntity.setQ9(questionnaireAnswers.get(8));
    questionnaireActivityEntity.setQ10(questionnaireAnswers.get(9));
    questionnaireActivityEntity.setQ11(questionnaireAnswers.get(10));
    String totalYes = Integer.toString(yesAnswers);
    questionnaireActivityEntity.setTotalYes(totalYes);
    questionnaireActivityEntity.setId(1);

    questionnaireActivityEntity.setSeverityLevel(severityString);
    modelDB.questionnaireModel().insertQuestionnaire(questionnaireActivityEntity);
  }

  /**
   * Calls add questionnaire and sends in the answers to the entity.
   *
   * @param db is the database.
   */
  public static void populateWithData(final VolitionDatabase db) {
    addQuestionnaire(questionnaireAnswers, yesAnswers,
        QuestionnaireActivity.severityString);

  }

  /**
   * Asynchronously processes the database.
   */
  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {


    private final VolitionDatabase modelDB;

    PopulateDbAsync(VolitionDatabase db) {
      modelDB = db;
    }

    @Override
    protected Void doInBackground(final Void... params) {
      populateWithData(modelDB);
      return null;
    }

  }

  private static ArrayList<Boolean> questionnaireAnswers = new ArrayList<>();
  private static VolitionDatabase modelDB;
  private int displayState = 0;
  private static int yesAnswers = 0;
}
