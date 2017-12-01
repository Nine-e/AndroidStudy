package cn.edu.hznu.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Music music = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView musicTitle = (TextView) view.findViewById(R.id.music_title);
        TextView musicArtist = (TextView) view.findViewById(R.id.music_artist);
        TextView musicDuration = (TextView) view.findViewById(R.id.music_duration);
        musicTitle.setText(music.getTitle());
        musicArtist.setText(music.getArtist());
        musicDuration.setText(music.getDuration());
        return view;
    }
}
