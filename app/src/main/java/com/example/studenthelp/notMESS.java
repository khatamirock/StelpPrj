package com.example.studenthelp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.graphics.Color.GRAY;

public class notMESS extends AppCompatActivity implements View.OnClickListener {
    int button_num=23;
    int move=0;
      Button b[]=new Button[25];
      String plr1[]=new String[25];
     // String plr2[]=new String[25];
    final Random myRandom = new Random();
TextView tv;
TextView tv1;
TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_mess);

Button reset;

//
//for(int i=1;i<=24;i++)
//{
//    plr1[i]="1";
//
//
//}
tv=findViewById(R.id.textView9);

        reset=findViewById(R.id.reset);



b[1]=findViewById(R.id.on1);
b[2]=findViewById(R.id.on2);
b[3]=findViewById(R.id.on3);
b[4]=findViewById(R.id.on4);
b[5]=findViewById(R.id.on5);
b[6]=findViewById(R.id.on6);
b[7]=findViewById(R.id.on7);
b[8]=findViewById(R.id.on8);
b[9]=findViewById(R.id.on9);
b[10]=findViewById(R.id.on10);
b[11]=findViewById(R.id.on11);
b[12]=findViewById(R.id.on12);
b[13]=findViewById(R.id.on13);
b[14]=findViewById(R.id.on14);
b[15]=findViewById(R.id.on15);
b[16]=findViewById(R.id.on16);
b[17]=findViewById(R.id.on17);
b[18]=findViewById(R.id.on18);
b[19]=findViewById(R.id.on19);
b[20]=findViewById(R.id.on20);
b[21]=findViewById(R.id.on21);
b[22]=findViewById(R.id.on22);
b[23]=findViewById(R.id.on23);
b[24]=findViewById(R.id.on24);

reset.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {

move=0;
        for(int i=1;i<25;i++){

            b[i].setBackgroundColor(Color.rgb(126,74,72));
            b[i].setText(""+i);
        }
b[19].setBackgroundColor(Color.rgb(173,69,65));

//        for(int i=1;i<=24;i++)
//        {
//            plr1[i]="1";
//
//        }

    }
});



for(int i=1;i<25;i++)
{

    String but="on"+i;
    int resID=getResources().getIdentifier(but,"id",getPackageName());

    // R.id= means -> resource.id ....///....
    b[i]=findViewById(resID);
    b[i].setOnClickListener(this);

}





    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String str = null;

        move++;



          str = (String) (  (Button)  view ).getText();
if(str.equals("19")){
    Toast.makeText(notMESS.this,"player lost",Toast.LENGTH_LONG).show();
}

 int i=0;

//         for(int k=0;k<400;k++) {
//             i++;

//              int nums = myRandom.nextInt(24) + 1;
//
//              while (plr1[nums].equals("0")  ) {
//                  nums = myRandom.nextInt(24) + 1;
//
//
//              }

//              str2 = String.valueOf(nums);
//              bul = Integer.valueOf(str2);// making integer....
//
//              makerZ2(bul); /// making the array zero....
//              zr2 = counter2(); // zero count....

//             tv1.setText(Integer.toString(zr1));
//             tv2.setText(Integer.toString(zr2-zr1));
//             if(zr2-zr1<=3){
//    break;
//}

       // tv2.setText(str2);
           //  if(zr2-zr1<=4){break;}
      //   }





        if(!str.equals("")) {
            int zer=Integer.parseInt(str);
      //   int zer2=Integer.parseInt(str2);
if(move%2==0)
tv.setText("1st player turn");
else
    tv.setText("2nd player turn");
            zero(zer);
      //    zero(zer2);
          //  plr1=plr2.clone();

        }







    }





public void makerZ(int num)
{
    int ret;


     ret=zero2(num) ;


    while (ret>=6) {
        for (int i = num; i <=ret; i++) {
            plr1[i]="0";
        }
        num -= 6;
        ret = zero2(num) ;
    }


}

    public void makerZ2(int num)
    {
        int ret;
        int make=0;

        ret=zero2(num) ;


        while (ret>=6) {
            for (int i = num; i <=ret; i++) {
                plr1[i]="0";
            }
            num -= 6;
            ret = zero2(num) ;
        }


    }




public  int counter()
{
    int count=0;

    for(int i=1;i<25;i++)
    {
        if(plr1[i].equals("0")){
            count++;
    }
    }

    return  count;
}

    public  void zero(int num){

        int ret=zero2(num);


        while (ret>=6) {
            for (int i = num; i <= ret; i++) {

 b[i].setBackgroundColor(Color.WHITE);
                b[i].setText("");
            }
            num -= 6;// cutting....
            ret = zero2(num);
        }

    }


    public int zero2(int y){
        int ret=0;
        if(y>=1 && y<=6)  { ret=6; }
        else if(y>=7 && y<=12) { ret=12; }
        else if(y>=13 && y<=18){ ret=18; }
        else  if(y>=19 && y<=24){ ret=24; }
        return ret;
    }




}
