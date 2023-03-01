package p2;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.xml.parsers.*;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Sint181P2 extends HttpServlet{

	public DataModel model = new DataModel();//esta variable tendra el DataModel para procesar y enviar los datos requeridos
	public FrontEnd front = new FrontEnd();//esta variable tendra el FrontEnd para representar por pantalla los datos que reciba
	

	public void init(){//este es un metodo que solo se ejecuta una vez al llamar a este servlet
		model.readDocument();
	}


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{//este es un metodo que se llama cada vez que se cambia la URL desde el buscador
	     boolean auto=false;//se usara para ver si usamos auto mode o no
		 String passwd_recibida;
		 String pphase;
		 PrintWriter out = null;
		 String contra = "22Rubben23";
		 
	try{
		out=res.getWriter();
		if(req.getParameter("auto")!=null && req.getParameter("auto").equals("true")){//aqui vemos el parametro auto para saber como tratar las respuestas
			auto=true;
		 }
		 else{
			res.setContentType("text/html");
			res.setContentType("charset=UTF-8");
			auto=false;
		 }

		 if(req.getParameter("p")==null){//no se ha encontrado la contraseña
			if(auto==true){
				front.autoMissingPasswd(out);
			}
			else{
				front.htmlMissingPasswd(out);
			}
		 }
		 
		 else{
			if(req.getParameter("p").equals(contra)==false){//aqui se entra si p no es nulo y se comprueba si la contraseña no es correcta
				if(auto==true){
					front.autoWrongPasswd(out);
				}
				else{
					front.htmlWrongPasswd(out);
				}	
		 	}
			else{//aqui se entra si la contraseña no es nula y es correcta
				passwd_recibida=req.getParameter("p");

				if(req.getParameter("pphase")==null){//aqui se pone por defecto la primera pagina porque no hay pphase especifica
					if(auto==true){
						front.autoPhase01(out,model);
					}
					else{
						front.doGetPhase01(out,model);
					}
				}
				else{//a partir de aqui comprobamos que pphase hay para cargar la pagina correspondiente
					if(req.getParameter("pphase").equals("01")==true){
						if(auto==true){
							front.autoPhase01(out,model);
						}
						else{
							front.doGetPhase01(out,model);
						}
					}
					else if(req.getParameter("pphase").equals("02")==true){
						if(auto==true){
							ArrayList<String> errorFiles = new ArrayList<String>();
							ArrayList<String> fatalFiles = new ArrayList<String>();
							errorFiles=model.getQ1ErrorFilesXML();
							fatalFiles=model.getQ1FatalFilesXML();
							front.autoPhase02(out,errorFiles,fatalFiles);
						}
						else{
							ArrayList<String> errorFiles = new ArrayList<String>();
							ArrayList<String> fatalFiles = new ArrayList<String>();
							errorFiles=model.getQ1ErrorFiles();
							fatalFiles=model.getQ1FatalFiles();
							front.doGetPhase02(out,errorFiles,fatalFiles);
						}
					}
					else if(req.getParameter("pphase").equals("11")==true){
						ArrayList<String> countries = new ArrayList<String>();
						countries=model.getQ1Countries();//llamamos al metodo getQ1Countries para poder enviarle los datos que recibamos al FrontEnd para sacar por pantalla los paises de los albumes
						if(auto==true){
							front.autoPhase11(out,countries);
						}
						else{
							front.doGetPhase11(out,countries);
						}
					}
					else if(req.getParameter("pphase").equals("12")==true){
						ArrayList<Album> albums = new ArrayList<Album>();
						String pcountry = req.getParameter("pcountry");
						albums=model.getQ1Albums(pcountry);//llamamos al metodo getQ1Albums para poder enviarle los datos que recibamos al FrontEnd para sacar por pantalla los albumes del pais especificado en el pcountry
						if(pcountry==null){
							if(auto==false){
								front.missingCompulsory(out,"pcountry");//aqui llamamos a un metodo del FrontEnd para sacar por pantalla que un atributo del URL necesario para esta pphase no se ha encontrado
							}
							else{
								front.missingAutoCompulsory(out,"pcountry");
							}
							
						}
						else{
							if(auto==true){
								front.autoPhase12(out,pcountry,albums);//enviamos los datos al FrontEnd para que saque los datos de los albumes del pais en pcountry
							}
							else{
								front.doGetPhase12(out,pcountry,albums);
							}
						}
					}
					else if(req.getParameter("pphase").equals("13")==true){
						ArrayList<Song> songs = new ArrayList<Song>();
						String pcountry = req.getParameter("pcountry");
						String paid = req.getParameter("paid");
						songs=model.getQ1Songs(pcountry, paid);//llamamos al metodo getQ1Songs para poder enviarle los datos que recibamos al FrontEnd para sacar por pantalla las canciones del album cuyo aid sea paid en el pais especificado por pcountry
						if(pcountry==null){
							if(auto==false){
								front.missingCompulsory(out,"pcountry");
							}
							else{
								front.missingAutoCompulsory(out,"pcountry");
							}
						}
						else if(paid==null){
							if(auto==false){
								front.missingCompulsory(out,"paid");//missing atributte
							}
							else{
								front.missingAutoCompulsory(out,"paid");
							}
						}
						else{
							if(auto==true){
								front.autoPhase13(out,pcountry,paid,songs);
							}
							else{
								front.doGetPhase13(out,pcountry,paid,songs);
							}
						}
					}
				}
			}
		}

		 




	}catch(Exception e){
		e.printStackTrace();
	}

}//doGet
    
}//class
