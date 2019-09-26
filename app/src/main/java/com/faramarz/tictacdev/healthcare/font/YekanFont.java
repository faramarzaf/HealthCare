package com.faramarz.tictacdev.healthcare.font;

import android.app.Application;

import com.faramarz.tictacdev.healthcare.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class YekanFont extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/FarYekan.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


}
