package com.androidadvance.androidsurvey.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SessionReference;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.utilities.Fonts;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;


public class SmileFragment extends Fragment {
    private static final String TAG = SmileFragment.class.getSimpleName();
    private Question q_data;
    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private boolean at_leaset_one_checked = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate
                (R.layout.fragment_simle_frament, container, false);

// initView
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        //initView
        button_continue = (Button) rootView.findViewById(R.id.button_continue);
        textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
        SmileRating smileRating = (SmileRating) rootView.findViewById(R.id.smile_rating);
        // setFonts To view
        Fonts.set(button_continue, getContext());
        Fonts.set(textview_q_title, getContext());
        Typeface typeface = Typeface.createFromAsset(getContext().getResources().getAssets(), "font.ttf");
        smileRating.setTypeface(typeface);
        // click on continue Btn
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SurveyActivity) mContext).go_to_next();
            }
        });
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.BAD:
                        Log.i(TAG, "Bad");
                        changeState("Bad");
                        break;
                    case SmileRating.GOOD:
                        Log.i(TAG, "Good");
                        changeState("Good");
                        break;
                    case SmileRating.GREAT:
                        Log.i(TAG, "Great");
                        changeState("Great");
                        break;
                    case SmileRating.OKAY:
                        Log.i(TAG, "Okay");
                        changeState("Okay");
                        break;
                    case SmileRating.TERRIBLE:
                        Log.i(TAG, "Terrible");
                        changeState("Terrible");
                        break;
                }
            }
        });

    }

    private void changeState(String mood) {
        button_continue.setVisibility(View.VISIBLE);
        SessionReference.getInstance().put_answer(textview_q_title.getText().toString(), mood);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        // get data
        q_data = (Question) getArguments().getSerializable("data");
        //set data to view
        textview_q_title.setText(q_data.getQuestionTitle());
        if (q_data.getRequired()) {
            if (at_leaset_one_checked) {
                button_continue.setVisibility(View.VISIBLE);
            } else {
                button_continue.setVisibility(View.GONE);
            }
        }
    }


}
