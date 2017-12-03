package cn.edu.hznu.musicplayer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Music> musicList = new ArrayList<>();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Music nowMusic = null;
    private int index = 0;
    private TextView message_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置顶部logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.logo);
        actionBar.setDisplayUseLogoEnabled(true);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE },1);
        }else {
            initMusics();
            initMusicPlayer();//初始化MusicPlayer
        }

        message_text = (TextView)findViewById(R.id.message_text);

        nowMusic = musicList.get(0);
        message_text.setText(nowMusic.getTitle()+"-"+nowMusic.getArtist()
                +"\n"+toTime(nowMusic.getDuration()));


        MusicAdapter adapter = new MusicAdapter(MainActivity.this,R.layout.music_item,musicList);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(nowMusic == null){
                    nowMusic = musicList.get(position);
                    Log.d("MainActivity",nowMusic.getTitle());
                    initMusicPlayer();
                    mediaPlayer.start();

                }else {
                    nowMusic = musicList.get(position);
                    Log.d("MainActivity",nowMusic.getTitle());
                    try{
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(nowMusic.getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                setMessage();
            }
        });

        ImageButton pause = (ImageButton)findViewById(R.id.pause_btn);
        ImageButton start = (ImageButton)findViewById(R.id.start_btn);
        ImageButton prev = (ImageButton)findViewById(R.id.prev_btn);
        ImageButton stop = (ImageButton)findViewById(R.id.stop_btn);
        ImageButton next = (ImageButton)findViewById(R.id.next_btn);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //这里写切换歌曲的代码
                int index = nowMusic.getIndex();
                nowMusic = musicList.get(index);
                Log.d("MainActivity",nowMusic.getTitle());

                try{
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(nowMusic.getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
                setMessage();
            }
        });



    }

    private void initMusics(){
        Cursor cursor =
                getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor.moveToFirst()){
            do{
                // 歌曲 ID：MediaStore.Audio.Media._ID
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                // 歌曲的名称 ：MediaStore.Audio.Media.TITLE
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                // 歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album =cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                // 歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                // 歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                // 歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION，这里的单位为毫秒
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                // 歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                ++index;
                Music music = new Music(id,title,album,artist,url,duration,size,index);
                musicList.add(music);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }

    private void initMusicPlayer(){
        try{
            mediaPlayer.setDataSource(nowMusic.getUrl());
            mediaPlayer.prepare();//让MediaPlyer进入准备状态
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //修改底部控制栏text内容
    private void setMessage(){
        message_text.setText(nowMusic.getTitle()+"-"+nowMusic.getArtist()
                +"\n"+toTime(nowMusic.getDuration()));
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case 1:
               if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                   initMusics();
                   initMusicPlayer();

               }else {
                   Toast.makeText(this,"拒绝权限将无法使用程序",
                           Toast.LENGTH_SHORT).show();
                   finish();
               }
               break;
           default:
       }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_btn:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();//开始播放
                }
                break;
            case R.id.pause_btn:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();//暂停播放
                }
                break;
            case R.id.stop_btn:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();//停止播放
                    initMusicPlayer();
                }
                break;
            case R.id.prev_btn:
                int index1 = nowMusic.getIndex()-2;
                nowMusic = musicList.get(index1);
                Log.d("MainActivity",nowMusic.getTitle());

                try{
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(nowMusic.getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
                setMessage();
                break;
            case R.id.next_btn:
                int index2 = nowMusic.getIndex();
                nowMusic = musicList.get(index2);
                Log.d("MainActivity",nowMusic.getTitle());

                try{
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(nowMusic.getUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
                setMessage();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
