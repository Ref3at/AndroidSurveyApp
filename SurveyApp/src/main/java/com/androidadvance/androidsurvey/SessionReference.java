package com.androidadvance.androidsurvey;

import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;
import com.google.gson.Gson;

import java.util.LinkedHashMap;

//Singleton SessionReference ........

public class SessionReference {
    private volatile static SessionReference uniqueInstance;

    public SurveyTobeSaved mSurveyTobeSaved = new SurveyTobeSaved();


    public void put_answer(String key, String value) {
        mSurveyTobeSaved.questionsAndAnswersHashmap.put(key, value);
    }

    public void setName(String userName) {
        this.mSurveyTobeSaved.userName = userName;
    }




//    public String get_json_object() {
//        Gson gson = new Gson();
//        return gson.toJson(answered_hashmap,LinkedHashMap.class);
//    }

//    @Override
//    public String toString() {
//        return String.valueOf(answered_hashmap);
//    }

    public static SessionReference getInstance() {
        if (uniqueInstance == null) {
            synchronized (SessionReference.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SessionReference();
                }
            }
        }
        return uniqueInstance;
    }


}
