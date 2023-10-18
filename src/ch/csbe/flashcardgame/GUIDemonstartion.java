/**
 * The GUIDemonstartion class serves as the entry point for the GUI demonstration application.
 * <p>
 * This class initializes the look and feel of the user interface to match the system's look and feel.
 * Once initialized, it launches the main application window.
 * </p>
 * 
 * @author Stefan Mitric
 * @version 1.0
 * @since 17.07.2023
 */
package ch.csbe.flashcardgame;

import javax.swing.UIManager; 
import javax.swing.UnsupportedLookAndFeelException; 
import ch.csbe.flashcardgame.ui.MainWindow;

public class GUIDemonstartion { 

    /**
     * The main method for the GUIDemonstartion class.
     * <p>
     * This method attempts to set the look and feel of the UI to the system default.
     * If unsuccessful, it prints the stack trace of the exception.
     * After setting the look and feel, it initializes and displays the main application window.
     * </p>
     * 
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) { 
        
        // Attempt to set the system's look and feel for the application.
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } 
        // Catch any exceptions that might occur while setting the look and feel.
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException event) { 
            // Print the exception stack trace for debugging purposes.
            event.printStackTrace(); 
        } 

        // Create or retrieve the main application window instance.
        MainWindow mainWindow = MainWindow.getInstance(); 
        
        // Make the main application window visible to the user.
        mainWindow.setVisible(true); 
    } 
}
