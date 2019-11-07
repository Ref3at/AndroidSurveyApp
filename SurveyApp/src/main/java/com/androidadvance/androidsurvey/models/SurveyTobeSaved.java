package com.androidadvance.androidsurvey.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.LinkedHashMap;

@Entity(tableName = "savedSurveys")
public class SurveyTobeSaved {



    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    public LinkedHashMap<String, String> questionsAndAnswersHashmap = new LinkedHashMap<>();
    private Date date;
    private String signaturePath;


    @Ignore
    public SurveyTobeSaved() {

    }

    public SurveyTobeSaved(int id, String userName, String signaturePath, Date date, LinkedHashMap<String, String> questionsAndAnswersHashmap) {
        this.id = id;
        this.userName = userName;
        this.signaturePath = signaturePath;
        this.date = date;
        this.questionsAndAnswersHashmap = questionsAndAnswersHashmap;
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
