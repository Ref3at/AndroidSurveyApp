package com.androidadvance.androidsurvey;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidadvance.androidsurvey.models.SurveyTobeSaved;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyDetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_question_answers)
    TextView txtQuestionAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_detail);
        ButterKnife.bind(this);


        SurveyTobeSaved surveyTobeSaved =
                new Gson().fromJson(getIntent().getExtras().get("SURVEY").toString(), new TypeToken<SurveyTobeSaved>() {
                }.getType());

        fillViews(surveyTobeSaved);
    }

    private void fillViews(SurveyTobeSaved surveyTobeSaved) {
        txtUserName.setText("Submitted by: "+surveyTobeSaved.getUserName());
        txtDate.setText("Date: "+surveyTobeSaved.getDate());

        String body = String.valueOf( surveyTobeSaved.questionsAndAnswersHashmap);
        txtQuestionAnswers.setText(body.replace(", ","\n\n").replace("=","\n").replace("{","").replace("}",""));



    }
}
