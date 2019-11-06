package com.androidadvance.androidsurvey.models;

import java.util.Date;
import java.util.LinkedHashMap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "savedSurveys")
public class SurveyTobeSaved {


    // @TypeConverters(LinkedHashMapConverters.class)
    public LinkedHashMap<String, String> questionsAndAnswersHashmap = new LinkedHashMap<>();
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    //@TypeConverters(DateConverter.class)
    private Date date;
    private String signaturePath;

    @Ignore
    public SurveyTobeSaved() {

    }

    public SurveyTobeSaved(int id, String userName, Date date, String signaturePath) {
        this.id = id;
        this.userName = userName;
        this.date = date;
        this.signaturePath = signaturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSignaturePath() {
        return signaturePath;
    }

    public void setSignaturePath(String signaturePath) {
        this.signaturePath = signaturePath;
    }
}
