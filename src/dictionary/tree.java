/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dictionary;

/**
 *
 * @author HiRock'z
 */


public class tree {

public tree_nodeSerializable root;
String str[]=new String[5];
    int index;
    int pos=-1;
    boolean flag=false;

public tree(){
    root=null;
}
public void insert(String iword,int mean_i)
{
    if(root==null)
        root=new tree_nodeSerializable(iword,mean_i);
    else
        root.insert(iword, mean_i);
}
public int search(String iword)
{
    pos=-1;
    searchInorder(root,iword);
    return(pos);
}

public String[] suggest(String iword){
    index=0;
    inorderTraverse(root,iword);
    return(str);
}

private void inorderTraverse(tree_nodeSerializable n,String iword)
{
    if(n==null)
        return;
    inorderTraverse(n.leftnode,iword);
    if(n.word.toLowerCase().startsWith(iword))
        str[index++]=n.word;
    if(index==5)
        return;
    inorderTraverse(n.rightnode,iword);

}

private void searchInorder(tree_nodeSerializable n,String iword) {
    if(n==null)
        return;
    searchInorder(n.leftnode,iword);
    if(n.word.compareToIgnoreCase(iword)==0)
    {
        pos=n.mean_index;
        flag=true;
    }
    if(flag)
        return;
    searchInorder(n.rightnode,iword);
}
}
