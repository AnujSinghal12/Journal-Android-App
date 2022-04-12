package androidsamples.java.journalapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // TODO implement the method
    //This ensures that when the user opens the picker it shows the current date.
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(getActivity(), this, year, month, day);
  }
  public void onDateSet(DatePicker view, int year, int month, int day) {
    Calendar mCalendar = Calendar.getInstance();
    mCalendar.set(year,month+1,day);
    Button mBtnEntryDate = getActivity().findViewById(R.id.btn_entry_date);
    String s = mCalendar.getTime().toString();
    s = s.substring(0,10) + " " + s.substring(30,34);
    mBtnEntryDate.setText(s);
  }
}