package com.faramarz.tictacdev.healthcare.CustomDialogs;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.faramarz.tictacdev.healthcare.R;

import com.faramarz.tictacdev.healthcare.main_categories.ManageDaruActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

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
    @BindView(R.id.btn_update_profile)
    Button btn_update_profile;
    @BindView(R.id.btn_delete_profile)
    Button btn_delete_profile;

    @BindView(R.id.btn_show)
    Button btn_show;

    @BindView(R.id.numberPicker_age)
    NumberPicker numberPicker_age;
    @BindView(R.id.numberPicker_weight)
    NumberPicker numberPicker_weight;
    @BindView(R.id.numberPicker_height)
    NumberPicker numberPicker_height;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_info_profile_layout, container, false);
        ButterKnife.bind(this, view);
        Realm.init(getContext());

        setNumberPickers();
        initNumberPickers();


        radioG_profile.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*if (radioG_profile.getCheckedRadioButtonId() != -1) {
                    int id = radioG_profile.getCheckedRadioButtonId();
                    View radioButton = radioG_profile.findViewById(id);
                    int radioId = radioG_profile.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioG_profile.getChildAt(radioId);
                    String selection = (String) btn.getText();
                    Toast.makeText(getActivity(), selection, Toast.LENGTH_SHORT).show();
                }
*/
            }
        });

        btn_save_profile.setOnClickListener(this);
        btn_update_profile.setOnClickListener(this);
        btn_delete_profile.setOnClickListener(this);
        btn_show.setOnClickListener(this);
        return view;
    }

    void setNumberPickers() {
        NumberPicker.OnValueChangeListener onValueChangeListenerAge =
                new NumberPicker.OnValueChangeListener() {

                    @Override
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                        /*Toast.makeText(getActivity(),
                                "selected number " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();*/
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
                 /*if (radioG_profile.getCheckedRadioButtonId() != -1) {
                    int idRG = radioG_profile.getCheckedRadioButtonId();
                    View radioButton = radioG_profile.findViewById(idRG);
                    int radioId = radioG_profile.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioG_profile.getChildAt(radioId);
                    String selection = (String) btn.getText();
                    method.saveData(getActivity(), txt_userName.getText().toString(), selection, numberPicker_age.getValue(), numberPicker_weight.getValue(), numberPicker_height.getValue());
                    dismiss();
                }*/
                break;

            case R.id.btn_update_profile:
                if (radioG_profile.getCheckedRadioButtonId() != -1) {
                    int idRG = radioG_profile.getCheckedRadioButtonId();
                    View radioButton = radioG_profile.findViewById(idRG);
                    int radioId = radioG_profile.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioG_profile.getChildAt(radioId);
                    String selection = (String) btn.getText();
                     dismiss();

                }

            case R.id.btn_delete_profile:

                dismiss();

            case R.id.btn_show:


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