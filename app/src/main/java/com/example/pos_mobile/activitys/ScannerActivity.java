package com.example.pos_mobile.activitys;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.content.pm.PackageManager;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.pos_mobile.R;
import com.google.zxing.Result;

public class ScannerActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    Button btnPress;
    ImageView backArrow;
    ImageView torch;
    Switch scanSwitch;
    boolean hasCameraFlash = false;
    boolean flashOn = true;
    boolean pressed=false;
    boolean decode_switch= true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        getSupportActionBar().hide();
        backArrow = findViewById(R.id.backArrow);
        torch = findViewById(R.id.torch);
        btnPress=findViewById(R.id.btnScan);
        scanSwitch=findViewById(R.id.scanSwitch);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        hasCameraFlash = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=true;

            }
        });
        scanSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    decode_switch=true;
                }else {
                    decode_switch=false;
                }
            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.startPreview();

        torch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flashOn) {
                    mCodeScanner.setFlashEnabled(false);
                    flashOn = false;
                    torch.setImageResource(R.drawable.torchoff);

                } else {
                    mCodeScanner.setFlashEnabled(true);
                    flashOn = true;
                    torch.setImageResource(R.drawable.torch);
                }
            }
        });

        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(decode_switch || pressed){
                            Toast.makeText(ScannerActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                            mCodeScanner.startPreview();
                        }
                        mCodeScanner.startPreview();
                    }
                });
            }
        });
    }


}