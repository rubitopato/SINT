package p3;

import java.util.ArrayList;

public class Bean12 {
    
    private ArrayList<Album> albums; //the albums released in a country
    private String pcountry;//the country of the albums

    public Bean12(){ 

    }

    public ArrayList<Album> getAlbums(){
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums){
        this.albums = albums;
    }

    public String getPcountry(){
        return pcountry;
    }

    public void setPcountry(String pcountry){
        this.pcountry = pcountry;
    }

}
