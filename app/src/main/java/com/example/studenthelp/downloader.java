package com.example.studenthelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class downloader extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int percode = 400;
    Button dwon;
    Button dwon2;

    String url = "https://drive.google.com/open?id=1lWggB_p-5ClIx3a4xC51gqKR-smvc_LW";
    String[] users = { "1-1 sem", "1-2 sem", "2-1 sem",

            "2-2 sem.", "3-1 sem",  "3-2 sem","4-1 sem","4-2 sem"};

    String[] series = { "19", "18", "17",

            "16.", "15"};


int extrapos;

    @Override
   protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);
        Spinner spin = findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, users);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


spin.setAdapter(null);
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "three"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter2);

dropdown.setAdapter(null);

        dwon = findViewById(R.id.download);

        dwon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),
                        "OPENING THE CHOTHAS FOR"+users[extrapos] +" SEMESTER.....PLEASE SELECT OPTION BELOW"
                        ,Toast.LENGTH_SHORT).show();
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });



    }





    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {

 extrapos=position;
      if(users[position].toString().equals("1-1 sem")){
          url="https://drive.google.com/open?id=1KHlha64BUdA1P-Dih-_a-ViCu3vci2XS";
      }
        if(users[position].toString().equals("1-2 sem")){
            url="https://drive.google.com/open?id=1fCb21VQwkC2Q7nGHUuOD6wq6M-YOXIYR";
        }
        if(users[position].toString().equals("2-1 sem")){
            url="https://drive.google.com/open?id=1q9f73rCNWzpkXGqY23OD_3D9zyDQf7Ia";
        }

        if(users[position].toString().equals("2-2 sem")){
            url="https://drive.google.com/open?id=1dBKn30tWywSmxP_ry9eizZ8XDVcN0t8-";
        }


        if(users[position].toString().equals("3-1 sem")){
            url="https://drive.google.com/open?id=1dBKn30tWywSmxP_ry9eizZ8XDVcN0t8-";
        }

        if(users[position].toString().equals("3-1 sem")){
            url="https://drive.google.com/open?id=1dBKn30tWywSmxP_ry9eizZ8XDVcN0t8-";
        }

        if(users[position].toString().equals("4-1 sem")){
            url="https://drive.google.com/open?id=1dBKn30tWywSmxP_ry9eizZ8XDVcN0t8-";
        }

        if(users[position].toString().equals("4-2 sem")){
            url="https://drive.google.com/open?id=1dBKn30tWywSmxP_ry9eizZ8XDVcN0t8-";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
