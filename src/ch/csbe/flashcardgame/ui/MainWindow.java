/**
 * The MainWindow class represents the primary user interface window for the game.
 * <p>
 * This window provides options to the user to either start the game, learn how to set it up, or quit.
 * It is the first window users will interact with when launching the application.
 * </p>
 * 
 * @author Stefan Mitric
 * @version 1.0
 * @since 17.07.2023
 */

package ch.csbe.flashcardgame.ui; 

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ch.csbe.flashcardgame.ui.MainWindow; 

public class MainWindow extends JFrame implements ActionListener {
	// Serialization ID for the class.
    private static final long serialVersionUID = 6575190792282698563L;
    // Singleton instance of the MainWindow class.
    private static MainWindow instance;
    // Buttons for user actions in the main window.
    private JButton beginButton;
    private JButton startButton;
    private JButton quitButton;
    // Pane to manage layered items like buttons.
    private JLayeredPane buttonPane;

    /**
     * Singleton pattern to ensure only one instance of MainWindow is created.
     * 
     * @return The existing instance of MainWindow, or a new one if none exists.
     */
    
    public static MainWindow getInstance() {
    	// Singleton pattern implementation.
        if (instance == null)  {
            instance = new MainWindow();
        }

        return instance;
    }

    /**
     * Private constructor for the MainWindow class.
     * Initializes the UI components, sets the window size, and configures event listeners.
     */
    
    private MainWindow() {
    	// Setting the window size.
        this.setSize(825, 800);
        
        
        // Add key listener to close the application when ESC is pressed.
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        // Allowing the window to be focusable.
        this.setFocusable(true);

        // Initializing the layered pane to hold UI components.
        buttonPane = new JLayeredPane();
        buttonPane.setPreferredSize(new Dimension(300, 100));

        // Initializing the "HOW TO DO" button and adding it to the pane.
        this.beginButton = new JButton("HOW TO DO");
        beginButton.setBounds(100, 0, 110, 50);
        beginButton.addActionListener(this);
        buttonPane.add(beginButton);

        // Initializing the "START" button and adding it to the pane.
        this.startButton = new JButton("START");
        startButton.setBounds(350, 0, 110, 50);
        startButton.addActionListener(this);
        buttonPane.add(startButton);

        // Initializing the "QUIT" button and adding it to the pane.
        this.quitButton = new JButton("QUIT");
        quitButton.setBounds(600, 0, 110, 50);
        quitButton.addActionListener(this);
        buttonPane.add(quitButton);

        // Adding the pane to the bottom of the window.
        this.add(buttonPane, BorderLayout.PAGE_END);

        // Initializing the label that prompts the user to start the game.
        JLabel questionLabel = new JLabel("Press START to select your folder!");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 20));
        this.add(questionLabel, BorderLayout.CENTER);
    }

    /**
     * Event handling method for the actions performed on the UI components of the MainWindow.
     * Handles the actions for the "HOW TO DO", "START", and "QUIT" buttons.
     * 
     * @param event The triggered action event.
     */
    
    public void actionPerformed(ActionEvent event) {
    	// Handling the action when the "HOW TO DO" button is pressed.
        if (event.getSource() == this.beginButton) {
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.setVisible(false);
            ExplenationWindow explenationWindow = ExplenationWindow.getInstance();
            explenationWindow.setVisible(true);
            
            // Handling the action when the "START" button is pressed.
        } else if (event.getSource() == this.startButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
            	ReadyWindow readyWindow = ReadyWindow.getInstance();
            	readyWindow.setSelectedFolder(fileChooser.getSelectedFile());
            	readyWindow.setVisible(true);
                this.setVisible(false);
            }
            // Handling the action when the "QUIT" button is pressed.
            } else if (event.getSource() == this.quitButton) {
            	System.exit(0); // This will close the program
        }
    }
}