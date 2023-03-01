package p3;

import java.util.ArrayList;

public class Bean11 {
    
    private ArrayList<String> countries;//countries where an album was released

    public Bean11(){ 
        countries=DataModel.getQ1Countries();//we call the method of the datamodel to get the countries
    }

    public ArrayList<String> getCountries(){
        return countries;
    }

    public void setFatalFiles(ArrayList<String> countries){
        this.countries = countries;
    }
}
