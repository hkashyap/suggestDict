
package dictionary;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author d_red_horn
 */
public class initializer extends JFrame {
    String suggestions[];// = {"abc", "def", "ijk", "lmn", "opq                                     "};
    String searchedWord;
    JTextArea textArea;
    JTextField lookUpBox;
    JList suggestionBox;
    Box box1;
    SearchWord searchWord;
    public static Dictionary dict = new Dictionary();

    public initializer() {
        //initializing the JFrame...
        super(" Dictonary ");
        super.setLocation(100, 100);
        super.setSize(700, 600);
        super.setIconImage(new ImageIcon("Icon.jpg", "Icon").getImage());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.setResizable(false);

        //allocating memory for suggestion string...
        suggestions = new String[5];
        suggestions[4] = "                                                                    ";    //just to set size to kno it just make it comment and see effect in suggestion box.


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

        //creating the edit menu...
        JMenu Edit = new JMenu("Edit");
        Edit.setMnemonic('E');

        //Creating Edit submenus...
        JMenuItem addNewWord = new JMenuItem("Add a new word");
        addNewWord.setMnemonic('A');
        addNewWord.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        addNewWord.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        AddNewWordInterface anwi = new AddNewWordInterface();
                    }
                }
        );
        //edit menu ends...
        //adding the addNewWord submenu to the edit menu...
        Edit.add(addNewWord);
        //adding the Edit menu to the menu bar...
        bar.add(Edit);

        //creating the help menu...
        JMenu Help = new JMenu("Help");
        Help.setMnemonic('H');

        //Creating Help submenus...
        JMenuItem credit = new JMenuItem("Credit");
        credit.setMnemonic('r');
        credit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        credit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        credit();
                    }
                }
        );
        //adding the credit submenu to the help menu...
        Help.add(credit);

        JMenuItem about = new JMenuItem("About");
        about.setMnemonic('b');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
        about.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        about();
                    }
                }
        );
        //adding the about submenu to the help menu...
        Help.add(about);
        //help menu ends...
        //adding the Help menu to the menu bar...
        bar.add(Help);

        box1 = Box.createHorizontalBox();
        //setting the JTextField containing the loockup word...
        JTextField lookUp = new JTextField(" Look up ");
        lookUp.setEditable(false);
        lookUp.setBorder(null);
        box1.add(lookUp);

        //lookup box for entering the word..
        lookUpBox = new JTextField();
        lookUpBox.setColumns(20);
        lookUpBox.setEditable(true);    //use textarea and JList
        lookUpBox.addKeyListener(
                new KeyListener(){
                    public void keyTyped(KeyEvent event) {
                        searchedWord = lookUpBox.getText()+event.getKeyChar();
                        //System.out.println("yes "+searchedWord);
                        searchedWord = WordSearch.trim(searchedWord);
                        if(searchedWord.length() >= 1){
                            //System.out.println(searchedWord);
                            WordSearch wordSearch = new WordSearch(searchedWord);
                            for(int i=0; i<5; i++)
                                suggestions[i] = wordSearch.getWordSuggestion(i);
                            updateSuggestionBox();
                        }
                    }
                    public void keyPressed(KeyEvent event) {}
                    public void keyReleased(KeyEvent event) {}
                }
        );
        box1.add(lookUpBox);

        JTextField textField1 = new JTextField(" Suggestions");
        textField1.setEditable(false);
        textField1.setBorder(null);
        box1.add(textField1);

        //setting the suggestion box...
        suggestionBox = new JList(suggestions);
        suggestionBox.setVisibleRowCount(1);
        suggestionBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionBox.addListSelectionListener(
                new ListSelectionListener(){
                    public void valueChanged(ListSelectionEvent event){
                        updateLookUpBox();
                    }
        });
        box1.add(new JScrollPane(suggestionBox));
        
        
        JButton button = new JButton("Search");
        button.addActionListener( new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        //System.out.println(lookUpBox.getText());
                        search();
                    }
                }
        );
        box1.add(button);
        add(box1);

        //just for creating the distance between box1 and box 2 ie the top bar and the meaninig area...
        Box box = Box.createHorizontalBox();
        JTextField textField = new JTextField(80);
        textField.setEditable(false);
        textField.setBorder(null);
        box.add(textField);
        add(box);

        //for meaning field...
        Box box2 = Box.createHorizontalBox();
        textArea = new JTextArea(28, 55);
        textArea.setEditable(false);
        box2.add(new JScrollPane(textArea));
        add(box2);
        
        setLayout(new FlowLayout());

    }

    //function to close the window.
    private void Close() {  //checked
        super.dispose();
        System.exit(0);
    }

    private void credit(){
        JOptionPane.showMessageDialog(rootPane, "This programme is build by Ujjal Sharma (CSB08006),\nHirak Jyoti Kashyap " +
                "(CSB08007) and Simanta Bordoloi (CSB08014)\nas the PPL term project. Date of submittion 15/11/" +
                "2010.","Credit",JOptionPane.PLAIN_MESSAGE);
    }

    private void about(){
        JOptionPane.showMessageDialog(rootPane, "This programme is a word suggesting dictonary you insert a\ncharacter and this" +
                " program shows five possible words\ninitiating with the string or character and on pressing the\n'search' button" +
                " it displays the meaning in the text area below\nand if the word is absent then it gives a link to google " +
                "dictonary." ,"About",JOptionPane.PLAIN_MESSAGE);
    }

    private void search(){
        if(!lookUpBox.getText().equals("")){
            //System.out.println("123 "+lookUpBox.getText());
            searchWord = new SearchWord(lookUpBox.getText());
            displayMeaning(searchWord.getFlag());
        }
    }

    private void displayMeaning(int flag){
        if(flag == 0)
            textArea.setText(searchWord.getMeaning());
        else if (flag == 1){
            textArea.setText(" Word not found.\n Try following link.\n");
            //give link
        }
        else
            textArea.setText(" An unexpected error orroured!!!");
        textArea.updateUI();
    }

    private void updateSuggestionBox(){
        //for(int i=0;i<5;i++)
        //    suggestions[i] = searchedWord+i;
        suggestionBox.setListData(suggestions);
        suggestionBox.updateUI();
        suggestionBox.setSelectedIndex(7);
    }

    private void updateLookUpBox(){
        if(suggestionBox.getSelectedValue() != null){
            searchedWord = String.format("%s",suggestionBox.getSelectedValue());
            lookUpBox.setText(searchedWord);
            lookUpBox.updateUI();
            //System.out.println("NO "+searchedWord);
        }
    }
}
