package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final String[] paths = {"", "gift", "car", "pay", "pet", "sell", "explain", "that", "book", "now", "work", "total"
            , "trip", "future", "good", "thank you", "learn", "agent", "should", "like", "movie"};
    private boolean isSpinnerInitial = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,paths);
        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(this);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        if(isSpinnerInitial)
        {
            isSpinnerInitial = false;
        }
        else {
            final TextView helloTextView = (TextView) findViewById(R.id.editText);
            String lastName = helloTextView.getText().toString();
            Global.lastName = lastName;
            Toast.makeText(this, "Last Name : " + lastName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            //Create the bundle
            Bundle bundle = new Bundle();
            bundle.putString("gesture", paths[position]);
            //intent.putExtra("First Gesture:", paths[position]);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        //Toast.makeText(this, "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        /*
        switch (position) {
            case 0:

                break;
            case 1:
                Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent2);
                //Toast.makeText(this, "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent intent3 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent3);
            case 3:
                Intent intent4 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent4);
            case 4:
                Intent intent5 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent5);
            case 5:
                Intent intent6 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent6);
            case 6:
                Intent intent7 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent7);
            case 7:
                Intent intent8 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent8);
            case 8:
                Intent intent9 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent9);
            case 9:
                Intent intent10 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent10);
            case 10:
                Intent intent11 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent11);
            case 11:
                Intent intent12 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent12);
            case 12:
                // Whatever you want to happen when the thrid item gets selected
            case 13:
                // Whatever you want to happen when the thrid item gets selected
            case 14:

            case 15:

            case 16:

            case 17:
                Intent intent16 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent16);
            case 18:
                Intent intent17 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent17);
            case 19:
                Intent intent19 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent19);
            case 20:
                Intent intent20 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent20);
                break;

        }

         */
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}
