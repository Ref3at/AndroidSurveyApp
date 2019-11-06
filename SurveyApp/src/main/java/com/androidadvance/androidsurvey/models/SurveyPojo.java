package com.androidadvance.androidsurvey.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurveyPojo implements Serializable {


    @SerializedName("survey_properties")
    @Expose
    public SurveyProperties surveyProperties;


    @SerializedName("questions")
    @Expose
    public List<Question> questions = new ArrayList<Question>();


}