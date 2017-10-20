package cn.edu.hznu.labmycustomui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定义新标题
        String newTitle = "My page";
        //获取控件
        TitleView mTitle = (TitleView)findViewById(R.id.title_bar);
        //调用设置标题方法
        mTitle.setmTitleText(newTitle);
    }
}
