package com.example.supplementaryassessmentit21018350.Database;

public class NoteModle {

    private int id;
    private String title;
    private String note;
    private String cDate;
    private String mDate;

    public NoteModle(int id, String title, String note, String cDate, String mDate) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.cDate = cDate;
        this.mDate = mDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
