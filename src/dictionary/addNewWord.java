/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

/**
 *
 * @author d_red_horn
 */
class addNewWord {

    private String Word;
    private String Meaning;
    private String Message;
    private int messageType;

    addNewWord(String word, String meaning) {
        Word = word;
        Meaning = meaning;
    }

    public String getMessage(){
        return Message;
    }

    public int getMessageType(){
        return messageType;
    }

}
