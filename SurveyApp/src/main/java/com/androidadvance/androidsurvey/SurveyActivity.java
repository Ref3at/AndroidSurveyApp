package com.androidadvance.androidsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.androidadvance.androidsurvey.adapters.AdapterFragmentQ;
import com.androidadvance.androidsurvey.fragment.FragmentCheckboxes;
import com.androidadvance.androidsurvey.fragment.FragmentEnd;
import com.androidadvance.androidsurvey.fragment.FragmentMultiline;
import com.androidadvance.androidsurvey.fragment.FragmentNumber;
import com.androidadvance.androidsurvey.fragment.FragmentRadioboxes;
import com.androidadvance.androidsurvey.fragment.FragmentStart;
import com.androidadvance.androidsurvey.fragment.FragmentTextSimple;
import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.androidadvance.androidsurvey.models.SurveyTobeSaved;
import com.androidadvance.androidsurvey.viewModel.SurveyPojoViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {

    private SurveyPojoViewModel mSurveyPojoViewModel;


    private SurveyPojo mSurveyPojo;
    private ViewPager mPager;
    private String style_string = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);


        //view Model sec
        mSurveyPojoViewModel = new ViewModelProvider(this).get(SurveyPojoViewModel.class);
        mSurveyPojoViewModel.getAllSurveys().observe(this, new Observer<List<SurveyTobeSaved>>() {
            @Override
            public void onChanged(@Nullable final List<SurveyTobeSaved> surveyList) {
                // Update the cached copy of the words in the adapter.
                Log.e("surveyListSize",surveyList.size()+"");
            }
        });



        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mSurveyPojo = new Gson().fromJson(bundle.getString("json_survey"), SurveyPojo.class);
            if (bundle.containsKey("style")) {
                style_string = bundle.getString("style");
            }
        }


        Log.i("json Object = ", String.valueOf(mSurveyPojo.questions));

        final ArrayList<Fragment> arraylist_fragments = new ArrayList<>();

        //- START -
        if (!mSurveyPojo.surveyProperties.getSkipIntro()) {
            FragmentStart frag_start = new FragmentStart();
            Bundle sBundle = new Bundle();
            sBundle.putSerializable("survery_properties", mSurveyPojo.surveyProperties);
            sBundle.putString("style", style_string);
            frag_start.setArguments(sBundle);
            arraylist_fragments.add(frag_start);
        }

        //- FILL -
        for (Question mQuestion : mSurveyPojo.questions) {

            if (mQuestion.getQuestionType().equals("String")) {
                FragmentTextSimple frag = new FragmentTextSimple();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Checkboxes")) {
                FragmentCheckboxes frag = new FragmentCheckboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Radioboxes")) {
                FragmentRadioboxes frag = new FragmentRadioboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Number")) {
                FragmentNumber frag = new FragmentNumber();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("StringMultiline")) {
                FragmentMultiline frag = new FragmentMultiline();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

        }

        //- END -
        FragmentEnd frag_end = new FragmentEnd();
        Bundle eBundle = new Bundle();
        eBundle.putSerializable("survery_properties", mSurveyPojo.surveyProperties);
        eBundle.putString("style", style_string);
        frag_end.setArguments(eBundle);
        arraylist_fragments.add(frag_end);


        mPager = (ViewPager) findViewById(R.id.pager);
        AdapterFragmentQ mPagerAdapter = new AdapterFragmentQ(getSupportFragmentManager(), arraylist_fragments);
        mPager.setAdapter(mPagerAdapter);


    }

    public void go_to_next() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public void event_survey_completed(SessionReference instance) {

        Log.d("****", "****************** WE HAVE ANSWERS ******************");
        Log.v("ANSWERS JSON", new Gson().toJson(instance.mSurveyTobeSaved));
        Log.d("****", "*****************************************************");


        mSurveyPojoViewModel.insert(instance.mSurveyTobeSaved);
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }



}
