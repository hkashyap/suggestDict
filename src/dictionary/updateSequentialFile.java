/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HiRock'z
 */
public class updateSequentialFile {
    
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private RandomAccessFile access;
    private Formatter fileBuilder;
    private File fileBuilder2;
    private String message;
    private int messageType;
    private BufferedWriter txtfileWriter;
    tree tr;
    tree_nodeSerializable tr_node;
    boolean new_flag=false;
    int a=0;

//    File currentPath = new File("C:\\Users\\HiRock'z\\Desktop\\dictionary\\");

    public updateSequentialFile(String iword,String imean){
        tr=new tree();
        String s;
        int pos;
        char ch=iword.charAt(0);
        openFile(String.valueOf(ch));
        if(!new_flag)
        {
            readRecords();
            closeFile();
        }
        if((pos=tr.search(iword))!=-1)
    {
        s=(String) Dictionary.means.get(pos);
       // System.out.println(s);
        //s=s.concat("\\ ");
        s=s.concat(String.format("\\ %s",imean));
        System.out.println(s);
        Dictionary.means.set(pos,s);
                try {
                    //BufferedWriter bw=new BufferedWriter(new FileWriter("mean.txt"));
                    //bw.writeLine(means.get(pos));
                    // fileBuilder= new Formatter("mean.txt");
                    txtfileWriter = new BufferedWriter(new FileWriter("mean.txt"));
                } catch (IOException ex) {
                    Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
                }
        for(int i=0;i<Dictionary.j;i++)
        {
                try {
                    //fileBuilder.format("%s\n",Dictionary.means.get(i));
                    txtfileWriter.write(Dictionary.means.get(i).toString());
                    txtfileWriter.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
            try {
                txtfileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        messageType=JOptionPane.PLAIN_MESSAGE;
        message="New meaning is successfully added";
    }
     else
    {
        Dictionary.means.add(Dictionary.j++,imean);
                try {
                    //fileBuilder = new Formatter("mean.txt");
                    txtfileWriter = new BufferedWriter(new FileWriter("mean.txt"));
                } catch (IOException ex) {
                    Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
                }
        for(int i=0;i<(Dictionary.j);i++)
        {
                try {
                    //fileBuilder.format("%s\n",Dictionary.means.get(i));
                    txtfileWriter.write(Dictionary.means.get(i).toString());
                    txtfileWriter.newLine();
                } catch (IOException ex) {
                    Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            try {
                txtfileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        tr.insert(iword,(Dictionary.j-1));    //save in tree file also
        //fileBuilder.close();
      //  message = "Write Complete!";
       // messageType = JOptionPane.PLAIN_MESSAGE;
        openFile2(String.valueOf(ch));
        writeRecords();
        closeFile2();
     }
 }


    private void openFile(String fname){
        try{
            input=new ObjectInputStream(new FileInputStream(String.format("%s.ser",fname)));
        }
        catch(FileNotFoundException fnf){
           new_flag=true;
           return;
        }
        catch(IOException iox2){
           System.out.println("Error Opening File1");
        }
    }

     private void openFile2(String fname){
        try{
            output=new ObjectOutputStream(new FileOutputStream(String.format("%s.ser",fname)));
        }
        catch(FileNotFoundException fnf){
            try {
                message="..right here........";
                fileBuilder = new Formatter(String.format("%s.ser", fname));
                fileBuilder.close();
                openFile2(fname);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(IOException iox2){
            System.out.println("Error Opening File2");
        }
    }
    private void readRecords(){

        //int a=0;
        try{
            while(true){
                
           tr_node=(tree_nodeSerializable) input.readObject();
           if(tr_node!=null)
           tr.insert(tr_node.word,tr_node.mean_index);
          // a++;
            }

        }
        catch(EOFException eof)
        {
           // System.out.println(a);
            return;
        }
        catch(ClassNotFoundException cnf){
            System.err.println("Unable to create object");
        }
        catch(IOException iox){
          System.err.println("Error while reading");
        }
    }

    private void writeRecords(){
        inorderWrite(tr.root);
        messageType=JOptionPane.PLAIN_MESSAGE;
        message="Successfully updated";
    }
    private void closeFile(){
        try{
                input.close();
        }
        catch(IOException iox){
            System.out.println("Error closing file");
        }
    }
     private void closeFile2(){
        try{
                output.close();
        }
        catch(IOException iox){
            System.out.println("Error closing file");
        }
    }
     private void inorderWrite(tree_nodeSerializable tr_node1){

            if (tr_node1 == null) {
                return;
            }
            inorderWrite(tr_node1.leftnode);
          try{
            output.writeObject(tr_node1);
            a++;
            System.out.println(a);
        } catch (IOException ex) {
            System.out.println("Tree write error");
            Logger.getLogger(updateSequentialFile.class.getName()).log(Level.SEVERE, null, ex);
        }
            inorderWrite(tr_node1.rightnode);
     }

     public String getMessage(){
         return message;
     }

     public int getMessageType(){
         return messageType;
     }

}
