package com.androidadvance.androidsurvey.roomDatabase;

import androidx.room.TypeConverter;

import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.models.SurveyProperties;
import com.androidadvance.androidsurvey.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


public class DataConverters {

    @TypeConverter
    public static String toStringValue(LinkedHashMap<String, String> linkedHashMap) {

        Gson gson = new Gson();
        String json = gson.toJson(linkedHashMap);
        return json;
    }

    @TypeConverter
    public static LinkedHashMap<String, String> toObject(String value) {
        Type type = new TypeToken<LinkedHashMap<String, String>>() {
        }.getType();
        return new Gson().fromJson(value, type);
    }

//
//    @TypeConverter
//    public static String questionListtoString(List<Question> questionList) {
//
//        Gson gson = new Gson();
//        String json = gson.toJson(questionList);
//        return json;
//    }
//
//    @TypeConverter
//    public static List<Question> fromJsonToSListOfQuestions(String value) {
//        Type type = new TypeToken<List<Question>>() {
//        }.getType();
//        return new Gson().fromJson(value, type);
//    }
//
//
//    @TypeConverter
//    public static String usertoString(User user) {
//
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//        return json;
//    }
//
//    @TypeConverter
//    public static User fromJsonToUser(String value) {
//        Type type = new TypeToken<User>() {
//        }.getType();
//        return new Gson().fromJson(value, type);
//    }
//
//
//    @TypeConverter
//    public static Date fromTimestamp(Long value) {
//        return value == null ? null : new Date(value);
//    }
//
//    @TypeConverter
//    public static Long dateToTimestamp(Date date) {
//        if (date == null) {
//            return null;
//        } else {
//            return date.getTime();
//        }
//    }
//

}
