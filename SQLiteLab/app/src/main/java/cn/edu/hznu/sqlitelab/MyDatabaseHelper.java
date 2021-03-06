package cn.edu.hznu.sqlitelab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by del on 2017/11/17.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper{

    public static final String CREATE_PHONE =  "create table contact ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "mobile text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PHONE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
