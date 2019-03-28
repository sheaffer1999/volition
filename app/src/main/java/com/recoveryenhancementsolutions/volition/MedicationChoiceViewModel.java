package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

/**
 * View Model for the Medication Choice Activity.
 */

public class MedicationChoiceViewModel extends AndroidViewModel {

  /**
   * Constructor for the Medication Choice View Model.
   *
   * @param application Application object for the View Model.
   */

  public MedicationChoiceViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Asynchronously populate the database.
   *
   * @param db the Volition Database to use for testing.
   */

  public static void populateAsync(final VolitionDatabase db){
    insertAsyncTask insert = new insertAsyncTask(med);
    updateAsyncTask update = new updateAsyncTask(med);
    insert.execute();
    update.execute();
  }

  /**
   * Set the test database for this View Model.
   *
   * @param db the Volition Database to use for testing.
   */

  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  /**
   * Retrieves the medication from the MedicationChoice table.
   *
   * @return Returns LiveData of type MedicationChoiceEntity.
   */

  public LiveData<MedicationChoiceEntity> getMedication(){
    return db.medicationChoiceDAO().getMedication();
  }

  /**
   * Inserts a medication into the MedicationChoice table.
   *
   * @param medication Medication object for the View Model.
   */

  public void insertMedication(final MedicationChoiceEntity medication) {
    new insertAsyncTask(db.medicationChoiceDAO()).execute(medication);
  }

  private static class insertAsyncTask extends AsyncTask<MedicationChoiceEntity, Void, Void> {

    private MedicationChoiceDAO asyncTaskDao;

    insertAsyncTask(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      asyncTaskDao.insertMedication(params[0]);
      return null;
    }
  }

  public void updateMedication(final MedicationChoiceEntity medication){
    new updateAsyncTask(db.medicationChoiceDAO()).execute(medication);
  }

  private static class updateAsyncTask extends AsyncTask<MedicationChoiceEntity, Void, Void>{

    private MedicationChoiceDAO asyncTaskDao;

    updateAsyncTask(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      asyncTaskDao.updateMedication(params[0]);
      return null;
    }
  }
  private static MedicationChoiceDAO med;
  private static VolitionDatabase db;
}
