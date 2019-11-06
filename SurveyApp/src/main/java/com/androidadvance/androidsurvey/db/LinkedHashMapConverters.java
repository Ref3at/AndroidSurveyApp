package com.androidadvance.androidsurvey.db;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import androidx.room.TypeConverter;


public class LinkedHashMapConverters {

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


}
