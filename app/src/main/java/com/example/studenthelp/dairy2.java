package com.example.studenthelp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class dairy2 extends AppCompatActivity {

int move=0;
    TextView tv;

    Button alert;
    Button load;
    String CHANNEL_ID = "nameer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy2);

//
//        alert = findViewById(R.id.button6);
//        tv = findViewById(R.id.textView4);
//        load = findViewById(R.id.button8);
//
//        load.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(dairy2.this,notMESS.class);
//                startActivity(intent);
//
//            }
//        });





//        tv.setMovementMethod(new ScrollingMovementMethod());
//
//
//        Intent home = new Intent(this, MainActivity.class);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//        // intenet adder..........
//        stackBuilder.addNextIntent(home);
//
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
//                PendingIntent.FLAG_UPDATE_CURRENT);

        Toast.makeText(this, "toasted & opened....", Toast.LENGTH_LONG).show();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                "32");


        Notification notification = null;
        notification = builder.setContentTitle("IN DAIRY FARM..")
                .setContentText("")
                .setSmallIcon(R.drawable.ic_access_time_black_24dp)
                .setAutoCancel(true)
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();


        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "3456456",
                    "NotificationDemo",
                    NotificationManager.IMPORTANCE_HIGH

            );


            notificationManager.createNotificationChannel(channel);

        }


        notificationManager.notify(0, notification);


        String names[] = {"1.txt", "2.txt", "3.txt", "4.txt", "5.txt"};
        String datas[] = {"4", "6", "1", "2", "3"};


        FileOutputStream fos = null;

//
//
//        alert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//String temp;
//
//                  temp=read("5.txt");
//
//                 called("5.txt",read("4.txt"));
//                 called("4.txt",read("3.txt"));
//                 called("3.txt",read("2.txt"));
//                 called("2.txt",read("1.txt"));
//                  called("1.txt",temp);
//
//
//            }
//        });





 }


    private void called(String name,String datas) {
        String names = name;
        int data;



        tv.setText(datas);
        FileOutputStream fos = null;

            try {

                fos = openFileOutput(names, MODE_PRIVATE);
                fos.write(datas.getBytes());
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


    private String read(String name) {
        FileInputStream fis = null;
        String FILE_NAME = name;
        String ret = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);


            BufferedReader br = new BufferedReader(isr);


            StringBuilder sb = new StringBuilder();


            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            ret = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}


