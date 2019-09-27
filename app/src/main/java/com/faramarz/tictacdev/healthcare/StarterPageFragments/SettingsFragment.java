package com.faramarz.tictacdev.healthcare.StarterPageFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;


import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.faramarz.tictacdev.healthcare.R;
import com.faramarz.tictacdev.healthcare.SharedPrefs.SharedPref;

import butterknife.BindView;


public class SettingsFragment extends Fragment {

    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.text_test)
    TextView text_test;
    @BindView(R.id.seekbar_size)
    SeekBar seekbar_size;
    @BindView(R.id.rd_nazanin)
    RadioButton rd_nazanin;
    @BindView(R.id.rd_kodak)
    RadioButton rd_kodak;
    @BindView(R.id.rd_yekan)
    RadioButton rd_yekan;
    @BindView(R.id.btn_save)
    Button btn_save;
    @BindView(R.id.aswitch)
    Switch aswitch;

    int fontsize = 16;
    SharedPreferences pref;
    SharedPref sharedPref;
    String Mypre = "Save", SIZE = "size", FONT_N = "nazanin", FONT_T = "yekan", FONT_K = "kodak";
    Typeface kodak, nazanin, yekan;


    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        sharedPref = new SharedPref(getActivity());

        if (sharedPref.loadNightMode() == true) {
            getContext().getTheme().applyStyle(R.style.DarkAppTheme, true);
        } else {
            getContext().getTheme().applyStyle(R.style.AppTheme, true);
        }

        pref = this.getActivity().getSharedPreferences(Mypre, 0);

        permission();
        changeFontSize();
        changeFontStyle();
        nightMode();

        return inflater.inflate(R.layout.fragment_settings, container, false);


    }

    void permission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (Settings.System.canWrite(getActivity())) {

            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package: " + getActivity().getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }

        }

    }


    void changeFontSize() {
        seekbar_size.setOnSeekBarChangeListener(new androidx.appcompat.widget.AppCompatSeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = pref.edit();

                fontsize = progress;
                text_test.setTextSize(fontsize);

                editor.putInt(SIZE, fontsize);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (fontsize < 16) {
                    fontsize = 16;
                    seekBar.setProgress(fontsize);
                }
            }


        });

        if (pref.contains(SIZE)) {
            fontsize = pref.getInt(SIZE, 0);
            seekbar_size.setProgress(fontsize);
            text_test.setTextSize(fontsize);

        }

    }

    void changeFontStyle() {

        kodak = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BKoodakBold.ttf");
        nazanin = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BNazanin.ttf");
        yekan = Typeface.createFromAsset(getActivity().getAssets(), "fonts/FarYekan.ttf");

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();

                if (rd_nazanin.isChecked()) {
                    text_test.setTypeface(nazanin);
                    title1.setTypeface(nazanin);
                    title2.setTypeface(nazanin);

                } else if (rd_kodak.isChecked()) {
                    text_test.setTypeface(kodak);
                    title1.setTypeface(kodak);
                    title2.setTypeface(kodak);


                } else if (rd_yekan.isChecked()) {
                    text_test.setTypeface(yekan);
                    title1.setTypeface(yekan);
                    title2.setTypeface(yekan);

                }

                editor.putBoolean(FONT_N, rd_nazanin.isChecked());
                editor.putBoolean(FONT_K, rd_kodak.isChecked());
                editor.putBoolean(FONT_T, rd_yekan.isChecked());

                editor.apply();
            }
        });

        if (pref.contains(FONT_N) && pref.contains(FONT_K) && pref.contains(FONT_T)) {

            rd_nazanin.setChecked(pref.getBoolean(FONT_N, false));
            rd_kodak.setChecked(pref.getBoolean(FONT_K, false));
            rd_yekan.setChecked(pref.getBoolean(FONT_T, false));

            if (rd_nazanin.isChecked() == true) {
                text_test.setTypeface(nazanin);
                title1.setTypeface(nazanin);
                title2.setTypeface(nazanin);

            } else if (rd_kodak.isChecked() == true) {
                text_test.setTypeface(kodak);
                title1.setTypeface(kodak);
                title2.setTypeface(kodak);

            } else if (rd_yekan.isChecked() == true) {
                text_test.setTypeface(yekan);
                title1.setTypeface(yekan);
                title2.setTypeface(yekan);
            }

        }

    }


    void nightMode() {
        if (sharedPref.loadNightMode() == true) {
            aswitch.setChecked(true);

        }

        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPref.setNightMode(true);
                    restartApp();
                } else {
                    sharedPref.setNightMode(false);
                    restartApp();
                }

            }

        });

    }

    void restartApp() {
        Intent res = new Intent(getActivity().getApplicationContext(), SettingsFragment.class);
        startActivity(res);
        getActivity().finish();

    }


}
