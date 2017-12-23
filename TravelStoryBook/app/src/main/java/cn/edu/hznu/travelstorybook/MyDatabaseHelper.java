package cn.edu.hznu.travelstorybook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by del on 2017/12/21.
 */

public class MyDatabaseHelper  extends SQLiteOpenHelper{
    public static final String CREATE_STORY = "create table Story ("
            +"story_id integer primary key autoincrement, "
            +"story_title text, "
            +"story_content text, "
            +"story_img_id integer, "
            +"story_date text, "
            +"story_author_id integer, "
            +"story_author_name text, "
            +"story_star_count integer)";
    public static final String CREATE_USER = "create table User ("
            +"user_id integer primary key autoincrement, "
            +"user_number text, "
            +"user_password text, "
            +"user_name text, "
            +"user_sex text, "
            +"user_img_id integer)";
    public static final String CREATE_STAR = "create table Star ("
            +"star_id integer primary key autoincrement, "
            +"story_id integer, "
            +"user_id integer)";
    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STORY);
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_STAR);
        Log.d("Database","create success");
        //插入初始数据
        addStory(db);
        addUser(db);
        addStar(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    private void addStory(SQLiteDatabase db){

        ContentValues values = new ContentValues();
        //第一条数据
        values.put("story_title","title-1");
        values.put("story_content","content-1");
        values.put("story_img_id",R.drawable.story_img);
        values.put("story_date","2017-01-01");
        values.put("story_author_id",1);
        values.put("story_author_name","name-1");
        values.put("story_star_count",0);
        db.insert("Story",null,values);
        values.clear();
        //第二条数据
        values.put("story_title","title-2");
        values.put("story_content","content-2");
        values.put("story_img_id",R.drawable.story_img);
        values.put("story_date","2017-01-02");
        values.put("story_author_id",2);
        values.put("story_author_name","name-2");
        values.put("story_star_count",0);
        db.insert("Story",null,values);

        Log.d("Database","add story success");
    }
    private void addUser(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        //第一条数据
        values.put("user_number","13588220684");
        values.put("user_password","123456");
        values.put("user_name","wangyy");
        values.put("user_sex","girl");
        values.put("user_img_id",R.drawable.user_girl);
        db.insert("User",null,values);
        Log.d("Database","add user success");
    }
    private void addStar(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        //第一条数据
        values.put("story_id","1");
        values.put("user_id","1");
        db.insert("Star",null,values);
        values.clear();
        //第二条数据
        /*values.put("story_id","2");
        values.put("user_id","1");
        db.insert("Star",null,values);*/
        Log.d("Database","add star success");
    }

}
