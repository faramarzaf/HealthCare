package com.faramarz.tictacdev.healthcare.StarterPageFragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faramarz.tictacdev.healthcare.R;
import com.faramarz.tictacdev.healthcare.main_categories.AvoidSmokingActivity;
import com.faramarz.tictacdev.healthcare.main_categories.DoctorVisitReminder;
import com.faramarz.tictacdev.healthcare.main_categories.EducationTopicsActivity;
import com.faramarz.tictacdev.healthcare.main_categories.ManageBodyActivity;
import com.faramarz.tictacdev.healthcare.main_categories.ManageDaruActivity;
import com.faramarz.tictacdev.healthcare.main_categories.ManageFoodActivity;
import com.faramarz.tictacdev.healthcare.main_categories.ManageStressActivity;
import com.faramarz.tictacdev.healthcare.main_categories.ParvandeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.textView8)
    TextView textView8;


    public HomeFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);
        textView8.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.textView1:
                Intent intent = new Intent(getActivity(), ManageDaruActivity.class);
                startActivity(intent);
                break;
            case R.id.textView2:
                Intent intent2 = new Intent(getActivity(), ParvandeActivity.class);
                startActivity(intent2);
                break;
            case R.id.textView3:
                Intent intent3 = new Intent(getActivity(), ManageFoodActivity.class);
                startActivity(intent3);
                break;
            case R.id.textView4:
                Intent intent4 = new Intent(getActivity(), ManageStressActivity.class);
                startActivity(intent4);
                break;
            case R.id.textView5:
                Intent intent5 = new Intent(getActivity(), AvoidSmokingActivity.class);
                startActivity(intent5);
                break;
            case R.id.textView6:
                Intent intent6 = new Intent(getActivity(), ManageBodyActivity.class);
                startActivity(intent6);
                break;
            case R.id.textView7:
                Intent intent7 = new Intent(getActivity(), DoctorVisitReminder.class);
                startActivity(intent7);
                break;
            case R.id.textView8:
                Intent intent8 = new Intent(getActivity(), EducationTopicsActivity.class);
                startActivity(intent8);
                break;
            default:
                break;
        }

    }


}


