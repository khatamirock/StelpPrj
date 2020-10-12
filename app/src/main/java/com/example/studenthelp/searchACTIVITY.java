package com.example.studenthelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class searchACTIVITY extends AppCompatActivity {

    TextView tv;
    Button find;
    EditText search;

    String store_Search;
    String data;
    String found;
    String news;
    String found2token;
    char[] tokens = new char[14];
String[] day={"A","B","C","D","E"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);


        tv=findViewById(R.id.result);
        find=findViewById(R.id.find);
        search =findViewById(R.id.search);

tv.setMovementMethod(new ScrollingMovementMethod());



        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

found="";
                store_Search = search.getText().toString();
int position;
                for (int i = 0; i < 5; i++) {
                    data = read(day[i]+"day.txt").toLowerCase();
                    if (data.contains(store_Search.toLowerCase())) {


                        position = data.indexOf(store_Search.toLowerCase());
                        int pos2 = data.indexOf(store_Search, position + store_Search.length());
                        news = "";
                        news = data.replaceAll(" ", "");

                        if (pos2 > 0) {

                            news = data.substring(pos2, data.length());
                            System.out.println("news" + news);


                        }

                        String[] separated2 = news.split(" ");
                        System.out.println(separated2[0] + "sep)");
                        // System.out.println(data.indexOf(store_Search.toLowerCase()));

                        try {
                            found2token = data.substring(position, position + 30);
                        } catch (StringIndexOutOfBoundsException e) {

                            found += store_Search + " -- " + day[i] + "day\n\n";


                        }


                        String[] separated = found2token.split(" ");
                        //   data.getChars(position+store_Search.length(),position+14,tokens,0);

                        if (pos2 > 0) {

                            try {
                                found += "  " + separated[0] + " " + separated[1] + " " + separated[2] + " " + separated[3] + " " + separated[4] + "\n" +
                                        separated2[0] + " " + separated2[1] + " " + separated2[2] + " " + separated2[3
                                        ] + day[i] + " day\n\n";
                            }


                        catch(ArrayIndexOutOfBoundsException e){

          found += "  " + separated[0] + " " + separated[1] + " " + separated[2] + " " + separated[3] + " "  + "\n" +
         separated2[0] + " " + separated2[1] + " " + separated2[2] + " "  + day[i] + " day\n\n";
                        }

                    }
else found+="  " + separated[0]+ " "+separated[1] +" " +separated[2]+ " "+separated[3]+
                                " -- "+ day[i]+"day\n\n";

found.toUpperCase();
news="";

                    }
                    //    tv.setText(data);
                }

                tv.setText(found);

               // found="";
            }
        });


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
