package com.androidadvance.androidsurvey.fragment;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidadvance.androidsurvey.SessionReference;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.utilities.Fonts;

public class FragmentNumber extends Fragment {

    private FragmentActivity mContext;
    private Button button_continue;
    private TextView textview_q_title;
    private EditText editText_answer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate
                (R.layout.fragment_text_simple, container, false);
        // initView
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        button_continue = (Button) rootView.findViewById(R.id.button_continue);
        textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
        editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);

        Fonts.set(button_continue, getContext());
        Fonts.set(textview_q_title, getContext());
        Fonts.set(editText_answer, getContext());

        editText_answer.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                SessionReference.getInstance().put_answer(textview_q_title.getText().toString(), editText_answer.getText().toString().trim());
                ((SurveyActivity) mContext).go_to_next();
            }
        });
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        Question q_data = (Question) getArguments().getSerializable("data");


        if (q_data.getRequired()) {
            button_continue.setVisibility(View.GONE);
            editText_answer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        button_continue.setVisibility(View.VISIBLE);
                    } else {
                        button_continue.setVisibility(View.GONE);
                    }
                }
            });
        }


        textview_q_title.setText(Html.fromHtml(q_data.getQuestionTitle()));
        editText_answer.requestFocus();
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText_answer, 0);


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