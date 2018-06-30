package com.viktoria.temp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity{
    final int DB_VERSION = 15;
    final String log = "_____MY_LOG____";
    String build, teacher;

    private ArrayList<Quote> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SecondActivity.DBHelper dbHelper = new SecondActivity.DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Bundle extras = getIntent().getExtras();
        build = extras.getString("build");
        teacher = extras.getString("teacher");
        Cursor cursor;

        //String sqlQuery = "SELECT * FROM quotes, school_and_build WHERE quotes.school = ? AND quotes.school = school_and_build.school;";

        if(teacher != null) {
            if (teacher.equals("Кленин Александр Сергеевич")) {
                Log.d(log, "___SELECT FROM KLENIN___");
                String sqlQuery = "SELECT * FROM quotes WHERE last_name = ? AND first_name = ? AND middle_name = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"Кленин", "Александр", "Сергеевич"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
        }
        else {
            if (build.equals("B")) {
                Log.d(log, "___SELECT FROM B___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШРМИ"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("C")) {
                Log.d(log, "___SELECT FROM C___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ИШ"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("D")) {
                Log.d(log, "___SELECT FROM D___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ? OR school = ? OR school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШЕН", "ШРМИ", "ЮШ"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("E")) {
                Log.d(log, "___SELECT FROM E___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ? OR school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШИГН", "ШП"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("F")) {
                Log.d(log, "___SELECT FROM F___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ? OR school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШИГН", "ШИКС"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("G")) {
                Log.d(log, "___SELECT FROM G___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШЭМ"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("S")) {
                Log.d(log, "___SELECT FROM S___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШИКС"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("M")) {
                Log.d(log, "___SELECT FROM M___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШБМ"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
            if (build.equals("L")) {
                Log.d(log, "___SELECT FROM L___");
                String sqlQuery = "SELECT * FROM quotes WHERE school = ?;";
                cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[]{"ШЕН"});
                logCursor(cursor);
                cursor.close();
                Log.d(log, "___ ___");
            }
        }
    }

    void logCursor(Cursor cursor) {
        ListView listView = (ListView) findViewById(R.id.listView);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : cursor.getColumnNames()) {
                        str = str.concat(cn = cursor.getString(cursor.getColumnIndex(cn)) + "\n");
                    }
                    Log.d("_MY_LOG_ ", str);
                    if (!((((cursor.getString(0) == null)&&(cursor.getString(1) == null)&&(cursor.getString(2) == null)&&((cursor.getString(3) == null)||(cursor.getString(4)==null)||(cursor.getString(5)==null))))))
                        mList.add(new Quote(cursor.getString( 0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(5),cursor.getString(4)));
                } while (cursor.moveToNext());
            }
            QuoteAdapter adapter = new QuoteAdapter(this,
                    R.layout.list_item, mList);
            listView.setAdapter(adapter);
        } else Log.d("_MY_LOG_ ", "NULL");
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "Teachers_Quotes", null, DB_VERSION);
        }

        @Override
        //Метод вызывается при первом создании базы данных
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            ContentValues contentValues = new ContentValues();

            sqLiteDatabase.execSQL(
                    "CREATE TABLE quotes " +
                            "(" +
                            "quote text not null, " +
                            "last_name text, " +
                            "first_name text , " +
                            "middle_name text, " +
                            "subject text, " +
                            "school text" +
                            ");"
            );

            /*Quotes quotes = new Quotes();
            try {
                quotes.getMore();
            } catch (Exception e) {
                e.printStackTrace();
            }*/

            Quotes quotes = new Quotes();
            Thread t = new Thread(quotes::getWall);

            try {
                t.start();
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int i = 0; i < quotes.list.size(); i++) {
                contentValues.clear();

                contentValues.put("quote", quotes.list.get(i).quote);
                if (quotes.list.get(i).surname != null)
                    contentValues.put("last_name", quotes.list.get(i).surname);
                if (quotes.list.get(i).name != null)
                    contentValues.put("first_name", quotes.list.get(i).name);
                if (quotes.list.get(i).patronymic != null)
                    contentValues.put("middle_name", quotes.list.get(i).patronymic);
                //contentValues.put("full_name", quotes.list.get(i).name);
                if (quotes.list.get(i).subject != null)
                    contentValues.put("subject", quotes.list.get(i).subject);
                if (quotes.list.get(i).school != null)
                    contentValues.put("school", quotes.list.get(i).school);

                sqLiteDatabase.insert("quotes", null, contentValues);
            }

            sqLiteDatabase.execSQL(
                    "CREATE TABLE school_and_build " +
                            "(" +
                            "school text not null, " +
                            "build text not null" +
                            ");"
            );


            contentValues.clear();

            contentValues.put("school", "ШЕН");
            contentValues.put("build", "D");

            contentValues.put("school", "ШЕН");
            contentValues.put("build", "L");

            contentValues.put("school", "ВИ-ШРМИ");
            contentValues.put("build", "D");

            contentValues.put("school", "ВИ-ШРМИ");
            contentValues.put("build", "B");

            contentValues.put("school", "ШРМИ");
            contentValues.put("build", "D");

            contentValues.put("school", "ШРМИ");
            contentValues.put("build", "B");

            contentValues.put("school", "ЮШ");
            contentValues.put("build", "D");

            contentValues.put("school", "ШБМ");
            contentValues.put("build", "M");

            contentValues.put("school", "ИШ");
            contentValues.put("build", "C");

            contentValues.put("school", "ИШ");
            contentValues.put("build", "F");

            contentValues.put("school", "ШП");
            contentValues.put("build", "E");

            contentValues.put("school", "ШЭМ");
            contentValues.put("build", "G");

            contentValues.put("school", "ШГН");
            contentValues.put("build", "F");

            contentValues.put("school", "ШИКС");
            contentValues.put("build", "F");

            contentValues.put("school", "ШИГН");
            contentValues.put("build", "F");

            sqLiteDatabase.insert("school_and_build", null, contentValues);
        }

        @Override
        //Вызывается при изменении базы данных (если указанный в приложении номер версии БД выше, чем в самой БД)
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.d(log, "__________________UPDT_________________");
            if (newVersion > oldVersion) {
                sqLiteDatabase.execSQL("DROP table if exists quotes");
                sqLiteDatabase.execSQL("DROP table if exists school_and_build");

                onCreate(sqLiteDatabase);
            }
        }
    }
}
