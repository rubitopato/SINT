package p4;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DataModel {

     boolean error=false;

     ArrayList<Album> albumArray = new ArrayList<Album>();
     ArrayList<Song> songArray = new ArrayList<Song>(); 
     ArrayList<String> countries = new ArrayList<String>();
     ArrayList<String> yearsArray = new ArrayList<String>();//arraylist con los años de los albumes en el mapa que se utilizan como llaves para acceder a los documentos del mapa
     ArrayList<String> errorFiles = new ArrayList<String>();//arraylist con las URL de los documentos que no se encuentran entre 1980-2021
     ArrayList<String> fatalFiles = new ArrayList<String>();//artaylist con las URL de los documentos que a la hora de leerlos dieron alguna excepcion de algun tipo
     ArrayList<String> tagsErrorsXML = new ArrayList<String>();//arraylist con las MuML tags de los documentos que no se encuentran entre 1980-2021
     ArrayList<String> tagsFatalsXML = new ArrayList<String>();//artaylist con las MuML tags de los documentos que a la hora de leerlos dieron alguna excepcion de algun tipo
	DataModel(){	}

    public void readDocument(){//este metodo es llamado en el init del servlet y procesa todos los documentos xml y guarda cada valor en su respectivo lugar
     ArrayList<String> mumlTags = new ArrayList<String>();
	String enlaceTop = "http://alberto.gil.webs.uvigo.es/SINT/22-23/";//parte general del enlace para ir concatenandolo con las posibles variables y asi ir leyendo todos los documentos xml
     String enlaceTotal = "muml2001.xml"; //valor inicial
     String enlaceFinal=null;
     try{
     //creamos el sax parser
     SAXParserFactory factory = SAXParserFactory.newInstance();
     SAXParser saxParser = factory.newSAXParser();

     //creamos un handler y especificamos como debe leer los xmls
     DefaultHandler handler = new DefaultHandler(){
    
          String name;
          String country;
          String performer;
          String ISBN;
          String company;
          String aid;
          String format;
          String muml;
          String review;
          int yearInt;
          String yearString;
          Album auxAlbum;
          Song auxSong;
           
          boolean namebol;
          boolean countrybol;
          boolean performerbol;
          boolean ISBNbol;
          boolean companybol;
          boolean yearbol;
          boolean mumlbol;
          boolean reviewbol=true;//este boolean sirve para saber cuando leer una review. Como la review no tiene etiqueta, al leer una review el parser va directo al metodo characters y este boolean sera true
          //si entra en el metodo startElement, significa que va a leer otra cosa, asi que este boolean sera false
           
          String title;
          String duration;
          String composer;
          String sid;
          String lang;
          String auxGenre;
          ArrayList<String> genres = new ArrayList<String>();
           
          boolean titlebol;
          boolean durationbol;
          boolean composerbol;
          boolean genrebol;
           
          //el parser entra en este metodo cuando lee el comienzo de un elemento
          public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                   
                   if(qName.equals("Album")){
                       aid = attributes.getValue("aid");
                       format = attributes.getValue("format");
                       reviewbol=true;
                   }
                   else if(qName.equals("Year")){
                       yearbol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Name")){
                       namebol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Country")){
                       countrybol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Singer")){
                       performerbol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Group")){
                       performerbol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Company")){
                       companybol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("ISBN")){
                       ISBNbol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("MuML")){
                       mumlbol=true;
                       reviewbol=false;
                   }
           ////////////////////////////////////////////////////hasta aqui de albums
                   else if(qName.equals("Title")){
                       titlebol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Duration")){
                       durationbol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Genre")){
                       genrebol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Composer")){
                       composerbol=true;
                       reviewbol=false;
                   }
                   else if(qName.equals("Song")){
                       sid = attributes.getValue("sid");
                       lang = attributes.getValue("lang");
                       reviewbol=true;
                   }
               }
               
               //entra en este metodo cuando lee texto y dependiendo del boolean activado en cada momento, se procesa la informacion de una forma determinada
               @Override
               public void characters(char[] ch, int start, int length) throws SAXException {
           
                if(reviewbol==false){
                    if(yearbol){
                        yearInt = Integer.parseInt(new String(ch, start, length).trim());
 
                        if(yearInt<1980 || yearInt > 2021){
                          error=true;//si error es true, aunque se lea la info no se guardara
                        }
 
                        yearString = new String(ch, start, length);
                        yearbol=false;
                    }
                    if(namebol){
                        name = new String(ch, start, length);
                        namebol=false;
                    }
                    if(countrybol){
                        country=new String(ch, start, length);
                        if(error==false){
                          if(countries.contains(country)==false){
                               countries.add(country);
                          }
                        }
 
                        countrybol=false;
                    }
                    if(performerbol){
                        performer=new String(ch, start, length);
                        performerbol=false;
                    }
                    if(companybol){
                        company=new String(ch, start, length);
                        companybol=false;
                    }
                    if(ISBNbol){
                        ISBN=new String(ch, start, length);
                        ISBNbol=false;
                    }
                    if(mumlbol){
                        muml=new String(ch, start, length);
                        if(error==false){
                          mumlTags.add(muml);
                        }
 
                        mumlbol=false;
                    }
            
                    if(titlebol){
                        title = new String(ch, start, length);
                        titlebol=false;
                    }
                    if(durationbol){
                        duration = new String(ch, start, length);
                        durationbol=false;
                    }
                    if(genrebol){
                        auxGenre = new String(ch, start, length);
                        genres.add(auxGenre);
                        genrebol=false;
                    }
                    if(composerbol){
                       composer = new String(ch, start, length);
                       composerbol=false;
                    }
                }
                else if(reviewbol==true){
                    String aux = new String(ch,start,length);
                    if(aux.trim().isEmpty()==false){
                        review=aux;
                    }
                    reviewbol=true;
                }
                   

               }
           
               //entra en este metodo cuando detecta el final de un elemento
               @Override
               public void endElement(String uri, String localName, String qName) throws SAXException {
           
                   if(qName.equals("Album")){
                    if(error==false){
                        //creamos un album con los elementos que hemos leido en caso de que el year este en el rango deseado
                         if(company.equals("") || company == null){
                              auxAlbum = new Album(name, country, performer, ISBN, aid, format, yearString);
                              auxAlbum.setSongs(songArray);
                              songArray = new ArrayList<Song>();
                          }
                          else{
                              auxAlbum = new Album(name, country, performer, ISBN, company, aid, format, yearString);
                              auxAlbum.setSongs(songArray);
                              songArray = new ArrayList<Song>();
                          }
                          //añadimos ese album a esta variable para poder pedirla con su getter
                          auxAlbum.setDescription(review);
                          albumArray.add(auxAlbum);

                    }
                   }
                   if(qName.equals("Song")){
                     if(error==false){
                        //creamos una song con los elementos que hemos leido en caso de que el year este en el rango deseado
                         if(composer.equals("") || composer == null){
                              auxSong = new Song(title,duration,genres,sid,lang);
                              genres = new ArrayList<String>();
                          }
                          else{
                              auxSong = new Song(title,duration,genres,composer,sid,lang);
                              genres = new ArrayList<String>();
                          }
                          //añadimos esa song a esta variable para poder pedirla con su getter
                          songArray.add(auxSong);
                     }
                   }
                   reviewbol=true;
               }
           
          };

     boolean finish=false;


     do{
          enlaceFinal = enlaceTop.concat(enlaceTotal);//concateno las dos partes de la URL completa de un documento

          try{
      
               saxParser.parse(enlaceFinal, handler);
               if(error==true){//si la varible error es true es que el year del documento esta fuera del rango deseado
                    errorFiles.add(enlaceFinal);//aqui añado el URL total para el html de los errors
                    tagsErrorsXML.add(enlaceTotal);//aqui solo añado la parte muml200... para el xml de los errors 
                    error=false;
               }

                
           //aqui actualizamos el doc para ir leyendo nuevos, sino se puede leer saltara una excepcion
          }catch(SAXException e){
               fatalFiles.add(enlaceFinal);//aqui añado el total para el html a los fatal errors
               tagsFatalsXML.add(enlaceTotal);//aqui solo añado la parte muml200... para el xml a los fatal errors
               enlaceTotal=mumlTags.get(0);
               mumlTags.remove(0);
               continue;
          }



          
          if(mumlTags.size()>0){
               enlaceTotal = mumlTags.get(0); //cogemos el primer elemento de la arrayList
               mumlTags.remove(0); //eliminamos el q ya cogimos
          }
          else{
               finish=true;//terminamos de leer documentos
          }
          /////////////////////////////
          
     }while(finish==false);

     }catch(Exception e){
        e.printStackTrace();
     }
     }

     public ArrayList<String> getQ1ErrorFiles(){//devuelve la arrayList de errores para representarla en el FrontEnd   
          return errorFiles;
     }
     public ArrayList<String> getQ1ErrorFilesXML(){   //devuelve la arrayList de errores para representarla en el FrontEnd (modo auto)
          return tagsErrorsXML;
     }
     public ArrayList<String> getQ1FatalFiles(){   //devuelve la arrayList de errores fatales para representarla en el FrontEnd 
          return fatalFiles;
     }
     public ArrayList<String> getQ1FatalFilesXML(){//devuelve la arrayList de errores fatales para representarla en el FrontEnd  (modo auto)
          return tagsFatalsXML;
     }
     
     public ArrayList<String> getQ1Countries(){//este metodo devuelve los paises donde alguno de los albumes leidos fueron publicados
		Collections.sort(countries, Collections.reverseOrder());//ordena los paises al reves
          return countries;
     }


     public ArrayList<Album> getQ1Albums(String country){//este metodo devuelve todos los albumes publicados en el pais que recibe como parametro pcountry
          ArrayList<Album> albumes = new ArrayList<Album>();
          Album middleAlbum; 
          for(int i=0; i<albumArray.size();i++){
               middleAlbum=albumArray.get(i);
               if(middleAlbum.getCountry().equals(country)){
                    albumes.add(middleAlbum);
               }
          }
          albumes.sort(new AlbumComparator());//ordenamos los albumes utilizando un comparator segun los criterios especificos
          return albumes;
     }



     public ArrayList<Song> getQ1Songs(String country, String paid){//este metodo devuelve una lista con ls canciones cuyo alguno de sus generos sea Pop de un album especifico de un pais especifico
          ArrayList<Song> songs = new ArrayList<Song>();
          Song middleSong;
          ArrayList<Song> middleSongArray = new ArrayList<Song>();
          Album middleAlbum; 
          for(int i=0; i<albumArray.size();i++){
               middleAlbum=albumArray.get(i);
               if(middleAlbum.getCountry().equals(country)){
                    if(middleAlbum.getAid().equals(paid)){
                        middleSongArray = middleAlbum.getSongs();
                        for(int a=0;a<middleSongArray.size();a++){
                            middleSong = middleSongArray.get(a);
                            if(middleSong.getGenres().contains("Pop")){//añadimos solo las canciones que tenga el genero Pop
                                songs.add(middleSong);
                            }
                        }
                    }
               }
          }
          songs.sort(new SongComparator());//ordena las canciones por menos generos y si son iguales por orden alfabetico
          return songs;
     }

     
     class AlbumComparator implements Comparator<Album>{//con esta clase comparator podemos ordenar los albumes segun el criterio especificado

          public int compare(Album o1, Album o2){
            if(Integer.parseInt(o1.getYear())<Integer.parseInt(o2.getYear())){
                return -1;//esto es q va antes
            }
            
            else if(Integer.parseInt(o1.getYear())>Integer.parseInt(o2.getYear())){
                return 1;
            }
        
            else{
                return o1.getName().compareTo(o2.getName());
            }
          }
     }

     class SongComparator implements Comparator<Song>{//con esta clase comparator podemos ordenar las songs segun el criterio especificado

          public int compare(Song o1, Song o2){
            if(o1.getGenres().size()<o2.getGenres().size()){
                return -1;
            }
            
            else if(o1.getGenres().size()>o2.getGenres().size()){
                return 1;
            }
        
            else{
                return o1.getTitle().compareTo(o2.getTitle());
            }
          }
     }
}//class


