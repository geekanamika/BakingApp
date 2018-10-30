package com.example.android.bakingapp.utils;

import android.content.Context;

import com.example.android.bakingapp.R;

import java.util.Locale;

/**
 * Created by Anamika Tripathi on 30/10/18.
 */
public class StringUtils {

    public static String formatIngdedient(Context context, String name, float quantity, String measure) {

        String line = context.getResources().getString(R.string.recipe_details_ingredient_line);

        String quantityStr = String.format(Locale.US, "%s", quantity);
        if (quantity == (long) quantity) {
            quantityStr = String.format(Locale.US, "%d", (long) quantity);
        }

        return String.format(Locale.US, line, name, quantityStr, measure);
    }
}
