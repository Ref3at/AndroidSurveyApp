package com.androidadvance.androidsurvey.viewModel;

import android.app.Application;

import com.androidadvance.androidsurvey.db.SurveyRepository;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SurveyToBeSavedViewModel extends AndroidViewModel {

    private SurveyRepository mRepository;
    private LiveData<List<SurveyTobeSaved>> mAllWords;


    public SurveyToBeSavedViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SurveyRepository(application);
        mAllWords = mRepository.getmAllSurveys();
    }


    public LiveData<List<SurveyTobeSaved>> getAllSurveys() {
        return mAllWords;
    }

    public void insert(SurveyTobeSaved survey) {
        mRepository.insert(survey);
    }


    public void deleteAll() {
        mRepository.deleteAll();
    }
}

