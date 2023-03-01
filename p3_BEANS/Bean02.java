package p3;

import java.util.ArrayList;

public class Bean02 {

    private ArrayList<String> errorFiles;//files with years outside the range
    private ArrayList<String> fatalFiles;//files with xml errors 

    public Bean02(){ 
        errorFiles=DataModel.getQ1ErrorFiles();//we call the method of the datamodel to get the error files
        fatalFiles=DataModel.getQ1FatalFiles();//we call the method of the datamodel to get the fatal error files
    }

    public ArrayList<String> getErrorFiles(){
        return errorFiles;
    }

    public ArrayList<String> getFatalFiles(){
        return fatalFiles;
    }

    public void setFatalFiles(ArrayList<String> fatalFiles){
        this.fatalFiles = fatalFiles;
    }

}
