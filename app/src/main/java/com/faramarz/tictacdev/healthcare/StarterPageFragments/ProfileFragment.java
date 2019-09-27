package com.faramarz.tictacdev.healthcare.StarterPageFragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.faramarz.tictacdev.healthcare.CustomDialogs.CustomEditInfoProfileDialog;
import com.faramarz.tictacdev.healthcare.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.makeramen.roundedimageview.RoundedImageView;
import java.io.File;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    public static final String Gender = "genderKey";
    public static final String Age = "ageKey";
    public static final String Weight = "weightKey";
    public static final String Height = "heightKey";


    @BindView(R.id.txt_change_avatar)
    TextView txt_change_avatar;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.txt_sex)
    TextView txt_sex;
    @BindView(R.id.txt_age)
    TextView txt_age;
    @BindView(R.id.txt_height)
    TextView txt_height;
    @BindView(R.id.txt_weight)
    TextView txt_weight;
    @BindView(R.id.avatar)
    RoundedImageView avatar;
    @BindView(R.id.edit_profile_info)
    ImageView edit_profile_info;


    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String name = preferences.getString(Name, "");
        String gender = preferences.getString(Gender, "");
        int age = preferences.getInt(Age, 0);
        int height = preferences.getInt(Height, 0);
        int weight = preferences.getInt(Weight, 0);

        txt_name.setText(name);
        txt_sex.setText(gender);
        txt_age.setText(String.valueOf(age));
        txt_height.setText(String.valueOf(height));
        txt_weight.setText(String.valueOf(weight));


        txt_change_avatar.setOnClickListener(this);
        edit_profile_info.setOnClickListener(this);


        return view;

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.txt_change_avatar:
                getPermissions();
                showImagePickDialog(v);
                break;

            case R.id.edit_profile_info:
                showAlertDialog();
                break;

            default:
                break;
        }

    }

    private void showAlertDialog() {
        CustomEditInfoProfileDialog customEditInfoProfileDialog = new CustomEditInfoProfileDialog();
        customEditInfoProfileDialog.show(getFragmentManager(), "CustomEditInfoProfileDialog");

    }


    public void showImagePickDialog(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true)
                .setSingleChoiceItems(new String[]{"Open Gallery", "Open Camera", "Remove current image"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            EasyImage.openGallery(ProfileFragment.this, 0);
                            dialog.dismiss();
                        }
                        if (which == 1) {
                            EasyImage.openCamera(ProfileFragment.this, 0);
                            dialog.dismiss();
                        }

                        if (which == 2) {
                            avatar.setImageResource(android.R.color.transparent);
                            dialog.dismiss();
                        }
                    }
                });
        builder.show();
    }

    void getPermissions() {

        Dexter.withActivity(getActivity())
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.INTERNET,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE

                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                Glide.with(ProfileFragment.this).load(imageFile).into(avatar);

            }


        });
    }

}


