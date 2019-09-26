package com.faramarz.tictacdev.healthcare.CustomDialogs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.faramarz.tictacdev.healthcare.R;
import com.faramarz.tictacdev.healthcare.StartPageActivity;
import com.faramarz.tictacdev.healthcare.StarterPageFragments.HomeFragment;
import com.faramarz.tictacdev.healthcare.main_categories.ManageDaruActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomEditInfoProfileDialog extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.txt_userName)
    EditText txt_userName;
    @BindView(R.id.ch_male)
    RadioButton ch_male;
    @BindView(R.id.ch_female)
    RadioButton ch_female;
    @BindView(R.id.radioG_profile)
    RadioGroup radioG_profile;
    @BindView(R.id.btn_save_profile)
    Button btn_save_profile;
    @BindView(R.id.btn_delete_profile)
    Button btn_delete_profile;
    @BindView(R.id.numberPicker_age)
    NumberPicker numberPicker_age;
    @BindView(R.id.numberPicker_weight)
    NumberPicker numberPicker_weight;
    @BindView(R.id.numberPicker_height)
    NumberPicker numberPicker_height;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Gender = "genderKey";
    public static final String Age = "ageKey";
    public static final String Weight = "weightKey";
    public static final String Height = "heightKey";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_info_profile_layout, container, false);
        ButterKnife.bind(this, view);
        //   Realm.init(getContext());
        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if (preferences.contains(Name)){
            txt_userName.setText(preferences.getString(Name,null));
        }else if (preferences.contains(Gender)){

        }else if (preferences.contains(Age)){
            numberPicker_age.setValue(preferences.getInt(Age,0));
        }else if (preferences.contains(Weight)){
            numberPicker_weight.setValue(preferences.getInt(Weight,0));
        }if (preferences.contains(Height)){
            numberPicker_height.setValue(preferences.getInt(Height,0));
        }



        setNumberPickers();
        initNumberPickers();

        radioG_profile.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioG_profile.getCheckedRadioButtonId() != -1) {
                    int id = radioG_profile.getCheckedRadioButtonId();
                    View radioButton = radioG_profile.findViewById(id);
                    int radioId = radioG_profile.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioG_profile.getChildAt(radioId);
                    String selection = (String) btn.getText();


                }

            }
        });

        btn_save_profile.setOnClickListener(this);
        btn_delete_profile.setOnClickListener(this);

        return view;
    }

    void setNumberPickers() {
        NumberPicker.OnValueChangeListener onValueChangeListenerAge =
                new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                    }
                };
        NumberPicker.OnValueChangeListener onValueChangeListenerWeight =
                new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                      /*  Toast.makeText(getActivity(),
                                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();*/

                    }
                };

        NumberPicker.OnValueChangeListener onValueChangeListenerHeight =
                new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    /*    Toast.makeText(getActivity(),
                                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();*/

                    }
                };
        numberPicker_age.setOnValueChangedListener(onValueChangeListenerAge);
        numberPicker_weight.setOnValueChangedListener(onValueChangeListenerWeight);
        numberPicker_height.setOnValueChangedListener(onValueChangeListenerHeight);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();


        switch (id) {
            case R.id.btn_save_profile:

                if (radioG_profile.getCheckedRadioButtonId() != -1) {
                    int id1 = radioG_profile.getCheckedRadioButtonId();
                    View radioButton = radioG_profile.findViewById(id1);
                    int radioId = radioG_profile.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioG_profile.getChildAt(radioId);

                    String selection = (String) btn.getText();
                    String n = txt_userName.getText().toString();
                    int age = numberPicker_age.getValue();
                    int weight = numberPicker_weight.getValue();
                    int height = numberPicker_height.getValue();


                    SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edt = preferences.edit();
                    edt.putString(Name, n);
                    edt.putString(Gender, selection);
                    edt.putInt(Age, age);
                    edt.putInt(Weight, weight);
                    edt.putInt(Height, height);
                    edt.apply();

                    Intent intent = new Intent(getActivity(), StartPageActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "اطلاعات با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.btn_delete_profile:

                dismiss();


        }

    }

    void initNumberPickers() {
        numberPicker_age.setMinValue(10);
        numberPicker_age.setMaxValue(110);
        numberPicker_age.setWrapSelectorWheel(false);
        numberPicker_weight.setMinValue(20);
        numberPicker_weight.setMaxValue(210);
        numberPicker_weight.setWrapSelectorWheel(false);
        numberPicker_height.setMinValue(60);
        numberPicker_height.setMaxValue(210);
        numberPicker_height.setWrapSelectorWheel(false);

    }


}