package cn.edu.hznu.travelstorybook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Story> storyBriefList = new ArrayList<>();
    //private String[] data = {"Apple","bananana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        setContentView(R.layout.activity_main);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(
         //       MainActivity.this,android.R.layout.simple_list_item_1,data);

        //搜索按钮点击事件
        ImageButton searchBtn = (ImageButton)findViewById(R.id.main_search_button);
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        //‘我的’按钮点击事件
        ImageButton mineBtn = (ImageButton)findViewById(R.id.main_mine_button);
        mineBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserDetailActivity.class);
                startActivity(intent);
            }
        });

        //初始化ListView
        initStoryBrief();
        StoryBriefAdapter adapter = new StoryBriefAdapter(MainActivity.this,R.layout.story_brief_item,storyBriefList);
        ListView listView = (ListView)findViewById(R.id.main_list_view);
        listView.setAdapter(adapter);
        //ListView点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Story story = storyBriefList.get(position);
                Intent intent = new Intent(MainActivity.this,StoryDetailActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initStoryBrief(){
        for (int i=0;i<5;i++){
            Story story = new Story(i,R.drawable.story_img,
                    "年末 | 12月活动推荐",
                    "2017年的最后一个月，哪些演出值得一看，哪些展览不容错过？",
                    "2017-12-12","游记编辑部",10+i);
            Log.d("MainActivity",story.getStory_author());
            storyBriefList.add(story);
        }
    }
}
