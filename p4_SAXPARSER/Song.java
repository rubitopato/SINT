package p4;

import java.util.ArrayList;
import java.util.Arrays;

public class Song {
    private String title;
    private String duration;
    private ArrayList<String> genres;
    private String composer;
    private String sid;
    private String lang;

    Song(String title, String duration, ArrayList<String> genres, String sid, String lang){
        this.title=title;
        this.duration=duration;
        this.genres=genres;
        this.sid=sid;
        this.lang=lang;
    }
    Song(String title, String duration, ArrayList<String> genres, String composer, String sid, String lang){
        this.title=title;
        this.duration=duration;
        this.genres=genres;
        this.composer=composer;
        this.sid=sid;
        this.lang=lang;
    }

    public String getTitle(){
        return this.title;
    }
    public String getDuration(){
        return this.duration;
    }
    public String getBien(){//elimina los [] del toString de una ArrayList para printearlos
        String list = Arrays.toString(this.genres.toArray()).replace("[", "").replace("]", "");
        return list;
    }
    public String getComposer(){
        return this.composer;
    }
    public ArrayList<String> getGenres(){
        return this.genres;
    }
    public String getSid(){
        return this.sid;
    }
    public String getLang(){
        return this.lang;
    }
}
