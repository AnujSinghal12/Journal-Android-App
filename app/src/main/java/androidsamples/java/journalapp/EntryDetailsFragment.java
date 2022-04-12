package androidsamples.java.journalapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.UUID;

public class EntryDetailsFragment extends Fragment {
  private EditText mEditTitle;
  private Button mBtnDate, mBtnStartTime, mBtnEndTime;
  private EntryDetailsViewModel mEntryDetailsViewModel;
  private JournalEntry mEntry;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    mEntryDetailsViewModel = new ViewModelProvider(getActivity()).get(EntryDetailsViewModel.class);

    UUID entryId = UUID.fromString(EntryDetailsFragmentArgs.fromBundle(getArguments()).getEntryId());

    mEntryDetailsViewModel.loadEntry(entryId);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_entry_details, container, false);
    mEditTitle = view.findViewById(R.id.edit_title);
    mBtnDate = view.findViewById(R.id.btn_entry_date);
    mBtnStartTime = view.findViewById(R.id.btn_start_time);
    mBtnEndTime = view.findViewById(R.id.btn_end_time);
    view.findViewById(R.id.btn_save).setOnClickListener(this::saveEntry);
    view.findViewById(R.id.btn_start_time).setOnClickListener(this::setStartTime);
    view.findViewById(R.id.btn_end_time).setOnClickListener(this::setEndTime);
    view.findViewById(R.id.btn_entry_date).setOnClickListener(this::setEntryDate);
    return view;
  }

  private void setEntryDate(View view) {
    Navigation.findNavController(view).navigate(R.id.datePickerAction);
  }

  private void setEndTime(View view) {
    EntryDetailsFragmentDirections.TimePickerAction action = EntryDetailsFragmentDirections.timePickerAction();
    action.setIsStartTime(false);
    Navigation.findNavController(view).navigate(action);
  }

  private void setStartTime(View view) {
    EntryDetailsFragmentDirections.TimePickerAction action = EntryDetailsFragmentDirections.timePickerAction();
    action.setIsStartTime(true);
    Navigation.findNavController(view).navigate(action);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mEntryDetailsViewModel.getEntryLiveData().observe(getActivity(),
            entry -> {
              this.mEntry = entry;
              if (entry != null) {
                updateUI();
              }
            });
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragmene_entry_detail, menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    //In case we need Info button in the EntryDetail too
//    if( item.getItemId() == R.id.info) {
//      Navigation.findNavController(getView()).navigate(R.id.infoAction);
    if (item.getItemId() == R.id.menu_delete_entry) {
      new AlertDialog.Builder(getContext()).setTitle("Delete this Entry").setMessage("Do you wish to delete this entry?").setPositiveButton(R.string.yes,(a,b) -> {mEntryDetailsViewModel.deleteEntry(mEntry);
        getActivity().onBackPressed();}).setNegativeButton(R.string.no, null).show();

    } else if (item.getItemId() == R.id.menu_share_entry) {
      Intent sendIntent = new Intent();
      sendIntent.setAction(Intent.ACTION_SEND);
      String s = "Look what I have been up to: " + mEntry.title() + " on " + mEntry.date() + " , " + mEntry.startTime() + " to " + mEntry.endTime();
      sendIntent.putExtra(Intent.EXTRA_TEXT, s);
      Intent.createChooser(sendIntent, "Send using");
      sendIntent.setType("text/plain");
      startActivity(sendIntent);
    }
    return super.onOptionsItemSelected(item);
  }

  private void updateUI() {
    //Log.d("testing", mEntry.date());
    mBtnDate.setText(mEntry.date());
    mEditTitle.setText(mEntry.title());
    mBtnStartTime.setText(mEntry.startTime());
    mBtnEndTime.setText(mEntry.endTime());
  }

  private void saveEntry(View v) {
    mEntry.setTitle(mEditTitle.getText().toString());
    mEntry.setDate(mBtnDate.getText().toString());
    mEntry.setStartTime(mBtnStartTime.getText().toString());
    mEntry.setEndTime(mBtnEndTime.getText().toString());
    //Log.d("testingstuff", mBtnEndTime.getText().toString());

    mEntryDetailsViewModel.saveEntry(mEntry);
    getActivity().onBackPressed();
  }
}