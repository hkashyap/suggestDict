/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

/**
 *
 * @author HiRock'z
 */
import java.io.Serializable;

/**
 *
 * @author HiRock'z
 */

public class tree_nodeSerializable implements Serializable{
    int mean_index;
    String word;
    tree_nodeSerializable leftnode;
    tree_nodeSerializable rightnode;

    //function member
   public tree_nodeSerializable(String iword,int mean_i){
        word=iword;
        mean_index=mean_i;
        leftnode=rightnode=null;
    }

    public void insert(String iword,int mean_i)
    {
        if(iword.compareTo(word)<0)
        {
            if(leftnode==null)
                leftnode=new tree_nodeSerializable(iword,mean_i);
            else
                leftnode.insert(iword, mean_i);
        }
        else if(iword.compareTo(word)>0)
        {
            if(rightnode==null)
            {
                rightnode=new tree_nodeSerializable(iword,mean_i);
            }
            else
                rightnode.insert(iword, mean_i);
        }

    }

}
