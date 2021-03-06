
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filetestconsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Navs
 */
public class FileTestConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String searchListFile="baseDatos.txt",text,tmp;
        ArrayList<String> searchList = new ArrayList<>();
        File f;
        f = new File( new File("").getAbsolutePath() );
        File[] fl = f.listFiles();
        for (File file : fl) {
            //fName = file.getName();
            System.out.println(file.getName());
        }
        
        try{//leyendo del txt
            BufferedReader buffReader=new BufferedReader(new FileReader("searchList.txt"));
            System.out.println("Reading txt search list Ariel"  );
            while(buffReader.ready()) {
                text=buffReader.readLine();
                System.out.println(" searching for: - " + text );
                searchList.add(text);
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("ERROR-2: no se encuentra el txt");
        }
        catch(IOException ex) {
            System.out.println("ERROR-3");
        }

        String origen;
        origen = "G:\\pkg";
        String destiny;
        destiny = "G:\\Nueva carpeta\\";
        System.out.println(f.getAbsolutePath());
        
        
        FileManager fm = new FileManager();
        System.out.println("############ -------------------");
        ArrayList<File> copyList = new ArrayList<>();
        //copyList = fm.findFiles("Ultimo", origen);//Hacer modulo para mayusculas y minusculas indistintamente
        
        for(String wish : searchList){
            copyList.addAll( fm.findFiles( wish, origen ) );
        }
        
        if(copyList.isEmpty()){ 
            System.out.println("Not search results were find");   
        } else {
            int l = origen.length();
            String realDestiny;
            for(File file : copyList){
                System.out.println("a copiar: " + file.getPath() + "\n --> to " + destiny );
                fm.copyfile(file.getPath(), destiny + file.getName());// para copiarlo todo en la misma carpeta
            }
        }
    }
}
