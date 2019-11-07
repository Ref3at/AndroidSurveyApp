package com.androidadvance.androidsurvey.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.androidadvance.androidsurvey.models.SurveyTobeSaved;

import java.util.List;

public class SurveyRepository {


    private SurvyDao mSurvyDao;
    private LiveData<List<SurveyTobeSaved>> mAllSurveys;


    public SurveyRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mSurvyDao = db.survyDao();
        mAllSurveys = mSurvyDao.getAllSurveys();
    }

    public LiveData<List<SurveyTobeSaved>> getmAllSurveys() {
        return mAllSurveys;
    }


    public void insert (SurveyTobeSaved surveyPojo) {
        new insertAsyncTask(mSurvyDao).execute(surveyPojo);
    }

    public void deleteAll() {
        new deleteAsyncTask(mSurvyDao).execute();
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

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private SurvyDao mAsyncTaskDao;

        deleteAsyncTask(SurvyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void ... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}