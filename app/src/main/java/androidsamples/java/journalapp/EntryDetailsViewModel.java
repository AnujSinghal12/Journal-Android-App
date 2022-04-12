package androidsamples.java.journalapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class EntryDetailsViewModel extends ViewModel {
    private static final String TAG = "EntryDetailsViewModel";
    private final JournalRepository mRepository;
    private int hours, minutes;

    private final MutableLiveData<UUID> entryIdLiveData = new MutableLiveData<>();

    public EntryDetailsViewModel() {
        mRepository = JournalRepository.getInstance();
    }

    LiveData<JournalEntry> getEntryLiveData() {
        Log.d(TAG, "getEntryLiveData called");
        return Transformations.switchMap(entryIdLiveData, mRepository::getEntry);
    }

    void loadEntry(UUID entryId) {
        Log.d(TAG, "loading entry: " + entryId);
        entryIdLiveData.setValue(entryId);
    }

    void saveEntry(JournalEntry entry) {
        Log.d(TAG, "Saving entry: " + entry.getUid());
        mRepository.update(entry);
    }

    void deleteEntry(JournalEntry entry) {
        Log.d(TAG, "Deleting entry: " + entry.getUid());
        mRepository.delete(entry);
    }

    //Another possible way of accessing the TimePickerFragment data.
    void setEndHours(int hours) {
        this.hours = hours;
    }

    int getEndHours() {
        return hours;
    }
    void setEndMinutes(int minutes) {
        this.minutes = minutes;
    }

    int getEndMinutes() {
        return minutes;
    }
    void setStartHours(int hours) {
        this.hours = hours;
    }

    int getStartHours() {
        return hours;
    }
    void setStartMinutes(int minutes) {
        this.minutes = minutes;
    }

    int getStartMinutes() {
        return minutes;
    }
}
