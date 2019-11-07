package com.androidadvance.androidsurvey;

import android.text.TextUtils;

import com.androidadvance.androidsurvey.models.SurveyTobeSaved;

import java.util.Date;

//Singleton SessionReference ........

public class SessionReference {
    private volatile static SessionReference uniqueInstance;

    public SurveyTobeSaved mSurveyTobeSaved = new SurveyTobeSaved();


    public void put_answer(String key, String value) {
        if (TextUtils.isEmpty(value))
            value = "No Answer!";
        mSurveyTobeSaved.questionsAndAnswersHashmap.put(key, value);
    }

    public void setName(String userName) {
        this.mSurveyTobeSaved.setUserName(userName);
    }

   public void setDate(Date date) {
        this.mSurveyTobeSaved.setDate(date);
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
