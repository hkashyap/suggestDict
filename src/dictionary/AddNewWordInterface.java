
package dictionary;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author d_red_horn
 */
class AddNewWordInterface extends JFrame{   //checked

    private String word, meaning;
    JTextArea wordTextArea, meaningTextArea;

    public AddNewWordInterface(){
        super(" Add New Word ");
        super.setLocation(150, 150);
        super.setSize(600, 500);
        super.setIconImage(new ImageIcon("Icon.jpg", "Icon").getImage());
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
        super.setResizable(false);

        //creating the menu bar...
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);

        //creating the file menu...
        JMenu File = new JMenu("File");
        File.setMnemonic('F');

        //Creating File submenus...
        JMenuItem Close = new JMenuItem("Close");
        Close.setMnemonic('C');
        Close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));  //setting the shortcut key use ctrl+c
        Close.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Close();
                    }
                }
        );
        //file menu ends...
        //adding the Close submenu to the file menu...
        File.add(Close);
        //adding the file menu to the menu bar...
        bar.add(File);

        //creating the help menu...
        JMenu Help = new JMenu("Help");
        Help.setMnemonic('H');

        //Creating Help submenus...
        JMenuItem helpOnAddNewWord = new JMenuItem("Help On Add New Word");
        helpOnAddNewWord.setMnemonic('e');
        helpOnAddNewWord.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        helpOnAddNewWord.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Help();
                    }
                }
        );
        //adding the credit submenu to the help menu...
        Help.add(helpOnAddNewWord);
        //help menu ends...
        //adding the Help menu to the menu bar...
        bar.add(Help);

        //Creating boxes...
        Box box1 = Box.createHorizontalBox();

        //setting the JTextField containing the word...
        JTextField wordTextField = new JTextField("  Word       ");
        wordTextField.setEditable(false);
        wordTextField.setBorder(null);
        box1.add(wordTextField);

        //Setting the text area for word input...
        wordTextArea = new JTextArea(2,45);
        box1.add(new JScrollPane(wordTextArea));
        add(box1);

        Box box2 = Box.createHorizontalBox();
        //setting the JTextField containing the meaning...
        JTextField meaningTextField = new JTextField("  Meaning  ");
        meaningTextField.setEditable(false);
        meaningTextField.setBorder(null);
        box2.add(meaningTextField);

        //Setting the text area for meaning input...
        meaningTextArea = new JTextArea(20,45);
        meaningTextArea.setWrapStyleWord(true);
        box2.add(new JScrollPane(meaningTextArea));
        add(box2);

        Box box3 = Box.createHorizontalBox();
        JButton OK = new JButton("  OK  ");
        OK.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    getValues();
                }
            }
        );
        box3.add(OK);

        JButton Cancel = new JButton("Cancel");
        Cancel.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Close();
                }
            }
        );
        box3.add(Cancel);

        JButton Clear = new JButton("Clear");
        Clear.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Clear();
                }
            }
        );
        box3.add(Clear);
        add(box3);

        setLayout(new FlowLayout());
    }

    private void Close() {  //checked
        super.dispose();
    }

    private void Help(){
        JOptionPane.showMessageDialog(rootPane, "\n Click on the Word box and put the word you want to enter in the dictionary. " +
                "\n Then click on the Meaning box and enter the proper meaning to the word.\n example--\n" +
                " Click in word box and enter a word say \t'word'. Click in the meaning box and enter\n" +
                " the meaning say\n 'Noun:\n 1. A unit of language that native speakers can identify.\n" +
                " 2. A brief statement." +
                "\n Verb:" +
                "\n 1. Put into words or an expression.'", "Help", JOptionPane.PLAIN_MESSAGE);
    }

    private void getValues(){
        //checking if empty
        if(wordTextArea.getText().equals(""))
            JOptionPane.showMessageDialog(rootPane, "The word part is empty!", "word empty", JOptionPane.WARNING_MESSAGE);
        else if(meaningTextArea.getText().equals(""))
            JOptionPane.showMessageDialog(rootPane, "The meaning part is empty!", "meaning empty", JOptionPane.WARNING_MESSAGE);
        else{
            if(JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to insert this word?", "Confermation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                word = WordSearch.trim(wordTextArea.getText());
                meaning = meaningTextArea.getText();
                updateSequentialFile usf = new updateSequentialFile(word, meaning);
                JOptionPane.showMessageDialog(rootPane, usf.getMessage(), "acknowledgement", usf.getMessageType());
                Close();
            }
        }
    }

    private void Clear(){
        word = "";
        meaning = "";
        wordTextArea.setText(word);
        meaningTextArea.setText(meaning);
        wordTextArea.updateUI();
        meaningTextArea.updateUI();
    }
}
