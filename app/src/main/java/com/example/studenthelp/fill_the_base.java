package com.example.studenthelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fill_the_base extends AppCompatActivity {




    private Spinner spinner1, spinner2,spinner3;
    private Button btnSubmit;
    EditText className;
    EditText sirsName;
    EditText time;
    EditText room;
    String dayStored_in_mem;

    DatabaseReference reff;

    Map<String,Integer> Day2Num= new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_the_base);

        Day2Num.put("Aday",1);
        Day2Num.put("Bday",2);
        Day2Num.put("Cday",3);
        Day2Num.put("Dday",4);
        Day2Num.put("Eday",5);





        reff= FirebaseDatabase.getInstance().getReference();


        className=findViewById(R.id.editText2);
        sirsName=findViewById(R.id.editText3);
        room=findViewById(R.id.editText4);
        time=findViewById(R.id.editText3);


        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();












    }

    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
      //  spinner3 = (Spinner) findViewById(R.id.spinner3);
        List<String> list = new ArrayList<String>();
        list.add("secA");
        list.add("secB");
        list.add("secC");
        list.add("data_test");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);




        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dayStored_in_mem=read(String.valueOf( spinner3.getSelectedItem()));// reading the
                /// txt file............

                //System.out.println(dayStored_in_mem);

                reff.child(spinner1.getSelectedItem().toString()).child("even")
                    .child(spinner2.getSelectedItem().toString().toLowerCase())
                .child(String.valueOf(Day2Num.get(spinner3.getSelectedItem())))
                .setValue(  dayStored_in_mem+"--man???")
                ;


                Toast.makeText(fill_the_base.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())+
                                "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem())
                        +String.valueOf(Day2Num.get(spinner3.getSelectedItem()))
                        ,
                        Toast.LENGTH_SHORT).show();
            }

        });
    }


        private String read(String name) {
            FileInputStream fis = null;
            String FILE_NAME = name+".txt";
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
