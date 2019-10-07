package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_2);

        Intent int1 = getIntent();
        int1.getStringArrayExtra("First Gesture:");
        Button bt2 = (Button)findViewById(R.id.practise_button);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(int2);
            }
        });

    }

}
