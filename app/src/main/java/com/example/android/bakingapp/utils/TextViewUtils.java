package com.example.android.bakingapp.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.widget.TextView;

/**
 * Created by Anamika Tripathi on 30/10/18.
 * https://stackoverflow.com/questions/10979821/
 */
public class TextViewUtils {

    public static void setTextWithSpan(TextView textView, String fullText,
                                       String styledText, StyleSpan style) {
        SpannableStringBuilder sb = new SpannableStringBuilder(fullText);
        int start = fullText.indexOf(styledText);
        int end = start + styledText.length();
        sb.setSpan(style, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(sb);
    }

}
