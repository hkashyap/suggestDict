/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Hirak & Ujjal
 */
class SearchWord {

    private int flag=2;
    private String meaning;
    private ObjectInputStream input;
    int mean_index;
    private tree tr=new tree();
    private tree_nodeSerializable tr_node;

    public SearchWord(String toString) {
        //the search method must be called by the constructor
        //after searching it initializes the flag and the getFlag method will return the flags...
        /*
         * flag = 0 -- found
         * flag = 1 -- not found
         * flag = 2 -- error
         *
         */
                char ch=toString.charAt(0);
                openFile(String.valueOf(ch));
               readRecords();
                closeFile();
            mean_index=tr.search(toString);
            if(mean_index==-1)
                flag=1;
            else
            {
                flag=0;
            meaning=(String) Dictionary.means.get(mean_index);
            }
    }
/*
     private void readRecords(){

        try{
            while(true){

           tr_node=(tree_nodeSerializable) input.readObject();
           if(tr_node!=null)
           tr.insert(tr_node.word,tr_node.mean_index);
            }

        }
        catch(EOFException eof)
        {
            return;
        }
        catch(ClassNotFoundException cnf){
            System.err.println("Unable to create object");
        }
        catch(IOException iox){
          System.err.println("Error while reading");
        }
    }
*/
    public int getFlag() {
        return flag;
    }

    public String getMeaning() {
        return meaning;
    }




      private void openFile(String fname){
        try{
            input=new ObjectInputStream(new FileInputStream(String.format("%s.ser",fname)));
        }
        catch(FileNotFoundException fnf){
           return;
        }
        catch(IOException iox2){
           System.out.println("Error Opening File1");
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
         private void closeFile(){
        try{
                input.close();
        }
        catch(IOException iox){
            System.out.println("Error closing file");
        }
    }
}