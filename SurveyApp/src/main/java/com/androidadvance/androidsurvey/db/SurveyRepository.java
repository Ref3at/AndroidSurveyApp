package com.androidadvance.androidsurvey.db;

import android.app.Application;
import android.os.AsyncTask;

import com.androidadvance.androidsurvey.models.SurveyTobeSaved;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SurveyRepository {


    private SurvyDao mSurvyDao;
    private LiveData<List<SurveyTobeSaved>> mAllSurveys;


    public SurveyRepository(Application application) {
        SurveysRoomDataBase db = SurveysRoomDataBase.getDatabase(application);
        mSurvyDao = db.survyDao();
        mAllSurveys = mSurvyDao.getAllSurveys();
    }

    public LiveData<List<SurveyTobeSaved>> getmAllSurveys() {
        return mAllSurveys;
    }


    public void insert (SurveyTobeSaved surveyPojo) {
        new insertAsyncTask(mSurvyDao).execute(surveyPojo);
    }

    private static class insertAsyncTask extends AsyncTask<SurveyTobeSaved, Void, Void> {

        private SurvyDao mAsyncTaskDao;

        insertAsyncTask(SurvyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SurveyTobeSaved ... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}