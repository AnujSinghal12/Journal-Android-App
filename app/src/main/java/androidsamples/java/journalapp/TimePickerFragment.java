package androidsamples.java.journalapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

  private boolean isStartTime;
  //private EntryDetailsViewModel mEntryDetailsViewModel;
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO implement the method
    //mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);
    setHasOptionsMenu(true);
    isStartTime = TimePickerFragmentArgs.fromBundle(getArguments()).getIsStartTime();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    // TODO implement the method
    //This ensures that when the user opens the picker it shows the current time.
    final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    return new TimePickerDialog(getActivity(), this, hour, minute,false);
  }
  public void onTimeSet(TimePicker view, int hour, int minute) {
    String s = hour + " : " + minute;
    if(isStartTime) {
      Button mBtnStartTime = getActivity().findViewById(R.id.btn_start_time);
      Log.d("time","start time : " + s);
      mBtnStartTime.setText(s);
//      mEntryDetailsViewModel.setStartHours(hour);
//      mEntryDetailsViewModel.setStartMinutes(minute);
    }
    else {
      Button mBtnEndTime = getActivity().findViewById(R.id.btn_end_time);
      Log.d("time", "end time : "+ s);
      mBtnEndTime.setText(s);
//      mEntryDetailsViewModel.setEndHours(hour);
//      mEntryDetailsViewModel.setEndMinutes(minute);
    }
  }
}
