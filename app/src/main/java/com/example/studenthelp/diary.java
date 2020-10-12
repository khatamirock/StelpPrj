package com.example.studenthelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.widget.Toast.*;

public class diary extends AppCompatActivity {
TextView tvvvv;
String name;
EditText editText;
String text;
Button button;
Button load;
private static final String FILE_NAME = "example.txt";
private static final String day_name = "day.txt";
private static final String time = "time.txt";
    String timee="0";
  private int plusser =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        final Intent intent= getIntent();

button=findViewById(R.id.button2);
        name=intent.getStringExtra("day");
editText=findViewById(R.id.editText);

load=findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text=editText.getText().toString();

                FileOutputStream fos = null;

                try {

                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos = openFileOutput(time, MODE_PRIVATE);

                    fos.write(text.getBytes());
                    fos.write(timee.getBytes());


                 } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });



tvvvv=findViewById(R.id.textView3);

        load.setOnClickListener(new View.OnClickListener() {
            FileInputStream fis = null;

            @Override
            public void onClick(View view) {

                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);

                    fis = openFileInput(time);
                    InputStreamReader isr2 = new InputStreamReader(fis);

                    BufferedReader br = new BufferedReader(isr);
                    BufferedReader br2 = new BufferedReader(isr2);


                    StringBuilder sb = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();

                    String text;

                    while ((text = br.readLine()) != null) {
                        sb.append(text).append("\n");
                    }
                    sb2.append(" ");
                    tvvvv.setText(sb.toString());


                    while ((text = br2.readLine()) != null) {
                        sb2.append(text).append(" ");
                    }

                    tvvvv.setText(sb2.toString());




                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });

}









    }
