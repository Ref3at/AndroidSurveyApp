package com.androidadvance.androidsurvey.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidadvance.androidsurvey.SessionReference;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.utilities.Fonts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentCheckboxes extends Fragment {

    private Question q_data;
    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private LinearLayout linearLayout_checkboxes;
    private final ArrayList<CheckBox> allCb = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_checkboxes, container, false);

        init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        button_continue = (Button) rootView.findViewById(R.id.button_continue);
        // set btn font
        Fonts.set(button_continue, getContext());
        textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
        // set textView font
        Fonts.set(textview_q_title, getContext());
        linearLayout_checkboxes = (LinearLayout) rootView.findViewById(R.id.linearLayout_checkboxes);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SurveyActivity) mContext).go_to_next();
            }
        });
    }

    private void collect_data() {

        //----- collection & validation for is_required
        String the_choices = "";
        boolean at_leaset_one_checked = false;
        for (CheckBox cb : allCb) {
            if (cb.isChecked()) {
                at_leaset_one_checked = true;
                the_choices = the_choices + cb.getText().toString() + ", ";
            }
        }

        if (the_choices.length() > 2) {
            the_choices = the_choices.substring(0, the_choices.length() - 2);
            SessionReference.getInstance().put_answer(textview_q_title.getText().toString(), the_choices);
        }


        if (q_data.getRequired()) {
            if (at_leaset_one_checked) {
                button_continue.setVisibility(View.VISIBLE);
            } else {
                button_continue.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mContext = getActivity();
        q_data = (Question) getArguments().getSerializable("data");

        textview_q_title.setText(q_data != null ? q_data.getQuestionTitle() : "");

        if (q_data.getRequired()) {
            button_continue.setVisibility(View.GONE);
        }

        List<String> qq_data = q_data.getChoices();
        // if getRandomChoices variable with true make list shuffle
        if (q_data.getRandomChoices()) {
            Collections.shuffle(qq_data);
        }

        for (String choice : qq_data) {
            // creating checkbox programmatically with style
            // creating checkbox based on number of choices then add it to array of checkboxes
            CheckBox cb = new CheckBox(new ContextThemeWrapper(mContext, R.style.MyCheckbox));
            cb.setText(Html.fromHtml(choice));
            cb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            cb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayout_checkboxes.addView(cb);
            allCb.add(cb);

            // onclick of checkbox after every click call collect_data to cehck
            // what is user checked
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    collect_data();
                }
            });
        }

    }

    void hideTheKeyPad(){
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}