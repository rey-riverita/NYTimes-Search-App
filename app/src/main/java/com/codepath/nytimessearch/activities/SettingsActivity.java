package com.codepath.nytimessearch.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.codepath.nytimessearch.ArticleFilter;
import com.codepath.nytimessearch.R;
import com.codepath.nytimessearch.SpinnerArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, Serializable {

    ArrayList<Spinner> sortOptions;
    SpinnerArrayAdapter adapter;
    EditText etDatePicker;
    ArticleFilter filter;
    Spinner spinner;
    StringBuilder sb;
    Calendar c;
    CheckBox checkBoxFashion;
    CheckBox checkBoxArts;
    CheckBox checkBoxSports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filters");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        this.filter = (ArticleFilter) getIntent().getSerializableExtra("intent");
            this.c = (Calendar) getIntent().getSerializableExtra("calendar");




        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sorting_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

    }

//    public void setupViews() {
//        if(c != null) {
//            this.c = c;
//        }
//    }

    public void onSaveFilters(View v) {

        //String value = spinner.getSelectedItem().toString();
        //setSpinnerToValue(spinner, spinner.getSelectedItem().toString());

        //filter.setBeginDate(c.toString());
        filter.setSort(setSpinnerToValue(spinner, spinner.getSelectedItem().toString()));
        // Initialize Scanner object
        if(etDatePicker != null && !etDatePicker.getText().toString().equals("")) {
//            Toast.makeText()
            Scanner scan = new Scanner(etDatePicker.getText().toString());
            scan.useDelimiter("/");
            String month = scan.next();
            String day = scan.next();
            String year = scan.next();

            if (month.length() < 2) {
                month = "0" + month;
            }

            if (day.length() < 2) {
                day = "0" + day;
            }

            filter.setBeginDate(year + month + day);

            scan.close();
        }

            String newsDeskValues = "";
            //boolean checked = ((CheckBox) view).isChecked();

            checkBoxFashion = (CheckBox) findViewById(R.id.checkbox_fashion);

            if (checkBoxFashion.isChecked()) {
                newsDeskValues += "\"Fashion & Style\" ";
            }

            checkBoxArts = (CheckBox) findViewById(R.id.checkbox_arts);

            if (checkBoxArts.isChecked()) {
                newsDeskValues += "\"Arts\" ";
            }

            checkBoxSports = (CheckBox) findViewById(R.id.checkbox_sports);

            if (checkBoxSports.isChecked()) {
                newsDeskValues += "\"Sports\" ";
            }

        if (!newsDeskValues.equals("")) {
            String newsDeskValuesFinal = newsDeskValues.substring(0, newsDeskValues.length() - 1);


            String newsDeskParamValue =
                    String.format("news_desk:(%s)", newsDeskValuesFinal);

            //if (!newsDeskParamValue.equals("")) {
            filter.setNewsDeskValue(newsDeskParamValue);
            // }

        }

        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("applied_filters", filter);
        Log.d("CALENDAR", c.toString());

        data.putExtra("calendar", c);



        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }

    public String setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break; // terminate loop
            }
        }
        return spinner.getSelectedItem().toString();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void onDatePickerClick(View view) {
        showDatePickerDialog(view);

    }
    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");


    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // store the values selected into a Calendar instance
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        Log.d("CALENDAR", c.toString());

        etDatePicker = (EditText) findViewById(R.id.etDatePicker);
        // set selected date into textview

        sb = new StringBuilder();

        etDatePicker.setText(sb.append(month + 1)
                .append("/").append(day).append("/").append(year));

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_fashion:
                if (checked){
                }
                // Put some meat on the sandwich
                else
                // Remove the meat
                break;
            case R.id.checkbox_arts:
                if (checked){}
                // Cheese me
                else
                // I'm lactose intolerant
                break;
            case R.id.checkbox_sports:
                if (checked){}
                // Cheese me
                else
                // I'm lactose intolerant
                break;
        }

    }


}
