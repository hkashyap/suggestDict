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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hirak & Ujjal
 */
class WordSearch {

    private String suggestions[];//=new String[5];
    private ObjectInputStream input;
    private static tree tr=new tree();
    private tree_nodeSerializable tr_node;
    //private static boolean new_flag=true;
   // private static char ch1;
    WordSearch(String comboBoxWord) {
          suggestions = new String[5];
          System.out.println(comboBoxWord.length());
          if(comboBoxWord.length()==1)
          {
              tr=new tree();
              //create tree
              //init tree;
              //sugeeg
            char ch1 = comboBoxWord.charAt(0);
            openFile(String.valueOf(ch1));
            readRecords();
            closeFile();
            //new_flag=false;
          }

            suggestions = tr.suggest(comboBoxWord);
    }

    String getWordSuggestion(int i) {
        return suggestions[i];
    }

/*    private void readRecords(){

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
    static String trim(String word) {  //done
        String temp = "";
        boolean moreThenOneWhiteSpace = false;
        for(int i=0; i<word.length();i++){
           if(word.charAt(i) == ' ' && !moreThenOneWhiteSpace){
               if(i != 0)
                   temp += word.charAt(i);
               moreThenOneWhiteSpace = true;
           }
           else if((word.charAt(i)>96 && word.charAt(i)<123) || (word.charAt(i)>64 && word.charAt(i)<91)){
               moreThenOneWhiteSpace = false;
               temp += Character.toLowerCase(word.charAt(i));
           }
        }
        return temp;
    }

}
