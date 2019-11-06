package com.androidadvance.androidsurvey.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fonts {
    public static void set(TextView textView, Context context) {

        Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), "font.ttf");
        if (textView != null) {
            textView.setTypeface(font);
        }
    }


    public static void set(EditText editText, Context context) {

        Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), "font.ttf");
        if (editText != null) {
            editText.setTypeface(font);
        }
    }

    public static void set(Button button, Context context) {

        Typeface font = Typeface.createFromAsset(context.getResources().getAssets(), "font.ttf");
        if (button != null) {
            button.setTypeface(font);
        }
    }
}
