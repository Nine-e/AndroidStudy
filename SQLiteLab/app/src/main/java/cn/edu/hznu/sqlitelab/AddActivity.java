package cn.edu.hznu.sqlitelab;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText name_text = (EditText)findViewById(R.id.name_text);
        final EditText phone_text = (EditText)findViewById(R.id.phone_text);

        dbHelper = new MyDatabaseHelper(this,"PhoneBook.db",null,1);
        //dbHelper.getWritableDatabase();

        //add SQLite
        Button addbtn = (Button)findViewById(R.id.add_btn);
        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //添加数据
                values.put("name", name_text.getText().toString());
                values.put("mobile", phone_text.getText().toString());
                db.insert("contact", null, values);
                values.clear();

                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
