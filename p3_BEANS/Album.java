package p3;

public class Album {
    private String name;
    private String country;
    private String performer;
    private String ISBN;
    private String company;
    private String description;
    private String aid;
    private String format;
    private String year;

    Album(String name, String country, String performer, String ISBN, String aid, String format, String year){
        this.name=name;
        this.country=country;
        this.performer=performer;
        this.ISBN=ISBN;
        this.aid=aid;
        this.format=format;
        this.year=year;
    }

    Album(String name, String country, String performer, String ISBN, String company, String aid, String format, String year){
        this.name=name;
        this.country=country;
        this.performer=performer;
        this.ISBN=ISBN;
        this.company=company;
        this.aid=aid;
        this.format=format;
        this.year=year;
    }


    public String getName(){
        return this.name;
    }
    public String getCountry(){
        return this.country;
    }
    public String getISBN(){
        return this.ISBN;
    }
    public String getPerformer(){
        return this.performer;
    }
    public String getCompany(){
        return this.company;
    }
    public String getDescription(){
        return this.description;
    }
    public String getAid(){
        return this.aid;
    }
    public String getFormat(){
        return this.format;
    }
    public String getYear(){
        return this.year;
    }
    public void setDescription(String review){
        this.description=review;
    }
}
