package com.recoveryenhancementsolutions.volition;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import java.text.DateFormat;
import java.util.Calendar;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.CheckBox;


/**
 * Class for running activity_create_profile.xml Which includes two pop-up calendars
 */

public class CreateProfileActivity extends AppCompatActivity {


  /*
   * All of these public methods take in the current view @c
   * Then they check if the Corresponding RadioButton has been selected
   * If the RadioButton has been selected the corresponding field in the database is set to true
   */
  public void addSupportListener() {
    radioSupport = (RadioButton) findViewById(R.id.radioSupport);
    radioSupport.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setPersonInRecovery(false);
        }
      }
    });
  }

  public void addClientListener() {
    radioClient = (RadioButton) findViewById(R.id.radioClient);
    radioClient.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setPersonInRecovery(true);
        }
      }
    });
  }

  public void addHeroinListener() {
    radioHeroin = (RadioButton) findViewById(R.id.radioHeroin);
    radioHeroin.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseHeroin(true);
        }
      }
    });
  }

  public void addOpiatesListener() {
    radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
    radioOpiates.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseOpiateOrSynth(true);
        }
      }
    });
  }

  public void addAlocholListener() {
    radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
    radioAlcohol.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseAlcohol(true);
        }
      }
    });
  }

  public void addCocaineListener() {
    radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
    radioCocaine.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseCrackOrCocaine(true);
        }
      }
    });
  }

  public void addMarijuanaListener() {
    radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
    radioMarijuana.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseMarijuana(true);
        }
      }
    });
  }

  public void addMethListener() {
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    radioMeth.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseMethamphetamine(true);
        }
      }
    });
  }

  public void addBenListener() {
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    radioBen.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseBenzo(true);
        }
      }
    });
  }

  public void addTranqListener() {
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    radioTranquilizers.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          data.setUseNonBeznoTrang(true);
        }
      }
    });
  }

  public void addSedativesListener() {
    radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
    radioSedatives.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((RadioButton) v).isChecked()) {
          // data.setUseBenzo(true); No sedatives in DemogrpahicDataEntity
        }
      }
    });
  }

  public void addInhanlentsListener() {
    radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
    radioInhalants.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (((CheckBox) v).isChecked()) {
          data.setUseInhalants(true);
        }
      }
    });
  }

  public void addAllListeners() {
    radioSupport = (RadioButton) findViewById(R.id.radioSupport);
    radioClient = (RadioButton) findViewById(R.id.radioClient);
    radioHeroin = (RadioButton) findViewById(R.id.radioHeroin);
    radioInhalants = (RadioButton) findViewById(R.id.radioInhalants);
    radioSedatives = (RadioButton) findViewById(R.id.radioSedatives);
    radioTranquilizers = (RadioButton) findViewById(R.id.radioTranquilizers);
    radioBen = (RadioButton) findViewById(R.id.radioBen);
    radioMeth = (RadioButton) findViewById(R.id.radioMeth);
    radioMarijuana = (RadioButton) findViewById(R.id.radioMarijuana);
    radioAlcohol = (RadioButton) findViewById(R.id.radioAlcohol);
    radioCocaine = (RadioButton) findViewById(R.id.radioCocaine);
    radioOpiates = (RadioButton) findViewById(R.id.radioOpiates);
    addSupportListener();
    addClientListener();
    addAlocholListener();
    addBenListener();
    addCocaineListener();
    addHeroinListener();
    addInhanlentsListener();
    addMarijuanaListener();
    addMethListener();
    addOpiatesListener();
    addSedativesListener();
    addTranqListener();
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_profile);


    addAllListeners();

    final Calendar dobCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener dateOfBirthListener = new OnDateSetListener() {
      /**
       * Event handler for when a date of birth is chosen by the user.
       * @param view DatePicker object
       * @param year Year chosen by user
       * @param month Month chosen by user
       * @param day Day chosen by user
       */
      @Override
      public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        dobCalendar.set(Calendar.YEAR, year);
        dobCalendar.set(Calendar.MONTH, month);
        dobCalendar.set(Calendar.DAY_OF_MONTH, day);
        final EditText dob = findViewById(R.id.date_of_birth);
        dob.setText(DateFormat.getDateInstance().format(dobCalendar.getTime()));
      }
    };

    final Calendar cleanDateCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener cleanDateListener = new OnDateSetListener() {
      /**
       * Event handler for when a date of last use is chosen by the user.
       * @param view DatePicker object
       * @param year Year chosen by user
       * @param month Month chosen by user
       * @param day Day chosen by user
       */
      @Override
      public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
        cleanDateCalendar.set(Calendar.YEAR, year);
        cleanDateCalendar.set(Calendar.MONTH, month);
        cleanDateCalendar.set(Calendar.DAY_OF_MONTH, day);
        final EditText cleanDate = findViewById(R.id.clean_date);
        cleanDate.setText(DateFormat.getDateInstance().format(cleanDateCalendar.getTime()));
      }
    };

    findViewById(R.id.date_of_birth).setOnClickListener(new OnClickListener() {
      /**
       * Event handler for triggering the display of the Date of Birth date picker when the user
       * taps on the "dateOfBirth" EditText object.
       * @param view EditText object
       */
      @Override
      public void onClick(final View view) {
        final DatePickerDialog pickDate = new DatePickerDialog(CreateProfileActivity.this,
            dateOfBirthListener, dobCalendar.get(Calendar.YEAR), dobCalendar.get(Calendar.MONTH),
            dobCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    findViewById(R.id.clean_date).setOnClickListener(new OnClickListener() {
      /**
       * Event handler for triggering the display of the Last Use Date date picker when the user
       * taps on the "cleanDate" EditText object.
       * @param view EditText object
       */
      @Override
      public void onClick(final View view) {
        final DatePickerDialog pickDate = new DatePickerDialog(CreateProfileActivity.this,
            cleanDateListener, cleanDateCalendar.get(Calendar.YEAR),
            cleanDateCalendar.get(Calendar.MONTH), cleanDateCalendar.get(Calendar.DAY_OF_MONTH));
        pickDate.show();
      }

    });

    final Button send;
    send = findViewById(R.id.record_button);
    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        sendOff();
      }

      /**
       *Upon Clicking, "Record Answers" Birthday, name, gender, and CleanDate will be added to
       * the database. Only these four will be added from my method because Collin is handling
       * the outputs from the buttons and is adding them to the database according to his
       * latest commit on March 27, 2019. However for now it will be simply a button until
       * confirmation
       */
      private void sendOff() {
        //Intent goes to the next activity in the Work Flow.
        Intent intent = new Intent(CreateProfileActivity.this, QuestionnaireActivity.class);
        startActivity(intent);
      }
    });
  }

  private DemographicDataEntity data = new DemographicDataEntity();
  private RadioButton radioSupport;
  private RadioButton radioClient;
  private RadioButton radioHeroin;
  private RadioButton radioOpiates;
  private RadioButton radioAlcohol;
  private RadioButton radioCocaine;
  private RadioButton radioMarijuana;
  private RadioButton radioMeth;
  private RadioButton radioBen;
  private RadioButton radioTranquilizers;
  private RadioButton radioSedatives;
  private RadioButton radioInhalants;
}