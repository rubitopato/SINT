package p2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;


public class DataModel {
     XPathFactory xpathFactory = XPathFactory.newInstance();
     XPath xpath = xpathFactory.newXPath();//creamos el xpath que necesiaremos para navegar por los documentos xml
     HashMap<String,Document> mapDocs = new HashMap<String,Document>();// mapa donde guardaremos los documentos que vayamos leyendo y no tengan errores
     ArrayList<String> yearsArray = new ArrayList<String>();//arraylist con los años de los albumes en el mapa que se utilizan como llaves para acceder a los documentos del mapa
     ArrayList<String> errorFiles = new ArrayList<String>();//arraylist con las URL de los documentos que no se encuentran entre 1980-2021
     ArrayList<String> fatalFiles = new ArrayList<String>();//artaylist con las URL de los documentos que a la hora de leerlos dieron alguna excepcion de algun tipo
     ArrayList<String> tagsErrorsXML = new ArrayList<String>();//arraylist con las MuML tags de los documentos que no se encuentran entre 1980-2021
     ArrayList<String> tagsFatalsXML = new ArrayList<String>();//artaylist con las MuML tags de los documentos que a la hora de leerlos dieron alguna excepcion de algun tipo
	DataModel(){	}

    public void readDocument(){//este metodo es llamado en el init del servlet y procesa todos los documentos xml y guarda cada valor en su respectivo lugar
     ArrayList<String> mumlTags = new ArrayList<String>();
     Document doc=null;
	String enlaceTop = "http://alberto.gil.webs.uvigo.es/SINT/22-23/";//parte general del enlace para ir concatenandolo con las posibles variables y asi ir leyendo todos los documentos xml
     String enlaceTotal = "muml2001.xml"; //valor inicial
     String enlaceFinal=null;
     try{
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();//creamos el dBuilder para poder procesar los documentos xml
     boolean finish=false;
     do{
          enlaceFinal = enlaceTop.concat(enlaceTotal);//concateno las dos partes de la URL completa de un documento

          try{
               doc = dBuilder.parse(enlaceFinal); //aqui actualizamos el doc para ir leyendo nuevos, sino se puede leer saltara una excepcion
          }catch(Exception e){
               fatalFiles.add(enlaceFinal);//aqui añado el total para el html a los fatal errors
               tagsFatalsXML.add(enlaceTotal);//aqui solo añado la parte muml200... para el xml a los fatal errors
               enlaceTotal=mumlTags.get(0);
               mumlTags.remove(0);
               continue;
          }



          String yearOfCurrentDoc = (String) xpath.evaluate("/Music/Year", doc, XPathConstants.STRING);//leemos el año del file
          if(Integer.parseInt(yearOfCurrentDoc)>=1980 && Integer.parseInt(yearOfCurrentDoc)<=2021){//vemos si esta en el rango de años validos
               mapDocs.put(yearOfCurrentDoc, doc);//si no es erroneo lo añadimos al mapa
               yearsArray.add(yearOfCurrentDoc);//y el año (llave) a la arrayList de las llaves
          }
          else{
               errorFiles.add(enlaceFinal);//aqui añado el URL total para el html de los errors
               tagsErrorsXML.add(enlaceTotal);//aqui solo añado la parte muml200... para el xml de los errors 
               if(mumlTags.size()>0){
                    enlaceTotal = mumlTags.get(0); //cogemos el primer elemento de la arrayList
                    mumlTags.remove(0); //eliminamos el q ya cogimos
                    continue;
               }
               else{
                    finish=true;
                    break;
               }

          }
          //ahora buscamos las mumls para seguir leyendo files
               NodeList mumlLista = (NodeList)xpath.evaluate("//Album/MuML", doc, XPathConstants.NODESET);//sacamos una lista de las MuML tags de un documento a nivel de album en caso de haberlas
          //vamos procesando una a una las de la lista para añadirlas a la lista de MuML tags general para leer diferentes documentos     
               for(int a=0; a<mumlLista.getLength();a++){
                    Element mu = (Element) mumlLista.item(a);
                    String muName = mu.getTextContent().trim();
                    if(muName.equals("")==false){
                         if(mumlTags.contains(muName)==false){ //solo añade las q no estan ya
                              mumlTags.add(muName);
                         }
                    }
               }
          ////////////////////sacamos una lista de las MuML tags de un documento a nivel de song en caso de haberlas
               NodeList mumlListaSongs = (NodeList)xpath.evaluate("//Album/Song/MuML", doc, XPathConstants.NODESET);//aqui tengo todas las muml de todas las canciones
               for(int a=0; a<mumlListaSongs.getLength();a++){
                    Element mu = (Element) mumlListaSongs.item(a);
                    String muName = mu.getTextContent().trim();
                    if(muName.equals("")==false){
                         if(mumlTags.contains(muName)==false){ //solo añade las q no estan ya
                              mumlTags.add(muName);
                         }
                    }
               }
          /////////////////////////comprobamos cuantas muml quedan para saber si ya terminamos de leer documentos o aun quedan
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
          ArrayList<String> countries = new ArrayList<String>();
          Document doc=null;
          try{
               for(int a=0; a<yearsArray.size(); a++){
                    doc=mapDocs.get(yearsArray.get(a)); //esta cogiendo el documento de cada año q estan guardados en yearsArray, en principio tienen el mismo tamaño
                    /////////vamos sacando los paises de cada album y los añadimos a la lista de countries si no estan ya para que no se repitan
                    NodeList listAlbums = (NodeList)xpath.evaluate("/Music/Album", doc, XPathConstants.NODESET);
                    for(int i=0; i<listAlbums.getLength(); i++){
                         String countryRead = (String)xpath.evaluate("Country" ,listAlbums.item(i), XPathConstants.STRING);
                         if(countries.contains(countryRead)==false){
                              countries.add(countryRead);
                         }
			     Collections.sort(countries, Collections.reverseOrder());//ordena los paises al reves
                    }
                    ///////////////
               }
               
          }catch(Exception e){
               e.printStackTrace();
          }
          
          
          return countries;
     }


     public ArrayList<Album> getQ1Albums(String country){//este metodo devuelve todos los albumes publicados en el pais que recibe como parametro pcountry
          ArrayList<Album> albumes = new ArrayList<Album>();
          Document doc=null;
          try{
          for(int a=0; a<yearsArray.size(); a++){
               doc=mapDocs.get(yearsArray.get(a)); //esta cogiendo el documento de cada año q estan guardados en yearsArray, en principio tienen el mismo tamaño
               NodeList listAlbums = (NodeList)xpath.evaluate("/Music/Album", doc, XPathConstants.NODESET);
               for(int i=0; i<listAlbums.getLength(); i++){
               //////////////vamos cogiendo cada uno de los atributos de cada album
               String name = (String)xpath.evaluate("Name" ,listAlbums.item(i), XPathConstants.STRING);
               String countryRead = (String)xpath.evaluate("Country" ,listAlbums.item(i), XPathConstants.STRING);
               String sing = (String)xpath.evaluate("Singer" ,listAlbums.item(i), XPathConstants.STRING);
               String group = (String)xpath.evaluate("Group" ,listAlbums.item(i), XPathConstants.STRING);
               String ISBN = (String)xpath.evaluate("ISBN" ,listAlbums.item(i), XPathConstants.STRING);
               String company = (String)xpath.evaluate("Company" ,listAlbums.item(i), XPathConstants.STRING);
               String aid = (String)xpath.evaluate("@aid" ,listAlbums.item(i), XPathConstants.STRING);// la @ es para coger los atributos de la etiqueta album
               String format = (String)xpath.evaluate("@format" ,listAlbums.item(i), XPathConstants.STRING);
               ///////sacamos la review
               NodeList list = listAlbums.item(i).getChildNodes();
               StringBuilder textContent = new StringBuilder();
               for (int o = 0; o < list.getLength(); ++o) {
                    Node child = list.item(o);
                    if (child.getNodeType() == Node.TEXT_NODE){
                         textContent.append(child.getTextContent());
                    }
                         
               }
                String review = textContent.toString().trim();
               ///////////
               NodeList yearList = (NodeList)xpath.evaluate("/Music/Year" ,doc, XPathConstants.NODESET);
               Element yearElement = (Element) yearList.item(0);
               String year = yearElement.getTextContent().trim();
               if(countryRead.equals(country)){//comprobamos si el pais del album es el mismo que el que recibimos por pcountry para saber si lo devolvemos o no
                    //////dependiendo de los datos leidos del album se usara un constructor u otro para luego añadir el album creado a la arrayList que devolvera este metodo
                    if(sing.equals("")==false && company.equals("")==true){
                         Album album = new Album(name,countryRead,sing,ISBN,aid,format,year);
                         album.setDescription(review);
                         albumes.add(album);
                    }
                    if(sing.equals("")==true && company.equals("")==true){
                         Album album = new Album(name,countryRead,group,ISBN,aid,format,year);
                         album.setDescription(review);
                         albumes.add(album);
                    }
                    if(sing.equals("")==false && company.equals("")==false){
                         Album album = new Album(name,countryRead,sing,ISBN,aid,format,year);
                         album.setDescription(review);
                         albumes.add(album);
                    }
                    if(sing.equals("")==true && company.equals("")==false){
                         Album album = new Album(name,countryRead,group,ISBN,aid,format,year);
                         album.setDescription(review);
                         albumes.add(album);
                    }

               }

               }
          }
          
     }catch(Exception e){
          e.printStackTrace();
     }
          albumes.sort(new AlbumComparator());//ordenamos los albumes utilizando un comparator segun los criterios especificos
          return albumes;
     }



     public ArrayList<Song> getQ1Songs(String country, String paid){//este metodo devuelve una lista con ls canciones cuyo alguno de sus generos sea Pop de un album especifico de un pais especifico
          ArrayList<Song> songs = new ArrayList<Song>();
          Document doc=null;
          try{   
               for(int a=0; a<yearsArray.size(); a++){
                    doc=mapDocs.get(yearsArray.get(a)); //esta cogiendo el documento de cada año q estan guardados en yearsArray, en principio tienen el mismo tamaño
                    NodeList listSongs = (NodeList)xpath.evaluate("/Music/Album[@aid ='"+paid+"']/Song", doc, XPathConstants.NODESET);
                    for(int i=0;i<listSongs.getLength();i++){
                         /////////////vamos cogiendo cada elemento de cada song
                         ArrayList<String> genres = new ArrayList<String>();
                         String title = (String)xpath.evaluate("Title", listSongs.item(i), XPathConstants.STRING);
                         String composer = (String)xpath.evaluate("Composer", listSongs.item(i), XPathConstants.STRING);
                         String duration = (String)xpath.evaluate("Duration", listSongs.item(i), XPathConstants.STRING);
                         String lang = (String)xpath.evaluate("@lang", listSongs.item(i), XPathConstants.STRING);
                         String sid = (String)xpath.evaluate("@sid", listSongs.item(i), XPathConstants.STRING);
                         NodeList genresList = (NodeList)xpath.evaluate("Genre", listSongs.item(i), XPathConstants.NODESET);
                         for(int o=0; o<genresList.getLength(); o++){
                              Element gen = (Element) genresList.item(o);
                              String genName = gen.getTextContent().trim();
                              genres.add(genName);
                         }
                         if(genres.contains("Pop")==true){//solo incluimos las canciones con algun genero siendo Pop
                              if(composer.equals("")==false){//dependiendo de los elementos leidos de la cancion usamos un constructor diferente
                                   songs.add(new Song(title, duration, genres, composer, sid, lang));
                              }
                              else{
                                   songs.add(new Song(title, duration, genres, sid, lang));
                              }
                         }
                    }
               }   

          }catch(Exception e){
               e.printStackTrace();
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


