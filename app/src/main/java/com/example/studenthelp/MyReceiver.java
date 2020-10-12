package com.example.studenthelp;

import
        android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import java.io.File;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;
import static android.preference.PreferenceManager.getDefaultSharedPreferences;


public class MyReceiver extends BroadcastReceiver {



    private static final String CHANNEL_ID = "rocked";
    String days;
    int count   ;
    int hour   ;


    @Override
    public void onReceive(Context context, Intent intent) {




        //intent.putExtra()
        int day;

        String DY[] = {" ", "A", "B", "C", "D", "E"};

        int time;

        Calendar cal = Calendar.getInstance();

        hour = cal.get(Calendar.HOUR_OF_DAY);
        day = cal.get(Calendar.DAY_OF_WEEK);
        time = cal.get(Calendar.HOUR_OF_DAY);
        switch (day) {
            case 1:
                days = "D";
                break;
            case 2:
                days = "E";
                break;
            case 3:
                days = "A";
                break;
            case 4:
                days = "B";
                break;
            case 7:
                days = "C";
                break;
        }

        String clas = null;


        String aday[] = {"EEE", "CSE (sa)", " MATH", "HUM ", "---"};
        String bday[] = {"--", "EEE", "MATH", " --", " --", " --"};
        String cday[] = {"CSE(sa)", "CSE(rt)", " EEE ", " (BREAK) ", " HUM ", "MATH"};
        String dday[] = {"EEE-LAB ", "break", "CSE(rt)", "HUM", "CSE(sa) "};
        String eday[] = {"CSE(lab) ", "-- ", " --", "-- ", "-- "};

        if (time == 7) {
            if (days.equals("A")) {
                clas = aday[0];
            }
            if (days.equals("B")) {
                clas = bday[0];
            }
            if (days.equals("C")) {
                clas = cday[0];
            }
            if (days.equals("D")) {
                clas = dday[0];
            }
            if (days.equals("E")) {
                clas = eday[0];
            }
        }
        if (time >= 8) {
//            if (days.equals("A")) {
//                clas = aday[1];
//            }
//            if (days.equals("B")) {
//                clas = bday[1];
//            }
//            if (days.equals("C")) {
//                clas = cday[1];
//            }
//            if (days.equals("D")) {
//                clas = dday[1];
//            }
//            if (days.equals("E")) {
//                clas = eday[1];
//            }
        }

        if (time >= 9) {
//            if (days.equals("A")) {
//                clas = aday[2];
//            }
//            if (days.equals("B")) {
//                clas = bday[2];
//            }
//            if (days.equals("C")) {
//                clas = cday[2];
//            }
//            if (days.equals("D")) {
//                clas = dday[2];
//            }
//            if (days.equals("E")) {
//                clas = eday[2];
//            }
        }

        if (time >= 10) {
//            if (days.equals("A")) {
//                clas = aday[3];
//            }
//            if (days.equals("B")) {
//                clas = bday[3];
//            }
//            if (days.equals("C")) {
//                clas = cday[3];
//            }
//            if (days.equals("D")) {
//                clas = dday[3];
//            }
//            if (days.equals("E")) {
//                clas = eday[3];
//            }
        }
        if (time >= 11) {
//            if (days.equals("A")) {
//                clas = aday[4];
//            }
//            if (days.equals("B")) {
//                clas = bday[4];
//            }
//            if (days.equals("C")) {
//                clas = cday[4];
//            }
//            if (days.equals("D")) {
//                clas = dday[4];
//            }
//            if (days.equals("E")) {
//                clas = eday[4];
//            }
        }


        // SHARED PREFERENCE....


        Intent notificationIntent = new Intent(context, MainActivity.class);
        //   Intent notificationIntent2 = new Intent(context, dairy2.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        //   TaskStackBuilder stackBuilder2 = TaskStackBuilder.create(context);
// to create pending intent...........
        stackBuilder.addParentStack(diary.class);
        // stackBuilder2.addParentStack(dairy2.class);

        stackBuilder.addNextIntent(notificationIntent);
        //  stackBuilder2.addNextIntent(notificationIntent2);


        //   PendingIntent pendingIntent2 = stackBuilder2.getPendingIntent(23,PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Bundle b = intent.getExtras();
        String name = b.getString("day");
if(hour>6 && hour<14){
        Notification.Builder builder = new Notification.Builder(context);

        Notification notification = builder.setContentTitle("READY FOR YOUR UPCOMMING CLASS ")
                .setContentText("stELP is here.....")
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "STELP",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notification);

    }


}

    }




