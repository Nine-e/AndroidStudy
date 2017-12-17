package cn.edu.hznu.storybooktest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by del on 2017/12/13.
 */

public class StoryBriefAdapter extends ArrayAdapter<Story> {
    private int resourceId;
    public StoryBriefAdapter(Context context, int textViewResourceId, List<Story> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Story story = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView storyImage = (ImageView) view.findViewById(R.id.story_brief_image);
        TextView storyTitle = (TextView)view.findViewById(R.id.story_brief_title);
        TextView storyContent = (TextView)view.findViewById(R.id.story_brief_content);
        TextView storyDate = (TextView)view.findViewById(R.id.story_brief_date);
        TextView storyAuthor = (TextView)view.findViewById(R.id.story_brief_author);

        storyImage.setImageResource(story.getStory_img_id());
        storyTitle.setText(story.getStory_title());
        storyContent.setText(story.getStory_content());
        storyDate.setText(story.getStory_date());
        storyAuthor.setText(story.getStory_author());

        return view;
    }
}
