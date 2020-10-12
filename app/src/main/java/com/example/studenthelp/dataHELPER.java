package com.example.studenthelp;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dataHELPER extends SQLiteOpenHelper {

    public static final String datNAME="nameer.db";
    public static final String tableName ="tablet";

    public static final String col1="ID";


    public static final String col2= "date";



    public dataHELPER(Context context) {
        super(context, datNAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table " + tableName +
                "(ID INTEGER PRIMARY KEY,name INTEGER )"
        );



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);

        onCreate(sqLiteDatabase);



    }


    public  boolean inserdata(int name)
    {

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(col2,name);
        //cv.put(col3,appoint);
        long result= db.insert(tableName,null,cv);
        if(result==-1){
            return  false;
        }
        else {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ tableName,null);
        return  res;
    }

    public void updater(String id ,String name,String apps)
    {


        SQLiteDatabase db= this.getWritableDatabase();


        ContentValues cv=new ContentValues();

        cv.put(col1,id);
        cv.put(col2,name);

        db.update(tableName,cv,"ID = ?",new String[] {id});



    }





}
