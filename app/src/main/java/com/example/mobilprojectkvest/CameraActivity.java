package com.example.mobilprojectkvest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;
    private Button mapBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setRequestedOrientation(ActivityInfo. SCREEN_ORIENTATION_PORTRAIT);

        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.cameraview );
        mSurfaceHolder = surfaceView.getHolder();
        mSurfaceHolder.addCallback( this );
        mSurfaceHolder.setType(SurfaceHolder. SURFACE_TYPE_PUSH_BUFFERS );
        mapBtn = findViewById(R.id.btnMap);

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CameraActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        mCamera = Camera.open();
        mCamera.setDisplayOrientation( 90);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        if ( mCamera != null ) {
            try {
                mCamera .setPreviewDisplay( mSurfaceHolder);
                mCamera .startPreview();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null ;
    }
}