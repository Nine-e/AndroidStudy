package cn.edu.hznu.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by del on 2017/11/30.
 */

public class MusicAdapter extends ArrayAdapter<Music> {
    private int resourceId;

    public MusicAdapter(Context context, int textViewResourceId, List<Music> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        Music music = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView musicIndex = (TextView) view.findViewById(R.id.music_index);
        TextView musicTitle = (TextView) view.findViewById(R.id.music_title);
        TextView musicArtist = (TextView) view.findViewById(R.id.music_artist);
        TextView musicDuration = (TextView) view.findViewById(R.id.music_duration);
        musicIndex.setText(music.getIndex()+". ");
        musicTitle.setText(music.getTitle());
        musicArtist.setText(music.getArtist());
        musicDuration.setText(toTime(music.getDuration()));
        return view;
    }
    public String toTime(int duration){
        String time = "";
        String time_minutes = "";
        String time_seconds = "";
        int totalSeconds = duration/1000;
        int seconds = totalSeconds % 60;
        int minutes = totalSeconds / 60;
        if(minutes<10){
            time_minutes = "0"+minutes;
        }else {
            time_minutes = ""+minutes;
        }

        if (seconds<10){
            time_seconds = "0"+seconds;
        }else {
            time_seconds = ""+seconds;
        }
        time = time_minutes + ":" + time_seconds;
        return time;
    }
}