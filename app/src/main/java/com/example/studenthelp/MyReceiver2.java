package com.example.studenthelp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class MyReceiver2 extends BroadcastReceiver {
    String CHANNEL_ID="rgrg";
    @Override
    public void onReceive(Context context, Intent intent) {

        Notification.Builder builder = new Notification.Builder(context);
        Notification notification = null;
        notification = builder.setContentTitle("UPCOMMING CLASS in        DAY")
                .setContentText(    " day  "  )
                .setSmallIcon(R.drawable.ic_access_time_black_24dp)
                .setAutoCancel(true)
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)

                .build();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID);
        }


        /// to manage the builders.....
        /// manager the boss... builders the worker  & notification the CLIENT....

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "askmfsd",
                    "NotificationDemo",
                    NotificationManager.IMPORTANCE_HIGH

            );


            notificationManager.createNotificationChannel(channel);

        }

// call notification manager to notify with this notification..
        notificationManager.notify(0, notification);

    }
}
