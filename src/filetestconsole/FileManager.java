/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filetestconsole;

/**
 *
 * @author Administrador
 */
import java.io.*;
import java.util.ArrayList;

public class FileManager{
    public int percentT = 0 , percent = 0;
            
    public FileManager(){
        
    }
    public void copyfile(String srFile, String dtFile){
        try{
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            System.out.println("Star Coping..." + f1.getName() + " ...");
            if(!f1.isDirectory()){
                File dtDir = new File(f2.getParent());
                dtDir.mkdirs();
                InputStream in = new FileInputStream(f1);
                OutputStream out = new FileOutputStream(f2);

                byte[] buf = new byte[1024];
                int len;
                fileSize(f1);
                while ((len = in.read(buf)) > 0){
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                System.out.println("File copied.");
            } else {
                System.out.println(srFile + "\\" + "\n   to: " + dtFile + "\\");
                copyDirectory(srFile + "\\", dtFile + "\\");
            }
        }
        catch(FileNotFoundException ex){
                System.out.println(ex.getMessage() + " in the specified directory.");
                //System.exit(0);
        }
        catch(IOException e){
                System.out.println(e.getMessage());			
        }
    }
        
    public void copyDirectory(String srFile, String destiny){
        //String fName ;//, origen, destiny;
        File f = new File(srFile);
        File[] fl = f.listFiles();
        String realDestiny;
        int l = destiny.length();
        for (File file : fl) {
            //fName = file.getName();
            //System.out.println(fName);

            srFile = file.getPath();
            realDestiny = destiny + file.getName();
            //realDestiny = destiny + file.getPath().substring(l+1) + file.getName();
            
            //System.out.println("origin: " + srFile);
            //System.out.println("destiny: " + destiny2);

            this.copyfile(srFile, realDestiny);
        }
    }
    
    public ArrayList<File> findFiles(String searchFile, String srFile){
        String fName ;//, origen, destiny;
        File f = new File(srFile);
        
        File[] fl = f.listFiles();
        ArrayList<File> results = new ArrayList<>();

        for (File file : fl) {
            fName = file.getName();
            //System.out.println(fName);
            //System.out.println(srFile + "\\" + file.getName());
            if(file.isDirectory() && !fName.contains(searchFile)){
                results.addAll( this.findFiles( searchFile, srFile + "\\" + file.getName() ) );
            }
            String filePath;
            if(fName.contains(searchFile)){
                filePath = file.getPath();
                System.out.println(filePath);
                results.add(new File(filePath));
            }
        }
        return results;
    }
    public void totalSize(){
        
    }

    public String fileSize(File file) throws IOException{
        long size = file.length(); //en bytes
        String mesure = "bytes";
        if(size > 1024 && size <= 1048576){
            size = size/1024;//en Kilo bytes
            mesure = "Kb";
        } else if(size > 1048576 && size <= 1073741824){
            size = size / 1048576; // en Mega bytes
            mesure = "Mb";
        } else if(size > 1073741824){
            size = size / 1073741824; // en Giga bytes
            mesure = "Gb";
        }
        String fs = size + " " + mesure;
        System.out.println("file size: " + fs);
        return fs;
    }
        
}
