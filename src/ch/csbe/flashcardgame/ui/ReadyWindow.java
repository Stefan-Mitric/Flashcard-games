/**
 * The ReadyWindow class represents a GUI window that prompts the user to confirm if they are ready to start.
 * <p>
 * This window provides options for the user to either proceed with the questions or go back to the main window.
 * Additionally, a message is displayed asking if the user is ready.
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
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea; 

 

public class ReadyWindow extends JFrame implements ActionListener {
	// Serialization ID for the class
	private static final long serialVersionUID = 1L;
    // Singleton instance of the ReadyWindow class.
	private static ReadyWindow instance;
	// UI components for the window.
    private JButton readyButton;
    private JButton backButton;
    private JLayeredPane buttonPane;
 // Selected file to be used for questions.
	private File selectedFile;

	/**
     * Singleton pattern to ensure only one instance of ReadyWindow is created.
     * 
     * @return The existing instance of ReadyWindow, or a new one if none exists.
     */
	
    public static ReadyWindow getInstance() {
        // Check if an instance already exists, if not, create one.
        if (instance == null)  {
            instance = new ReadyWindow();
        }

        return instance;
    }

    /**
     * Private constructor for the ReadyWindow class.
     * Initializes the UI components, sets the window size, and configures event listeners.
     */
    
    private ReadyWindow() {
    	// Set the window size.
        this.setSize(825, 800);
        
        // Add key listener to the entire window
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	 // If ESC key is pressed, exit the application.
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        this.setFocusable(true);

        // Initialize the layered pane for UI elements.
        buttonPane = new JLayeredPane();
        buttonPane.setPreferredSize(new Dimension(300, 100));

        // Initialize and set properties for the "YES" button.
        this.readyButton = new JButton("YES");
        readyButton.setBounds(150, 0, 110, 50);
        readyButton.addActionListener(this);
        buttonPane.add(readyButton);
        
        // Initialize and set properties for the "NO" button.
        this.backButton = new JButton("NO");
        backButton.setBounds(550, 0, 110, 50);
        backButton.addActionListener(this);
        buttonPane.add(backButton);

        // Add the layered pane to the main window.
        this.add(buttonPane, BorderLayout.PAGE_END);

        // Initialize text area.
        JTextArea textArea = new JTextArea();
        this.add(textArea);

        // Initialize and set properties for the label displaying the question.
        JLabel questionLabel = new JLabel("Are You ready!");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 30));
        this.add(questionLabel, BorderLayout.CENTER);
    }

    /**
     * Event handling method for the actions performed on the UI components of the ReadyWindow.
     * Handles the actions for the "YES" and "NO" buttons.
     * 
     * @param event The triggered action event.
     */
    
    @Override
    public void actionPerformed(ActionEvent event) {
        // Handle action when "YES" button is clicked.
        if (event.getSource() == this.readyButton) {
            ReadyWindow readyWindow = ReadyWindow.getInstance(); 
            readyWindow.setVisible(false);
            QuestionWindow questionWindow = QuestionWindow.getInstance(); 
            questionWindow.setSelectedFolder(this.selectedFile);
            questionWindow.setVisible(true); 
        } 
        // Handle action when "NO" button is clicked.
        else if (event.getSource() == this.backButton) {
            ReadyWindow readyWindow = ReadyWindow.getInstance(); 
            readyWindow.setVisible(false);
            MainWindow mainWindow = MainWindow.getInstance();
            mainWindow.setVisible(true);
		 }  
	}

	 /**
     * Sets the selected folder from which the text files will be loaded for questions.
     * 
     * @param selectedFile The folder containing text files.
     */
	
	public void setSelectedFolder(File selectedFile) {
		// Set the selected file.
		this.selectedFile = selectedFile;
	
	}
}

 