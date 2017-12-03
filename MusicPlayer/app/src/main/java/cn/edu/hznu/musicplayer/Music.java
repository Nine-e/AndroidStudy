package cn.edu.hznu.musicplayer;

/**
 * Created by del on 2017/11/30.
 */

public class Music {
    private int id;
    private String title;
    private String album;
    private String artist;
    private String url;
    private int duration;
    private long size;
    private int index;

    public Music(int id,String title,String album,String artist,String url,int duration,long size,int index){
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.url = url;
        this.duration = duration;
        this.size = size;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getUrl() {
        return url;
    }

    public int getDuration() {
        return duration;
    }

    public long getSize() {
        return size;
    }
    public int getIndex() {return index;}
}
