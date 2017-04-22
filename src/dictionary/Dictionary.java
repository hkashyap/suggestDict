/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

/**
 *
 * @author HiRock'z
 */

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Dictionary {

public static Vector means=new Vector();
public static int j=0;
private Formatter fileBuilder;
String suggestions[]=new String[5];
enum alpha {a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z};
    int bytelength[]=new int[26];
    //ObjectOutputStream output;
    //ObjectInputStream input;
    //Formatter m_output;
   // String message;
    //int messageType;

public Dictionary(){
    load_meaning();
}

private void load_meaning(){
    try{
        //File f2;=new File("mean.txt");
        //f2.
        
        BufferedReader f= new BufferedReader(new FileReader("mean.txt"));
        String mean= null;
        while((mean=f.readLine())!= null)
        {
            means.add(j, mean);
           // means.addElement(mean);
            j++;
        }
        f.close();
    }
    catch(FileNotFoundException fne){
          //  System.out.println("Fault");
            try{
           fileBuilder = new Formatter("mean.txt");
           fileBuilder.close();
            }
            catch(IOException ioe){
                System.err.println("File Can't be created");
            }
            load_meaning();
        }
    catch(Exception e ){
        System.err.println("Unable to read from mean.text");
        //JOptionPane.showMessageDialog(null, "Unable to read from mean.text", "Error", JOptionPane.ERROR_MESSAGE););
        //load_meaning();

    }
}

}
