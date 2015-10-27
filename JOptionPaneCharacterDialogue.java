
/**
 * Write a description of class JOptionPaneCharacterDialogue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class JOptionPaneCharacterDialogue
{
    public static void main(String[] args) {
    JDialog.setDefaultLookAndFeelDecorated(true);
    Object[] selectionValues = { "Where the hell am I?!", "Who are you?", "You're making a mistake" };
    String initialSelection = "Who are you?";
    Object selection = JOptionPane.showInputDialog(null, "Oh, wow so you're finally awake?",
        "Game Intro", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
    System.out.println(selection);
  }
}
