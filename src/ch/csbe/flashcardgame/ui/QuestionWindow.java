/**
 * The QuestionWindow class provides a user interface for presenting questions to the user.
 * <p>
 * It displays content from text files and allows the user to answer. Once answered, the user can 
 * check if their answer matches the file name (without extension). It provides buttons to navigate back 
 * to the main window, check answers, and finish the current session.
 * </p>
 * 
 * @author Stefan Mitric
 * @version 1.0
 * @since 17.07.2023
 */

package ch.csbe.flashcardgame.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuestionWindow extends JFrame implements ActionListener {
	// Serialization ID for the class.
    private static final long serialVersionUID = -8429055363017236971L;
    // Singleton instance of the QuestionWindow class.
    private static QuestionWindow instance;
    // Array containing all the text files for questions.
    private File[] listOfFiles;
    // Label to display the media content.
    private JLabel mediaContent;
    // Navigation and action buttons.
    private JButton backButton;
    private JButton checkButton;
    // Text field for user's answer.
    private JTextField textField;
    // Name of the current file (without extension) serving as the answer.
    private String currentFileName;
    // Index of the current file being shown.
    private int currentFileIndex = 0;
    // Button to finish the session.
	private JButton finishButton;
    
	 // Constants for font styling which can be modified.
    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE = 20;

    /**
     * Singleton pattern to ensure only one instance of QuestionWindow is created.
     * 
     * @return The existing instance of QuestionWindow, or a new one if none exists.
     */
    
    public static QuestionWindow getInstance() {
        if (instance == null) {
            instance = new QuestionWindow();
        }
        return instance;
    }

    /**
     * Private constructor for the QuestionWindow class.
     * Initializes the UI components, sets the window size, and configures event listeners.
     */
    
    private QuestionWindow() {
        this.setSize(825, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize and set properties for the media content label.
        this.mediaContent = new JLabel();
        this.mediaContent.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        this.add(mediaContent, BorderLayout.CENTER);

        // Add key listener to the entire window
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        this.setFocusable(true);

        // Top Container for navigation.
        JPanel topContainer = new JPanel();
        topContainer.setLayout(new BorderLayout());
        this.add(topContainer, BorderLayout.PAGE_START);

        this.backButton = new JButton("<");
        this.backButton.addActionListener(this);
        topContainer.add(this.backButton, BorderLayout.LINE_START);

        // Bottom Center container for user actions.
        JPanel bottomContainer = new JPanel();
        bottomContainer.setLayout(new BorderLayout());
        this.add(bottomContainer, BorderLayout.PAGE_END);

        this.checkButton = new JButton("Check");
        this.checkButton.addActionListener(this);
        bottomContainer.add(checkButton, BorderLayout.LINE_END);
        
        this.finishButton = new JButton ("Done");
        this.finishButton.addActionListener(this);
        bottomContainer.add(finishButton, BorderLayout.LINE_START);

        this.textField = new JTextField();
        this.textField.addActionListener(this);
        bottomContainer.add(textField, BorderLayout.CENTER);
    }
    
    /**
     * Loads the next media content from the text files and displays it.
     * If no more files are left, transitions to the finale window.
     */
    
    private void loadNextMedia() {
        // ... [Load the next question from the file and display]
        if (listOfFiles != null && currentFileIndex < listOfFiles.length) {
            File file = listOfFiles[currentFileIndex++];
            String fileName = file.getName().toLowerCase();

            // Get the file name without extension
            int pos = fileName.lastIndexOf(".");
            if (pos > 0) {
                currentFileName = fileName.substring(0, pos);
            }

            if (fileName.endsWith(".txt")) {
                try {
                    String content = new String(Files.readAllBytes(file.toPath()));
                    mediaContent.setText("<html>" + content + "</html>");
                } catch (IOException event) {
                    event.printStackTrace();
                }
            }
        
        } else {
            FinaleWindow finaleWindow = new FinaleWindow();
            finaleWindow.setVisible(true);
            this.setVisible(false);
        }
    }

    /**
     * Sets the selected folder from which the text files will be loaded for questions.
     * 
     * @param selectedFolder The folder containing text files.
     */
    
    public void setSelectedFolder(File selectedFolder) {
        // ... [Set the selected folder and load the first question]
        listOfFiles = selectedFolder.listFiles(file -> file.getName().toLowerCase().endsWith(".txt"));
        currentFileIndex = 0;

        // Debugging: Zeige die Anzahl der Dateien an
        System.out.println("Anzahl der Dateien im Ordner: " + (listOfFiles != null ? listOfFiles.length : 0));
        loadNextMedia();
    }

    /**
     * Event handling method for the actions performed on the UI components of the QuestionWindow.
     * Handles the actions for the "HOW TO DO", "START", and "QUIT" buttons.
     * 
     * @param event The triggered action event.
     */
    
    @Override
    public void actionPerformed(ActionEvent event) {
    	// ... [Handle user actions based on button pressed]
        if (event.getSource() == this.backButton) {
            this.setVisible(false); // Schließt das aktuelle Fenster (QuestionWindow)
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.setVisible(true);
            
        }
            else if (event.getSource() == this.finishButton) {
                this.setVisible(false); // Schließt das aktuelle Fenster (QuestionWindow)
                FinaleWindow finaleWindow = FinaleWindow.getInstance();
                finaleWindow.setVisible(true);
                
        } else if (event.getSource() == this.checkButton || event.getSource() == this.textField) {
            String enteredName = this.textField.getText().trim();
            if (enteredName.equalsIgnoreCase(currentFileName)) {
                System.out.println("Richtig!");
                loadNextMedia();
            } else {
                System.out.println("Falsch!");
            }
        }
    }

    public File[] getListOfFiles() {
        return listOfFiles;
    }

}
    
    

