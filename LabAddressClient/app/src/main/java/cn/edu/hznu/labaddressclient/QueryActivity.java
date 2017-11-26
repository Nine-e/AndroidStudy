package cn.edu.hznu.labaddressclient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends AppCompatActivity {
    private RadioButton radioName;
    private RadioButton radioMobile;
    private EditText keyText;
    private Button queryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        radioName = (RadioButton)findViewById(R.id.radioName);
        radioMobile = (RadioButton)findViewById(R.id.radioMobile);
        keyText = (EditText)findViewById(R.id.key_text);
        queryBtn = (Button)findViewById(R.id.query_btn);

        queryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String key = keyText.getText().toString();
                Uri uri = Uri.parse("content://cn.edu.hznu.sqlitelab.provider/contact");
                Cursor cursor ;
                //文本输入框为空时
                if(TextUtils.isEmpty(keyText.getText())){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(QueryActivity.this);
                    dialog.setTitle("请注意");
                    dialog.setMessage("关键字不能为空！");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }else {
                    //不为空时
                    cursor = null;
                    if (radioName.isChecked()) {
                        cursor = getContentResolver().query(uri, null, "name like ?", new String[]{"%" + key + "%"}, null);
                    } else if(radioMobile.isChecked()){
                        cursor = getContentResolver().query(uri, null, "mobile like ?", new String[]{"%" + key + "%"}, null);
                    }
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                            PhoneNumber pn = new PhoneNumber(name, mobile);
                            ResultActivity.phoneNumberList.add(pn);
                        }
                        cursor.close();
                    }
                    //使用显示Intent跳转到QueryActivity
                    Intent intent = new Intent(QueryActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        keyText.setText("");
        ResultActivity.phoneNumberList = new ArrayList<>();
    }
}
