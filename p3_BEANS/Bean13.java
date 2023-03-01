package p3;

import java.util.ArrayList;

public class Bean13 {
    
    private ArrayList<Song> songs; //the songs of an album released in a country
    private String pcountry;//the country
    private String paid;//the id of the album

    public Bean13(){ 

    }

    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void setSongs(ArrayList<Song> songs){
        this.songs = songs;
    }

    public String getPcountry(){
        return pcountry;
    }

    public void setPcountry(String pcountry){
        this.pcountry = pcountry;
    }

    public String getPaid(){
        return paid;
    }

    public void setPaid(String paid){
        this.paid = paid;
    }
}
