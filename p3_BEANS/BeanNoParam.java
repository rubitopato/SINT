package p3;


public class BeanNoParam {
    
    private String parametro;//required parameter missing in the query

    public BeanNoParam(){ 

    }

    public String getParametro(){
        return parametro;
    }

    public void setParametro(String parametro){
        this.parametro = parametro;
    }

}
