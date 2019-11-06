package com.androidadvance.androidsurvey.roomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;

import java.util.List;

@Dao
public interface SurvyDao {


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(SurveyTobeSaved survey);

        @Query("DELETE FROM savedSurveys")
        void deleteAll();

        @Query("SELECT * from savedSurveys Order by date DESC")
        LiveData<List<SurveyTobeSaved>> getAllSurveys();
    }
