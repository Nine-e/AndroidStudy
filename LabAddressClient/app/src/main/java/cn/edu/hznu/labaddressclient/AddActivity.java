package cn.edu.hznu.labaddressclient;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText name_text = (EditText)findViewById(R.id.name_text);
        final EditText phone_text = (EditText)findViewById(R.id.phone_text);


        Button addbtn = (Button)findViewById(R.id.add_btn);
        addbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               //为空时
                if(TextUtils.isEmpty(name_text.getText())||TextUtils.isEmpty(phone_text.getText())){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(AddActivity.this);
                    dialog.setTitle("请注意");
                    dialog.setMessage("姓名和号码不能为空！");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }else{

                //不为空时
                //添加数据
                Uri uri = Uri.parse("content://cn.edu.hznu.sqlitelab.provider/contact");
                ContentValues values = new ContentValues();
                values.put("name", name_text.getText().toString());
                values.put("mobile", phone_text.getText().toString());
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);

                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
                }
            }
        });
    }
}
