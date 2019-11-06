package com.androidadvance.androidsurvey.models;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.androidadvance.androidsurvey.roomDatabase.DataConverters;

import java.util.LinkedHashMap;

@Entity(tableName = "savedSurveys")
public class SurveyTobeSaved {


    @PrimaryKey(autoGenerate = true)
    public int id;

    public String userName;

    public Long date;

    public String signaturePath;

    @TypeConverters(DataConverters.class)
    public LinkedHashMap<String, String> questionsAndAnswersHashmap = new LinkedHashMap<>();



}
