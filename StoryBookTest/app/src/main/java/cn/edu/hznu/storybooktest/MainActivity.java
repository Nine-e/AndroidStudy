/*
package cn.edu.hznu.storybooktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
*/
package cn.edu.hznu.storybooktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private List<Story> storyBriefList = new ArrayList<>();
    private String[] data = {"Apple","bananana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,data);


        //initStoryBrief();
        //StoryBriefAdapter adapter = new StoryBriefAdapter(MainActivity.this,R.layout.story_brief_item,storyBriefList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
   /* private void initStoryBrief(){
        for (int i=0;i<5;i++){
            Story story = new Story(i,R.drawable.story,
                    "年末 | 12月活动推荐",
                    "2017年的最后一个月，哪些演出值得一看，哪些展览不容错过？",
                    "2017-12-12","游记编辑部",10+i);
            Log.d("MainActivity",story.getStory_author());
            storyBriefList.add(story);
        }
    }*/
}
