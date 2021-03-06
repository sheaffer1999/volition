package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import java.util.Calendar;
import java.util.Date;

/**
 * ViewModel for "DemographicDataEntity" in the database.
 */
public class DemographicDataViewModel extends AndroidViewModel {

  /**
   * Constructor for the ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public DemographicDataViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Sets a test database for the ViewModel. This should only be used for unit testing this
   * ViewModel.
   *
   * @param db The VolitionDatabase to use for testing the ViewModel
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  /**
   * Inserts a new DemographicDataEntity into the database.
   *
   * @param demographicDataEntity The entity to be inserted.
   */
  public void insertDemographicData(final DemographicDataEntity demographicDataEntity) {
    new insertAsyncTask(db.demographicDataDao()).execute(demographicDataEntity);
  }

  /**
   * Updates the date of last use and the date of the last usage report
   *
   * @param cleanDay A Calendar object representing the date of last use
   * @param reportDay A Calendar object representing the date of the report
   */
  public void updateLastCleanDate(final Calendar cleanDay, final Calendar reportDay) {
    new UpdateDaysCleanAsync(db.demographicDataDao()).execute(cleanDay, reportDay);
  }

  /**
   * Retrieves all of the patient's demographic data
   *
   * @return A LiveData object containing a DemographicDataEntity containing all of the patient's
   * demographic data
   */
  public LiveData<DemographicDataEntity> getAllDemographicData() {
    return db.demographicDataDao().getAllDemographicData();
  }

  /**
   * Retrieves the last date clean as stored in the database.
   *
   * @param reportDay A Calendar object representing the date of the report
   */
  public void updateLastReportDate(final Calendar reportDay) {
    new UpdateDaysCleanAsync(db.demographicDataDao()).execute(reportDay);
  }

  public LiveData<String> returnName() {
    return db.demographicDataDao().queryPatientName();
  }

  /**
   * Retrieves the last clean date from the database
   *
   * @return A LiveData object containing the user's 'last clean' Date
   */
  public LiveData<Date> getLastCleanDate() {
    return db.demographicDataDao().queryLastCleanDate();
  }

  /**
   * Retrieves the last report date from the database
   *
   * @return A LiveData object containing the Date of the user's last Clean Tracker report
   */
  public LiveData<Date> getLastReportDate() {
    return db.demographicDataDao().queryLastReportDate();
  }

  /**
   * Asynchronous task for inserting DemographicData entities into the database
   */
  private static class insertAsyncTask extends AsyncTask<DemographicDataEntity, Void, Void> {

    insertAsyncTask(final DemographicDataDAO dao) {
      demographicDataDao = dao;
    }

    @Override
    protected Void doInBackground(final DemographicDataEntity... params) {
      demographicDataDao.deleteDemographicInfo();
      demographicDataDao.insertDemographicInfo(params[0]);
      return null;
    }

    private final DemographicDataDAO demographicDataDao;
  }

  /**
   * Asynchronous task for updating the last clean date
   */
  private static class UpdateDaysCleanAsync extends AsyncTask<Calendar, Void, Void> {

    UpdateDaysCleanAsync(final DemographicDataDAO dao) {
      demographicDataDAO = dao;
    }

    @Override
    protected Void doInBackground(final Calendar... params) {
      if (params.length == 2) {
        Log.e("DemoDataViewModel", "Log Date: " + params[0].getTime().toString());
        demographicDataDAO.queryUpdateLastCleanDate(params[0].getTime(), params[1].getTime());
      }
      if (params.length == 1) {
        Log.e("DemoDataViewModel", "Log Date " + params[0].getTime().toString());
        demographicDataDAO.queryUpdateLastReportDate(params[0].getTime());
      }
      return null;
    }

    private DemographicDataDAO demographicDataDAO;
  }

  private VolitionDatabase db;
}