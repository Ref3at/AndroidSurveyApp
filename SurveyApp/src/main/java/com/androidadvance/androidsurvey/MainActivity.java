package com.androidadvance.androidsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.androidadvance.androidsurvey.utilities.Fonts;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int SURVEY_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Nothing fancy here. Plain old simple buttons....

        Button button_survey_example_1 = (Button) findViewById(R.id.button_survey_example_1);
        Button button_survey_example_2 = (Button) findViewById(R.id.button_survey_example_2);
        Button button_survey_example_3 = (Button) findViewById(R.id.button_survey_example_3);
        Fonts.set(button_survey_example_1, this);
        Fonts.set(button_survey_example_2, this);
        Fonts.set(button_survey_example_3, this);

        button_survey_example_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                //you have to pass as an extra the json string.
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_1.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });

        button_survey_example_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_2.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });

        button_survey_example_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_survey = new Intent(MainActivity.this, SurveyActivity.class);
                i_survey.putExtra("json_survey", loadSurveyJson("example_survey_3.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
            }
        });

    }


    //json stored in the assets folder. but you can get it from wherever you like.
    private String loadSurveyJson(String filename) {
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}