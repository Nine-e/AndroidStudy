package cn.edu.hznu.travelstorybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        //登录 按钮点击事件
        Button loginBtn = (Button)findViewById(R.id.user_detail_button);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //我的收藏 按钮点击事件
        Button starBtn = (Button)findViewById(R.id.user_detail_star);
        starBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this,UserStarActivity.class);
                startActivity(intent);
            }
        });

        //添加游记 按钮点击事件
        Button addBtn = (Button)findViewById(R.id.add_story_button);
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this,AddStoryActivity.class);
                startActivity(intent);
            }
        });

    }
}
