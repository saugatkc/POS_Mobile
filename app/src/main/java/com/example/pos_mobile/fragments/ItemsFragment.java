package com.example.pos_mobile.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pos_mobile.R;
import com.example.pos_mobile.activitys.ScannerActivity;


public class ItemsFragment extends Fragment {

ImageView barcode;
    private static final int REQUEST_CAMERA_PERMISSION = 201;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        barcode=view.findViewById(R.id.barcode);
        barcode.setOnClickListener(v -> {

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(getContext(), ScannerActivity.class));


            } else {
                ActivityCompat.requestPermissions(getActivity(), new
                        String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);

            }

        });
        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        switch (requestCode){
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(new Intent(getContext(), ScannerActivity.class));
                }else {
                    Toast.makeText(getContext(), "This Feature needs camera access", Toast.LENGTH_LONG).show();
                }
        }
    }
}