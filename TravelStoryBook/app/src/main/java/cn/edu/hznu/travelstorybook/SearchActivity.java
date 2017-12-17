package cn.edu.hznu.travelstorybook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<Story> storyBriefList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initStoryBrief();
        StoryBriefAdapter adapter = new StoryBriefAdapter(SearchActivity.this,R.layout.story_brief_item,storyBriefList);
        ListView listView = (ListView)findViewById(R.id.search_list_view);
        listView.setAdapter(adapter);
    }
    private void initStoryBrief(){
        for (int i=0;i<5;i++){
            Story story = new Story(i,R.drawable.lunbo_1,
                    "年末 | 12月活动推荐",
                    "2017年的最后一个月，哪些演出值得一看，哪些展览不容错过？",
                    "2017-12-12","游记编辑部",10+i);
            Log.d("MainActivity",story.getStory_author());
            storyBriefList.add(story);
        }
    }
}
