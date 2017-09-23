package cn.edu.hznu.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击btn1使用显式Intent向下一个活动传递数据
            //获取按钮
        Button button1 = (Button)findViewById(R.id.btn1);
            //为按钮注册监听事件
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String data = "这是利用显式 Intent 启动的活动.";
                Intent intent = new Intent(MainActivity.this,AnotherActivity.class);
                intent.putExtra("msg",data);
                startActivity(intent);
            }
        });


        //点击btn2使用隐式Intent向下一个活动传递数据
            //获取按钮
        Button button2 = (Button)findViewById(R.id.btn2);
            //为按钮注册监听事件
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String data = "这是利用隐式 Intent 启动的活动。";
                Intent intent = new Intent("cn.edu.hznu.intenttest.CUSTOMIZED_ACTION");
                intent.addCategory("cn.edu.hznu.intenttest.NEW_CATEGORY");
                intent.putExtra("msg",data);
                startActivity(intent);
            }
        });

        //点击btn_call启动拨号程序，拨打10086
            //获取按钮
        Button button3 = (Button)findViewById(R.id.btn_call);
            //为按钮注册监听事件
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });


    }


}

