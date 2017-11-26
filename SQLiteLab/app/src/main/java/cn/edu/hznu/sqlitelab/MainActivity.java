package cn.edu.hznu.sqlitelab;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private List<PhoneNumber> phoneNumbersList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //create SQLite
        dbHelper = new MyDatabaseHelper(this,"PhoneBook.db",null,1);
        dbHelper.getReadableDatabase();

        //ListView
        initPhoneNumber();
        PhoneNumberAdapter adapter = new PhoneNumberAdapter(MainActivity.this,
                R.layout.phone_number_item,phoneNumbersList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);


        //添加列表的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                PhoneNumber contact = phoneNumbersList.get(position);
                String mobile = "tel:" + contact.getNumber();
                //点击列表的电话号码，拨打该号码
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(mobile));
                startActivity(intent);
            }
        });

    }

    //创建菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    //定义菜单响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                //使用显示Intent跳转到AddActivity
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                break;
            case R.id.delete_item:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                for(PhoneNumber phoneNumber:phoneNumbersList){
                    if(phoneNumber.getIsCheck()){
                        db.delete("contact","name=?",new String[]{phoneNumber.getName()});
                    }
                }
                phoneNumbersList.clear();
                initPhoneNumber();
                PhoneNumberAdapter adapter = new PhoneNumberAdapter(MainActivity.this,
                        R.layout.phone_number_item,phoneNumbersList);
                ListView listView = (ListView)findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                break;
            default:
        }
        return true;
    }


    //初始化数据（select）
    private void initPhoneNumber(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //查询contact表中所有数据
        Cursor cursor = db.query("contact",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String phone = cursor.getString(cursor.getColumnIndex("mobile"));
                PhoneNumber number = new PhoneNumber(name,phone);
                phoneNumbersList.add(number);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }
}
