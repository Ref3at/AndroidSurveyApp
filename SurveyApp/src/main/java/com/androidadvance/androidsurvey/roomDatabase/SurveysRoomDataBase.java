package com.androidadvance.androidsurvey.roomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;

@Database(entities = {SurveyTobeSaved.class}, version = 2)
@TypeConverters(DataConverters.class)
public abstract class SurveysRoomDataBase extends RoomDatabase {

    public abstract SurvyDao survyDao();


    private static volatile SurveysRoomDataBase INSTANCE;

    static SurveysRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SurveysRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SurveysRoomDataBase.class, "surveys_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

