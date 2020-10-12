package com.example.studenthelp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    //  SharedPreferences pref =getApplicationContext().getSharedPreferences("myPrefsKey",this.MODE_PRIVATE) ;



        DatabaseReference reff;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference mDbRef = mDatabase.getReference("Donor/Name");
    BottomNavigationView bottomNavigationView;
Toolbar toolbar;
    Button nfc;
    FileOutputStream fos = null;

    Button Load_fireBase;
    Button activ;
    Button nxtday;
    TextView tv;
    int atos=0;
    Button auto;
    Button DAYfix;
    TextView imv;
    String classh2;
    Calendar cal = Calendar.getInstance();
   public String clas[] = {

            "  ",
            "  - EEE -(8:00 - 8:50 ) \n - CSE (sa)      (8:50 - 9:40 )\n" +
  " - MATH (9:41 - 10:30 )\n\n BREAK OF(10:30) \n\n - HUM (10:50 - 11:40)\n-CSE(rt) (11:40-12:30)\n\n  BREAK UNTILL - (2:29)\n\n"+
                    "CSE(rt)-LAB  (2:30 - 5:00)  (2nd 30)\n\n\n\n,..",


  "(no class)\n -EEE   (8:50 - 9:40 ) \n- MATH   (9:41 - 10:30 ) \n\n-CSE(szl)- LAB - (10:50-1:20) \n BREAK \n",

  "-CSE(sa) -(8:00 - 8:50 ) \n-CSE(RT)  (8:50 - 9:40 ) \n-EEE(akp)  (9:41 - 10:30 )"   +
                    "\n\n BREAK OF(10:30)\n\n" +
                    "-HUM(ABS) (10:50 - 11:40)   \n-MATH(mah)  (11:40 - 12:30)\n",

            "-EEE-LAB   (8:00 - 8:50 ) \n\n  BREAK OF(10:30) \n\n-CSE(rt) " +
                    "   (10:50 - 11:40)\n -HUM " +
                    "  (11:41 - 12:30) \n-CSE(sa)   (12:31 - 1:20) ",


            "----(2nd 30)--------\n-CSE(sa)-LAB  (8:10 -10:30) \n\n BREAK OF 10:30\n\n ---(1st 30)--------\n-CSE(rt)- LAB  (10:50 -  1:20) \n",
            " "



    };

    String DY[] = {" ","A", "B", "C", "D",   "E"};
   int theNextDay_count=0;
    int count = 6;

    String paths;
    int day;



    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                Intent i = new Intent(MainActivity.this,
//                        theOPEN.class);
//                startActivity(i);
//                finish();
//            }
//        }, 3000);
////

        reff= FirebaseDatabase.getInstance().getReference().child("19");


        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDbRef = mDatabase.getReference("Donor/Name");
bottomNavigationView=findViewById(R.id.nav_ba);
//toolbar=findViewById(R.id.toolbar);
     long startTime = (long) (2 * 60 * 1000);

        AlarmManager alarmManager = (AlarmManager) getSystemService(this.ALARM_SERVICE);

        final Intent notificationIntent = new
 Intent(this, MyReceiver.class);

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
       // alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        notificationIntent.putExtra("day",getday());

        alarmManager.setInexactRepeating(AlarmManager.RTC,
                SystemClock.elapsedRealtime() +
                        startTime, (long) (15 * 60 * 1000), broadcast);


        String names[] = {"1.txt", "2.txt", "3.txt", "4.txt", "5.txt","nameer1.txt","auto.txt"};
        final String datas[] = {"4", "5", "1", "2", "3","0","0"};


        /// only for ontime.....

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {





            for (int i = 0; i < 7; i++) {


                try {

                    fos = openFileOutput(names[i], MODE_PRIVATE);
                    fos.write(datas[i].getBytes());
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
////////////////



            prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart", false);
            editor.apply();
        }
//////////////

        imv = findViewById(R.id.textView2);
        tv = findViewById(R.id.textView);



        nxtday=findViewById(R.id.button9);
 //nextcls=findViewById(R.id.textView8);
 //nextcls=findViewById(R.id.textView8);


        day = cal.get(Calendar.DAY_OF_WEEK);

        nfc = findViewById(R.id.not);
        DAYfix=findViewById(R.id.auto);




        nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setMovementMethod(new ScrollingMovementMethod());
String ady;
int num;


             //   String userId = reff.push().getKey();
            //    User user = new User("Hillary", "hillary@xyz.com", "90097863873");

               // reff.child(userId).setValue(user);



                ady=reader("auto.txt");
                num= Integer.valueOf(ady);
                int diff=cal.get(cal.DAY_OF_MONTH)-num;


if(diff<0){
    diff=1;
}
                ady=reader("nameer1.txt");
                num= Integer.valueOf(ady);

diff=num-diff;
//called("nameer1.txt",Integer.toString(diff));



              //  Toast.makeText(MainActivity.this,Integer.toString(diff),Toast.LENGTH_LONG).show();

                // tv.setText("now the time is : " + hour + " and : " + minute + " minuite and " + sec + " second.. day " + day + " am/pm" + am_pm);

                // Intent intent=new Intent(getApplicationContext(), Notification_receiver)
                int numDAY;

                final String setter;
                theNextDay_count= Integer.valueOf(ady);

                if (diff<=-1) {
                    called("nameer1.txt",datas[5]);




                    if (day == 1) {
// e day
                        ady = reader("1.txt");
                        numDAY = Integer.valueOf(ady);
                        tv.setText(read(DY[numDAY]+"day.txt"));

                        imv.setText(DY[numDAY]);

                    } else if (day == 2) {
// a day
//                    setter = called("name4");
//                    tv.setText(setter);
                        ady = reader("2.txt");
                        numDAY = Integer.valueOf(ady);
                        tv.setText(read(DY[numDAY]+"day.txt"));

                        imv.setText(DY[numDAY]);
                        //   String day=reader();

                    } else if (day == 3) {
// b day
                        ady = reader("3.txt");
                        numDAY = Integer.valueOf(ady);
                        tv.setText(read(DY[numDAY]+"day.txt"));

                        imv.setText(DY[numDAY]);   //// .....


                    } else if (day == 4) {// cday..
//                    setter = called("name1");
//                    tv.setText(setter);
                        ady = reader("4.txt");
                        numDAY = Integer.valueOf(ady);

                        tv.setText(read(DY[numDAY]+"day.txt"));

                        imv.setText(DY[numDAY]);


                    } else if (day == 7) {// cday..
//                    setter = called("name6");
//                    tv.setText(setter);
                        ady = reader("5.txt");
                        numDAY = Integer.valueOf(ady);

                        tv.setText(read(DY[numDAY]+"day.txt"));

                        imv.setText(DY[numDAY]);


                    } else {
                        tv.setText("TODAY IS HOLIDAY.....GO TAKE A BREAK...");
                    }

                }

                else {Toast.makeText(MainActivity.this,"you are in break for "+ diff+" days",Toast.LENGTH_LONG).show();

                    tv.setText("PROBALBLE CAUSES\n- YOU ARE ON A BREAK...\n -OR YOUR CLASS IS SUSPENDED..." +
                            "\n-OR YOUR VARSITY IS ON AUTO MODE....\n\n NOT THE CAUSE. ? PLAESE RESET THE APP DATA \n" +
                            "AND SIMPLY TAP THE  --'DAY FIX'--  BUTTON TO FIX THE DAY ACCORDINGLY.....\n" +
                            "otherwise it is inadvised to click that button.....\n\n\n");
                }
            }
        });
//        final Intent intent = new Intent(this, diary.class);
//        activ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                startActivity(intent);
//            }
//        });


        if (day == 5 || day == 6) {

            tv.setText("today is holiday...........");
        } else {


           // imv.setText(DY[day - 1]);
            tv.setText("\n\n click on the 'CLASSES' to get some\n INFO...");
        }



nxtday.setOnClickListener(new View.OnClickListener() {
    int numDAY;
    String ady;
    @Override
    public void onClick(View view) {
theNextDay_count++;


        String ady;
        int num;

        ady=reader("auto.txt");
        num= Integer.valueOf(ady);
        int diff=cal.get(cal.DAY_OF_MONTH)-num;
        if(diff<0){
            diff=1;
        }
        ady=reader("nameer1.txt");
        num= Integer.valueOf(ady);

        diff=num-diff;
        if (diff<=-1) {

            if (day == 1) {
// e day
                ady = reader("1.txt");
                numDAY = Integer.valueOf(ady);

                if (DY[numDAY].equals("E")) {
                    imv.setText("A");
                    tv.setText(read("Aday.txt"));

                } else {
                   // tv.setText(clas[numDAY + 1]);
                    tv.setText(read(DY[numDAY+1]+"day.txt"));

                    imv.setText(DY[numDAY + 1]);
                }
            } else if (day == 2) {
// a day
                ady = reader("2.txt");
                numDAY = Integer.valueOf(ady);

                if (DY[numDAY].equals("E")) {
                    imv.setText("A");
                   // tv.setText(clas[1]);
                    tv.setText(read("Aday.txt"));
                } else {
                   // tv.setText(clas[numDAY + 1]);
                    tv.setText(read(DY[numDAY+1]+"day.txt"));

                    imv.setText(DY[numDAY + 1]);
                }
            } else if (day == 3) {
// b day
                ady = reader("3.txt");
                numDAY = Integer.valueOf(ady);


                if (DY[numDAY].equals("E")) {
                    imv.setText("A");
                   // tv.setText(clas[1]);
                    tv.setText(read("Aday.txt"));
                } else {
                   // tv.setText(clas[numDAY + 1]);
                    tv.setText(read(DY[numDAY+1]+"day.txt"));
                    imv.setText(DY[numDAY + 1]);
                }
            } else if (day == 4) {// cday..
                ady = reader("4.txt");
                numDAY = Integer.valueOf(ady);


                if (DY[numDAY].equals("E")) {
                    imv.setText("A");
                   // tv.setText(clas[1]);
                    tv.setText(read("Aday.txt"));

                } else {
                    //tv.setText(clas[numDAY + 1]);
                    tv.setText(read(DY[numDAY+1]+"day.txt"));

                    imv.setText(DY[numDAY + 1]);
                }
            } else if (day == 7) {// cday..
                ady = reader("5.txt");
                numDAY = Integer.valueOf(ady);

                if (DY[numDAY].equals("E")) {
                    imv.setText("A");
                   // tv.setText(clas[1]);
                    tv.setText(read("Aday.txt"));
                } else {
                  //  tv.setText(clas[numDAY + 1]);
                    tv.setText(read(DY[numDAY+1]+"day.txt"));

                    imv.setText(DY[numDAY + 1]);
                }
            } else {
                tv.setText("TOMORROW IS ALSO HOLIDAY.....GO TAKE A BREAK...");
            }
        }
        else {
            Toast.makeText(MainActivity.this,"you are in break for "+ diff+" days",Toast.LENGTH_LONG).show();
            tv.setText("PROBALBLE CAUSES\n- YOU ARE ON A BREAK...\n -OR YOUR CLASS IS SUSPENDED..." +
                    "\n-OR YOUR VARSITY IS ON AUTO MODE....\n\n NOT THE CAUSE. ? PLAESE RESET THE APP DATA \n" +
                    "AND SIMPLY TAP THE  --'DAY FIX'--  BUTTON TO FIX THE DAY ACCORDINGLY.....\n" +
                    "otherwise it is inadvised to click that button.....\n\n\n");

        }
    }
});


        DAYfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                PopupMenu popup = new PopupMenu(MainActivity.this, view);
                popup.setOnMenuItemClickListener(MainActivity.this);
                // need to be (alt+enter)

                popup.inflate(R.menu.popup);







                popup.show();

            }
        });



// the auto button.....................................................





bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.download:
Intent dwn= new Intent(MainActivity.this,downloader.class);
startActivity(dwn);
                break;

            case R.id.chomp:
                Intent intent1 = new Intent(MainActivity.this, notMESS.class);
                startActivity(intent1);
                break;

            case R.id.about:
                Intent intent2 = new Intent(MainActivity.this, dairy2.class);
                startActivity(intent2);
                break;

        }

        return false;
    }
});



}
private int getday(){

        String ady;
        int numDAY = 0;

    if (day == 1) {
        ady=reader("1.txt");
        numDAY= Integer.valueOf(ady);


    } else if (day == 2) {
        ady=reader("2.txt");
        numDAY= Integer.valueOf(ady);



    } else if (day == 3) {

        ady=reader("3.txt");
        numDAY= Integer.valueOf(ady);

    } else if (day == 4) {
        ady=reader("4.txt");
        numDAY= Integer.valueOf(ady);

    } else if (day == 7) {
        ady=reader("5.txt");
        numDAY= Integer.valueOf(ady);

    }


    return  numDAY;
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

    private String reader(String name) {


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
                sb.append(text).append("");
            }
            ret = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    @Override
    protected void onPause() {
        super.onPause();
        long startTime = (long) (2 * 60 * 1000);




        AlarmManager alarmManager = (AlarmManager) getSystemService(this.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, MyReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        // alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        alarmManager.setInexactRepeating(AlarmManager.RTC,
                SystemClock.elapsedRealtime() +
                        startTime, (long) (15 * 60 * 1000), broadcast);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        long startTime = (long) (2 * 60 * 1000);

        AlarmManager alarmManager = (AlarmManager) getSystemService(this.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, MyReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        // alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        alarmManager.setInexactRepeating(AlarmManager.RTC,
                SystemClock.elapsedRealtime() +
                        startTime, (long) (15 * 60 * 1000), broadcast);



    }

    public String monDAY(String str)
    {
        int num=0;


        String days[]={"","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16",
                "17","18","19","20","21","22","23","24","25","26","27","28",
                "29","30","31"};

        for(int i=1;i<32;i++)
        {
            if(str.equals(days[i])){
                return days[i];
            }
        }


        return days[num];

    }


    public  void theCHANGER()
    {


        String temp;

        temp=read("5.txt");

        called("5.txt",read("4.txt"));
        called("4.txt",read("3.txt"));
        called("3.txt",read("2.txt"));
        called("2.txt",read("1.txt"));
        called("1.txt",temp);


    }

      String[] Store = new String[10];

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {


            case R.id.classAdd:{
                final Intent intent = new Intent(this, fill_the_base.class);
                startActivity(intent);
                return true;


            }

            case  R.id.seca19:{
                downFrom_fireBase("seca");return  true;
            }

            case  R.id.secb19:{
                downFrom_fireBase("secb");return  true;
            }
            case  R.id.secc19:{
                downFrom_fireBase("secc");return  true;
            }



            case R.id.one8: {

                Toast.makeText(this,"NOTHIN' YET for 18",Toast.LENGTH_LONG).show();return  true;
}

            case R.id.one7:{
              //  downFrom_fireBase("secb");
                Toast.makeText(this,"NOTHIN' YET for 17",Toast.LENGTH_LONG).show();
                return  true;
            }

            case R.id.one6:{
              //  downFrom_fireBase("seca");
                Toast.makeText(this,"NOTHIN' YET for 16",Toast.LENGTH_LONG).show();return  true;
            }

            case R.id.one5:{
                Toast.makeText(this,"15",Toast.LENGTH_LONG).show();return  true;
            }

            case R.id.one9:{
                Toast.makeText(this,"19",Toast.LENGTH_LONG).show();return  true;
            }
            case R.id.series:{

                Toast.makeText(this,"PLEASE select!",Toast.LENGTH_LONG).show();
return  true;
            }
            case R.id.search_item: {


                int num;
                String ady;
                int day = cal.get(cal.DAY_OF_MONTH);///
                String dy = Integer.toString(day);


                theCHANGER();

///  A VERY INPORTANT LESSON HERE....................>>>>
                /// THAT YOU CANT CONVERT IF YOU SAVE A TEXT AS STRING...
                //... BUT IF YOU KEEP THAT IN A ARRAY OF STRING .... YOU CAN CONVERET......

                ady = reader("nameer1.txt");
                num = Integer.valueOf(ady) + 1;

                if (num - 1 + day == 5) {
                    theCHANGER();

                    // theCHANGER();
                }
                if (num + day - 1 == 6) {
                    theCHANGER();
                    theCHANGER();

                    // theCHANGER();
                }


//dy=read("auto.txt");
//day=Integer.valueOf();

                called("nameer1.txt", Integer.toString(num));
                called("auto.txt", monDAY(dy));

                ady = reader("auto.txt");
                num = Integer.valueOf(ady);
                int diff = cal.get(cal.DAY_OF_MONTH) - num;
                if (diff < 0) {
                    diff = 1;
                }
                ady = reader("nameer1.txt");
                num = Integer.valueOf(ady);

                diff = num - diff;


                Toast.makeText(MainActivity.this, "the break is set for " + diff + " days", Toast.LENGTH_LONG).show();


                return true;
            }
            case R.id.upload_item: {// do your code

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("ARE YOU SURE !! ");
                builder.setMessage("This is gonna mess up with the apps data !!\n ITS ON YOU...");
                builder.setIcon(R.drawable.ic_access_time_black_24dp);

                builder.setPositiveButton("YEPPP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        theCHANGER();


                        Toast.makeText(MainActivity.this, "TAKEN CARE OF...", Toast.LENGTH_LONG).show();

                    }
                });

                builder.setNegativeButton("nope", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "OKS...", Toast.LENGTH_LONG).show();

                    }
                });
                builder.create();
                builder.show();


                return true;
            }
            case R.id.copy_item: {

                final Intent intent = new Intent(this, diary.class);


                startActivity(intent);
                return true;
            }

            case  R.id.find:{


   final Intent intent = new Intent(this, searchACTIVITY.class);


                startActivity(intent);
                return true;


            }






            default:
                return false;
        }
    }

    private void downFrom_fireBase(String sec) {


        classh2=null;

        reff.child("even").child(sec).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    ////  Log.i(null, child.getKey());
                    Log.i(null, child.getValue(String.class));

                    classh2 += child.getValue(String.class) +"\n\n";



                    //     classh[0] +=classh2;

                }
                classh2= classh2.substring(4,classh2.length());


                try {
                    fos = openFileOutput("Aday.txt", MODE_PRIVATE);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(classh2.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                classh2=read("Aday.txt");

                int pos=0;
                String ins="\n";

                classh2.replaceAll(",","\n");

                //   classh2 = classh2.replaceAll("[^\\w\\s]","");

                String[] tokens = new String[40];
                int i=0;

                StringTokenizer stringTokenizer = new StringTokenizer(classh2,"||");
                while(stringTokenizer.hasMoreTokens()){

//tokens[i]=stringTokenizer.nextToken();

                    tokens[i]=stringTokenizer.nextToken();
                    System.out.println(tokens[i]);


                    try {

                        fos = openFileOutput(DY[i+1]+"day.txt", MODE_PRIVATE);
                        tokens[i]=tokens[i].replaceAll(",","\n");
                        fos.write(tokens[i].getBytes());
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

                    i++;




                    // System.out.println("\n\n\n");
                }
                System.out.println(i);



                tv.setText("DATA LOADING COMLPETED......" +
                        "thank you for waiting...");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //    Log.w(TAG, "Failed to read value.", error.toException());
            }
        });







        Toast.makeText(this,"18",Toast.LENGTH_LONG).show();

    }
}
