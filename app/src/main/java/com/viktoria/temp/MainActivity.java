package com.viktoria.temp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView mCameraView;
    TextView mTextView;
    CameraSource mCameraSource;

    private static final String TAG = "MainActivity";
    private static final int requestPermissionID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCameraView = findViewById(R.id.surfaceView);
        mTextView = findViewById(R.id.text_view);

        startCameraSource();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != requestPermissionID) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mCameraSource.start(mCameraView.getHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startCameraSource() {

        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()) {
            Log.w(TAG, "Detector dependencies not loaded yet");
        } else {

            mCameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setAutoFocusEnabled(true)
                    .setRequestedFps(2.0f)
                    .build();

            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {

                        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    requestPermissionID);
                            return;
                        }
                        mCameraSource.start(mCameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    mCameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {
                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size() != 0) {

                        mTextView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                //for (int i = 0; i < items.size(); i++) {
                                    TextBlock item = items.valueAt(0);
                                    if (item.getValue().equals("B"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "B");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("C"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "C");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("D"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "D");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("E"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "E");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("F"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "F");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("G"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "G");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("S"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "S");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("M"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "M");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("L"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("build", "L");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    else if (item.getValue().equals("D953"))
                                    {
                                        Intent SecAct = new Intent(getApplicationContext(), SecondActivity.class);
                                        SecAct.putExtra("teacher", "Кленин Александр Сергеевич");
                                        mCameraSource.stop();
                                        startActivity(SecAct);
                                    }
                                    //stringBuilder.append(item.getValue());
                                    //stringBuilder.append("\n");
                                //}
                                mTextView.setText(stringBuilder.toString());
                            }
                        });
                    }
                }
            });
        }
    }
}