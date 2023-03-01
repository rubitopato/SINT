package p2;

import java.io.PrintWriter;
import java.util.ArrayList;

public class FrontEnd {
    public void autoMissingPasswd(PrintWriter out){//pantalla para missing password en auto
        out.println("<?xml version='1.0' encoding='utf-8'?>");
	out.println("<wrongRequest>no passwd</wrongRequest>");
    }
    public void htmlMissingPasswd(PrintWriter out){//pantalla para missing pasword 
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Missing Password</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1>Missing Password</h1>");
        out.println("<br>");
        out.println("<h2>Try again inserting the password in the url</h2>");
        out.println("</body>");
        out.println("<b>@Ruben Gonzalez<b>");
    }
    public void autoWrongPasswd(PrintWriter out){//pantalla para wrong password en auto
        out.println("<?xml version='1.0' encoding='utf-8'?>");
        out.println("<wrongRequest>bad passwd</wrongRequest>");
    }
    public void htmlWrongPasswd(PrintWriter out){//pantalla para wrong password
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Wrong Password</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1>Wrong Password</h1>");
        out.println("<br>");
        out.println("<h2>Try again inserting the password in the url</h2>");
        out.println("</body>");
        out.println("<b>@Ruben Gonzalez</b>");
    }
    public void missingCompulsory(PrintWriter out, String parameter){//pantalla para missing compulsory parameter
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Missing parameter</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<br>");
        out.println("<h1>Missing parameter: "+parameter+"</h1>");
        out.println("<br>");
        out.println("<br>");
        out.println("<form method=\"GET\" name=\"form\">");
        out.println("<input type=\"hidden\" name=\"p\" value=\"22Rubben23\">");
        out.println("<input type=\"hidden\" name=\"pphase\" value=\"&quot;01&quot;\">");
        out.println("<input type=\"submit\" id=\"home\" value=\"HOME\" onclick=\"form.pphase.value=&quot;01&quot;\">");//de vuelta al inicio
        out.println("</form>");
        out.println("</body>");
        out.println("<b>@Ruben Gonzalez</b>");
    }
    public void missingAutoCompulsory(PrintWriter out, String parameter){//pantalla para missing compulsory parameter en auto
        out.println("<?xml version='1.0' encoding='utf-8'?>");
	out.println("<wrongRequest>no param:"+parameter+"</wrongRequest>");
    }
    public void autoPhase01(PrintWriter out, DataModel model){//pantalla inicial sdel servicio en auto
        out.println("<?xml version='1.0' encoding='utf-8'?>");
	out.println("<service>");
	out.println("<status>OK</status>");
	out.println("</service>");
    }
    public void doGetPhase01(PrintWriter out, DataModel model){//pantalla inicial sdel servicio
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        ///
        out.println("<title>Music Information Service</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Music Information Service</h1>");
        out.println("<br>");
        out.println("<br>");
        out.println("<h1 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><b>Welcome to this service</b></h1>");
        out.println("<br>");
        out.println("<br>");
        out.println("<o1>");
        out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><a href=\"?p=22Rubben23&pphase=02\">Show error files</a></li>");
        out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><a href=\"?p=22Rubben23&pphase=11\">Query 1: Pop songs of an Album of a Country</a></li>");
        out.println("</o1>");
        out.println("<br>");
        out.println("<br>");
        out.println("<h3><b>@Ruben Gonzalez</b></h3>");
        out.println("</body>");
        out.println("</html>");
    }
    public void autoPhase02(PrintWriter out, ArrayList<String> errorFiles, ArrayList<String> fatalFiles){//pantalla para los documentos con errores en auto 
        ArrayList<String> errors = new ArrayList<String>();
        ArrayList<String> fatals = new ArrayList<String>();
        errors=errorFiles;
        fatals=fatalFiles;
        out.println("<?xml version='1.0' encoding='utf-8'?>");
	    out.println("<wrongDocs>");

        out.println("<errors>");
        for(int i=0; i<errors.size(); i++){
            out.println("<error><file>"+errors.get(i)+"</file></error>");
        }
        out.println("</errors>");

        out.println("<fatalerrors>");
        for(int i=0; i<fatals.size(); i++){
            out.println("<fatalerror><file>"+fatals.get(i)+"</file></fatalerror>");
        }
        out.println("</fatalerrors>");

	    out.println("</wrongDocs>");
    }
    public void doGetPhase02(PrintWriter out, ArrayList<String> errorFiles, ArrayList<String> fatalFiles){//pantalla para los documentos con errores en auto 
        ArrayList<String> errors = new ArrayList<String>();
        ArrayList<String> fatals = new ArrayList<String>();
        errors=errorFiles;
        fatals=fatalFiles;
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Files discarded</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Music Information Service</h1>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><b>Files with errors: "+errors.size()+"<b></h2>");
        for(int i=0; i<errors.size(); i++){
            out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">"+errors.get(i)+"</li>");
            out.println("<br>");
        }
        out.println("<br>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><b>Files with fatal errors: "+fatals.size()+"<b></h2>");
        out.println("<o1>");
        for(int i=0; i<fatals.size(); i++){
            out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">"+fatals.get(i)+"</li>");
            out.println("<br>");
        }
        out.println("</o1>");
        out.println("<br>");
        out.println("<br>");
        out.println("<form method=\"GET\" name=\"form\">");
        out.println("<input type=\"hidden\" name=\"p\" value=\"22Rubben23\">");
        out.println("<input type=\"hidden\" name=\"pphase\" value=\"&quot;01&quot;\">");
        out.println("<input type=\"submit\" id=\"back\" value=\"BACK\" onclick=\"form.pphase.value=&quot;01&quot;\">");//de vuelta al inicio
        out.println("</form>");
        out.println("</body>");
        out.println("<br>");
        out.println("<br>");
        out.println("<b>@Ruben Gonzalez</b>");
        

    }
    public void autoPhase11(PrintWriter out, ArrayList<String> countries){//pantalla para los paises de los albumes en auto
        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<countries>");
        for(int i=0; i<countries.size(); i++){
            out.println("<country>" + countries.get(i) + "</country>");
        }
        out.println("</countries>");
    }
    public void doGetPhase11(PrintWriter out, ArrayList<String> countries){//pantalla para los paises de los albumes
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        ///
        out.println("<title>Years Available</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Query 1: Phase 1</h1>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><b>Please; select a Country<b></h2>");
        out.println("<br>");
        out.println("<br>");
        out.println("<o1>");
        for(int i=0; i<countries.size(); i++){
            out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><a href=\"?pphase=12&p=22Rubben23&pcountry=" + countries.get(i) + "\">"+countries.get(i)+"</a></li>");
            out.println("<br>");
        }
	    out.println("</o1>");
	    out.println("<br>");
	    out.println("<br>");
        out.println("<form method=\"GET\" name=\"form\">");//para escribir "" hay q ponerlo asi: \" \"
        out.println("<input type=\"hidden\" name=\"p\" value=\"22Rubben23\">");
        out.println("<input type=\"hidden\" name=\"pphase\" value=\"12\">");
        out.println("<input value=\"HOME\" id=\"home\" type=\"submit\" onclick=\"form.pphase.value=&quot;01&quot;\">");//quot para que se ponga bien el valor 02 como value del pphase
        out.println("</form>");
        
        out.println("<br>");
        out.println("<br>");
        out.println("<h3><b>@Ruben Gonzalez</b></h3>");
        out.println("</body>");
        out.println("</html>");
    }
    public void autoPhase12(PrintWriter out, String pcountry, ArrayList<Album> albums){//pantalla para los albumes de un pais (pcountry) en auto
        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<albums>");
        for(int i=0;i<albums.size();i++){
            out.println("<album year='"+albums.get(i).getYear()+"' performer='"+albums.get(i).getPerformer()+"' review='"+albums.get(i).getDescription()+"'>"+albums.get(i).getName()+"</album>");//aqui habria que poner esos albums
        }
        out.println("</albums>");
    }
    public void doGetPhase12(PrintWriter out, String pcountry, ArrayList<Album> albums){//pantalla para los albumes de un pais (pcountry)
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Albums released in country</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Music Information Service</h1>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Query 1: Phase 2 (Country = "+pcountry+")</h2>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Please, select an album</h2>");
        out.println("<br>");
        out.println("<br>");
        out.println("<o1>");
        for(int i=0;i<albums.size();i++){
            out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\"><a href=\"?pphase=13&p=22Rubben23&pcountry=" + pcountry + "&paid=" + albums.get(i).getAid() + "\">Album = " + albums.get(i).getName() + "--- Year = '" + albums.get(i).getYear() + "' --- Performer = '" + albums.get(i).getPerformer() + "' --- Review = '"+albums.get(i).getDescription()+"'</a></li>");//aqui habria que poner esos albums
            out.println("<br>");
        }
        out.println("</o1>");
        out.println("<br>");
        out.println("<br>");
        out.println("<form method=\"GET\" name=\"form\">");
        out.println("<input type=\"hidden\" name=\"p\" value=\"22Rubben23\">");
        out.println("<input type=\"hidden\" name=\"pphase\" value=\"01\">");
        out.println("<input type=\"submit\" id=\"home\" value=\"HOME\" onclick=\"form.pphase.value=&quot;01&quot;\">");//de vuelta a la primera pagina
        out.println("<input type=\"submit\" id=\"back\" value=\"BACK\" onclick=\"form.pphase.value=&quot;11&quot;\">");//de vuelta a la ppagina anterior
        out.println("</form>");
        out.println("</body>");
        out.println("<br>");
        out.println("<br>");
        out.println("<b>@Ruben Gonzalez</b>");
    }
    public void autoPhase13(PrintWriter out, String pcountry, String paid, ArrayList<Song> songs){//pantalla para las canciones Pop de un album de un pais en auto
        out.println("<?xml version='1.0' encoding='utf-8' ?>");
        out.println("<songs>");
        for(int i=0;i<songs.size();i++){
            out.println("<song lang='"+songs.get(i).getLang()+"' genres='"+songs.get(i).getBien()+"' composer='"+songs.get(i).getComposer()+"'>"+songs.get(i).getTitle()+"</song>\n");//aqui habria que poner esos albums
        }
        out.println("</songs>");
    }
    public void doGetPhase13(PrintWriter out, String pcountry, String paid, ArrayList<Song> songs){//pantalla para las canciones Pop de un album de un pais
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Songs of an album</title>");
        out.println("</head>");
        out.println("<body style=\"background-color:rgb(255, 255, 255);padding-top: 2em;padding-bottom: 2em;padding-left: 15em;padding-right: 15em;\">");
        out.println("<h1 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Music Information Service</h1>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Query 1: Phase 3 (Country = "+pcountry+", Album = "+paid+")</h2>");
        out.println("<br>");
        out.println("<h2 style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">This is the query result:</h2>");
        out.println("<br>");
        out.println("<br>");
        out.println("<o1>");
        for(int i=0;i<songs.size();i++){
            out.println("<li style=\"border-style: solid;border-width: 0.5px;border-radius: 5px;text-align: left;padding: 0.5em;background: linear-gradient(to right, antiquewhite, rgb(218, 192, 158));\">Title = '"+songs.get(i).getTitle()+"' --- Language = '"+songs.get(i).getLang()+"' --- Genres = '"+songs.get(i).getBien()+"' --- Composer = '"+songs.get(i).getComposer()+"'</li>\n");//aqui habria que poner esos albums
            out.println("<br>");
        }
        out.println("</o1>");
        out.println("<br>");
        out.println("<br>");

        out.println("<button id=\"home\" onclick='location.href=\"?pphase=01&p=22Rubben23\"'>HOME</button>");//de vuelta a la ppagina anterior
        out.println("<button id=\"back\" onclick='location.href=\"?pphase=12&p=22Rubben23&pcountry="+pcountry+"\"'>BACK</button>");//de vuelta a la primera pagina  

        out.println("</body>");
        out.println("<br>");
        out.println("<br>");
        out.println("<b>@Ruben Gonzalez</b>");
    }
}
