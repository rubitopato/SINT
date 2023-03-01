package p3;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.xml.parsers.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import p3.Bean01;
import p3.Bean02;
import p3.Bean11;
import p3.Bean12;
import p3.Bean13;
import p3.BeanNoParam;
import p3.BeanPassword;


public class Sint181P3 extends HttpServlet{

	public DataModel model = new DataModel();//esta variable tendra el DataModel para procesar y enviar los datos requeridos
	//creamos las beans que vamos a necesitar
	private Bean01 bean01;
	private Bean02 bean02;
	private Bean11 bean11;
	private Bean12 bean12;
	private Bean13 bean13;
	private BeanNoParam beanNoParam;
	private BeanPassword beanPassword;

	public void init(){//este es un metodo que solo se ejecuta una vez al llamar a este servlet
		DataModel.readDocument();
	}


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{//este es un metodo que se llama cada vez que se cambia la URL desde el buscador
		 String passwd_recibida;
		 String pphase;
		 String contra = "22Rubben23";
		 
	try{


		 if(req.getParameter("p")==null){//no se ha encontrado la contraseña
			beanPassword = new BeanPassword();
			beanPassword.setMensaje("No Password"); //cambiamos el valor de la variable mensaje
			req.setAttribute("bean", beanPassword);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/fasePassword.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
			rd.forward(req,res);
		 }
		 
		 else{
			if(req.getParameter("p").equals(contra)==false){//aqui se entra si p no es nulo y se comprueba si la contraseña no es correcta
				beanPassword = new BeanPassword();
				beanPassword.setMensaje("Wrong Password"); //cambiamos el valor de la variable mensaje
				req.setAttribute("bean", beanPassword);
				ServletContext sc = getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/fasePassword.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
				rd.forward(req,res);
		 	}
			else{//aqui se entra si la contraseña no es nula y es correcta
				passwd_recibida=req.getParameter("p");

				if(req.getParameter("pphase")==null){//aqui se pone por defecto la primera pagina porque no hay pphase especifica
					bean01 = new Bean01();
					req.setAttribute("bean", bean01);
					ServletContext sc = getServletContext();
					RequestDispatcher rd = sc.getRequestDispatcher("/fase01.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
					rd.forward(req,res);
					
				}
				else{//a partir de aqui comprobamos que pphase hay para cargar la pagina correspondiente
					if(req.getParameter("pphase").equals("01")==true){
						bean01 = new Bean01();
						req.setAttribute("bean", bean01);
						ServletContext sc = getServletContext();
						RequestDispatcher rd = sc.getRequestDispatcher("/fase01.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
						rd.forward(req,res);
					}
					else if(req.getParameter("pphase").equals("02")==true){
						bean02 = new Bean02();
						req.setAttribute("bean", bean02);
						ServletContext sc = getServletContext();
						RequestDispatcher rd = sc.getRequestDispatcher("/fase02.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
						rd.forward(req,res);
					}
					else if(req.getParameter("pphase").equals("11")==true){
						bean11 = new Bean11();
						req.setAttribute("bean", bean11);
						ServletContext sc = getServletContext();
						RequestDispatcher rd = sc.getRequestDispatcher("/fase11.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
						rd.forward(req,res);
					}
					else if(req.getParameter("pphase").equals("12")==true){
						ArrayList<Album> albums = new ArrayList<Album>();
						String pcountry = req.getParameter("pcountry");
						albums = DataModel.getQ1Albums(pcountry);
//llamamos al metodo getQ1Albums para poder enviarle los datos que recibamos al FrontEnd para sacar por pantalla los albumes del pais especificado en el pcountry
						if(pcountry==null){
							beanNoParam = new BeanNoParam();
							beanNoParam.setParametro("pcountry");
							req.setAttribute("bean", beanNoParam);
							ServletContext sc = getServletContext();
							RequestDispatcher rd = sc.getRequestDispatcher("/faseNoParam.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
							rd.forward(req,res);
						}
						else{
							bean12 = new Bean12();
							bean12.setAlbums(albums);
							bean12.setPcountry(pcountry);
							req.setAttribute("bean", bean12);
							ServletContext sc = getServletContext();
							RequestDispatcher rd = sc.getRequestDispatcher("/fase12.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
							rd.forward(req,res);
						}
					}
					else if(req.getParameter("pphase").equals("13")==true){
						ArrayList<Song> songs = new ArrayList<Song>();
						String pcountry = req.getParameter("pcountry");
						String paid = req.getParameter("paid");
						songs=DataModel.getQ1Songs(pcountry, paid);//llamamos al metodo getQ1Songs para poder enviarle los datos que recibamos al FrontEnd para sacar por pantalla las canciones del album cuyo aid sea paid en el pais especificado por pcountry
//llamamos al metodo getQ1Songs para poder enviarle los datos que recibamos al FrontEnd para sacar por pantalla las canciones del album cuyo aid sea paid en el pais especificado por pcountry
						if(pcountry==null){
							beanNoParam = new BeanNoParam();
							beanNoParam.setParametro("pcountry");
							req.setAttribute("bean", beanNoParam);
							ServletContext sc = getServletContext();
							RequestDispatcher rd = sc.getRequestDispatcher("/faseNoParam.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
							rd.forward(req,res);
						}
						else if(paid==null){
							beanNoParam = new BeanNoParam();
							beanNoParam.setParametro("paid");
							req.setAttribute("bean", beanNoParam);
							ServletContext sc = getServletContext();
							RequestDispatcher rd = sc.getRequestDispatcher("/faseNoParam.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
							rd.forward(req,res);
						}
						else{
							bean13 = new Bean13();
							bean13.setSongs(songs);
							bean13.setPcountry(pcountry);
							bean13.setPaid(paid);
							req.setAttribute("bean", bean13);
							ServletContext sc = getServletContext();
							RequestDispatcher rd = sc.getRequestDispatcher("/fase13.jsp"); //llamamos al jsp que se encargara de gestionar esta pantalla
							rd.forward(req,res);
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
