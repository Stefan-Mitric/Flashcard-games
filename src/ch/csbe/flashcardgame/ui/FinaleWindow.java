/**
 * The FinaleWindow class represents the final GUI window that appears at the end of a game session.
 * <p>
 * This window provides options for the user to either retry, return to the home screen, or quit the game.
 * Additionally, a motivational message is displayed to the user.
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
import javax.swing.*;

public class FinaleWindow extends JFrame implements ActionListener {
	// Serialization ID for the class.
	private static final long serialVersionUID = 1L;
    // Singleton instance of the FinaleWindow class.
	private static FinaleWindow instance;
	// Action buttons for the finale window.
    private JButton homeButton;
    private JButton retryButton;
    private JButton quitButton;
    // Pane to manage layered items like buttons.
    private JLayeredPane buttonPane;
    

    /**
     * Singleton pattern to ensure only one instance of FinaleWindow is created.
     * 
     * @return The existing instance of FinaleWindow, or a new one if none exists.
     */
    
    public static FinaleWindow getInstance() {
    	 // Singleton pattern implementation.
        if (instance == null)  {
            instance = new FinaleWindow();
        }

        return instance;

    }
 
    /**
     * Private constructor for the FinaleWindow class.
     * Initializes the UI components, sets the window size, and configures event listeners.
     */
    
    FinaleWindow() {
    	// Setting the window size.
        this.setSize(825, 800);
        // Allowing the window to be focusable.
        this.setFocusable(true);
        
        // Add key listener to close the application when ESC is pressed.
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
      

        // Initializing the layered pane to hold UI components.
        buttonPane = new JLayeredPane();
        buttonPane.setPreferredSize(new Dimension(300, 100));


        this.retryButton = new JButton("RETRY");
        // Initializing the "RETRY" button and adding it to the pane.
        retryButton.setBounds(100, 0, 110, 50);
        retryButton.addActionListener(this);
        buttonPane.add(retryButton);

        this.homeButton = new JButton("HOME");
        // Initializing the "HOME" button and adding it to the pane.
        homeButton.setBounds(350, 0, 110, 50);
        homeButton.addActionListener(this);
        buttonPane.add(homeButton);

        this.quitButton = new JButton("QUIT");
        // Initializing the "QUIT" button and adding it to the pane.
        quitButton.setBounds(600, 0, 110, 50);
        quitButton.addActionListener(this);
        buttonPane.add(quitButton);

        this.add(buttonPane, BorderLayout.PAGE_END);
        // Adding the pane to the bottom of the window.
        
        // Initializing the label with a motivational message.
        JLabel questionLabel = new JLabel("ÜBUNG MACHT DEN MEISTER");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 20));
        this.add(questionLabel);

    }
    
    /**
     * Event handling method for the actions performed on the UI components of the FinaleWindow.
     * Handles the actions for the "HOME", "RETRY", and "QUIT" buttons.
     * 
     * @param event The triggered action event.
     */
    
    public void actionPerformed(ActionEvent event) {
    	  // Handling the actions for the "HOME", "RETRY", and "QUIT" buttons.
        if (event.getSource() == this.homeButton) {
        	FinaleWindow finaleWindow = FinaleWindow.getInstance();
        	finaleWindow.setVisible(false);
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.setVisible(true);
           
        } if (event.getSource() == this.retryButton) {
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    		int option = fileChooser.showOpenDialog(this);
    		if (option == JFileChooser.APPROVE_OPTION) {
    			ReadyWindow readyWindow = ReadyWindow.getInstance();
    			readyWindow.setSelectedFolder(fileChooser.getSelectedFile());
    			readyWindow.setVisible(true);
    			this.setVisible(false);    	
    		}
        	}	else if (event.getSource() == this.quitButton) {
        			System.exit(0); // This will close the program
    		}	
    	}
    }
