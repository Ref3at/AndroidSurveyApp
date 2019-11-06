package com.androidadvance.androidsurvey.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.androidadvance.androidsurvey.SessionReference;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.SurveyProperties;
import com.androidadvance.androidsurvey.utilities.Fonts;

public class FragmentEnd extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_end;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_end, container, false);
        //initView
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        Button button_finish = (Button) rootView.findViewById(R.id.button_finish);
        textView_end = (TextView) rootView.findViewById(R.id.textView_end);
        Fonts.set(button_finish, getContext());
        Fonts.set(textView_end, getContext());


        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SurveyActivity) mContext).event_survey_completed(SessionReference.getInstance());

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");

        assert survery_properties != null;
        textView_end.setText(Html.fromHtml(survery_properties.getEndMessage()));

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