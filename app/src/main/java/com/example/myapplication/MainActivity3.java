package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity3 extends AppCompatActivity {

    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        startRecording();

        Button bt2 = (Button)findViewById(R.id.upload_button);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(int2);
            }
        });
    }

    public void startRecording()
    {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        String pn = Integer.toString(Global.count + 1);
        ++Global.count;
        Global.count %= 3;
        File mediaFile = new
                File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/GESTURE_PRACTICE_" + pn + "_" + Global.lastName + ".mp4");



        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5);
            fileUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, VIDEO_CAPTURE);
    }
}
