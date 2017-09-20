package cn.edu.hznu.fitstlab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by del on 2017/9/17.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this,"you click Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"you click Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);//加载布局
        //获取按钮
        Button button1 = (Button)findViewById(R.id.button_view_message);
        //为按钮注册监听事件
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*//输出用户信息
                Toast.makeText(MainActivity.this,"You click Button",
                        Toast.LENGTH_SHORT).show();
                //启动MessageActivity
                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent);*/
                /*finish();
                Log.d("MainActivity", "onClick:finish ");*/
                /*Intent intent = new Intent("com.edu.hznu.firstlab.ACTION_START");
                intent.addCategory("com.edu.hznu.firstlab.MY_CATEGORY");
                startActivity(intent);*/
                Intent intent = new Intent (Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });


    }
}
