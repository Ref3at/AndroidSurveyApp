package com.androidadvance.androidsurvey.fragment;

import android.content.Context;
import android.os.Bundle;

import com.androidadvance.androidsurvey.SessionReference;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.SurveyProperties;
import com.androidadvance.androidsurvey.utilities.Fonts;


public class FragmentStart extends Fragment {

    private FragmentActivity mContext;
    private TextView textView_start;

    TextInputLayout txtUserName;
    TextInputEditText etxtUserName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_start, container, false);

         txtUserName=  rootView.findViewById(R.id.txt_user_name);
         etxtUserName =  rootView.findViewById(R.id.etxt_user_name);


        textView_start = (TextView) rootView.findViewById(R.id.textView_start);
        Fonts.set(textView_start, getContext());
        Button button_continue = (Button) rootView.findViewById(R.id.button_continue);
        Fonts.set(button_continue, getContext());
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideTheKeyPad();

                if (TextUtils.isEmpty(etxtUserName.getText()))
                {

                    txtUserName.setErrorEnabled(true);
                    txtUserName.setError("Please enter your name");
                    return;
                }
                if (etxtUserName.getText().toString().trim().length() < 3)
                {
                    txtUserName.setErrorEnabled(true);
                    txtUserName.setError("Please enter valid name!");
                    return;
                }


                SessionReference.getInstance().setName(etxtUserName.getText().toString().trim());
                ((SurveyActivity) mContext).go_to_next();


            }
        });

        etxtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                txtUserName.setErrorEnabled(false);
                txtUserName.setError("");


            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });





        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        SurveyProperties survery_properties = (SurveyProperties) getArguments().getSerializable("survery_properties");
        // intro must be html
        assert survery_properties != null;
        textView_start.setText(Html.fromHtml(survery_properties.getIntroMessage()));




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