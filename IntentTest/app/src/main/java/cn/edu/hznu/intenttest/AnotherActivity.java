package cn.edu.hznu.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        //显示传递过来的字符串
        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        TextView tv = (TextView)findViewById(R.id.tv_message);
        tv.setText(msg);
    }
}
