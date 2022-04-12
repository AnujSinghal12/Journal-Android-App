package androidsamples.java.journalapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "journal_table")
public class JournalEntry {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private UUID mUid;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "duration")
    private String mDate;

    @ColumnInfo(name = "startTime")
    private String mStartTime;

    @ColumnInfo(name = "endTime")
    private String mEndTime;


    public JournalEntry(@NonNull String title, String date, String startTime, String endTime) {
        mUid = UUID.randomUUID();
        mTitle = title;
        mDate = date;
        mStartTime = startTime;
        mEndTime = endTime;
    }

    @NonNull
    public UUID getUid() {
        return mUid;
    }

    public void setUid(@NonNull UUID id) {
        this.mUid = id;
    }

    @NonNull
    public String title() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String date() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public void setStartTime(String startTime) {
        this.mStartTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.mEndTime = endTime;
    }

    public String startTime() {
        return mStartTime;
    }

    public String endTime() {
        return mEndTime;
    }
}
