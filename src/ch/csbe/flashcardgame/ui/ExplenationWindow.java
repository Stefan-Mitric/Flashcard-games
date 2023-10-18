/**
 * The ExplenationWindow class represents a GUI window that provides instructions on how to set up the game.
 * The user can navigate back to the main window using a "BACK" button.
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
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea; 

	 
public class ExplenationWindow extends JFrame implements ActionListener {
	// Serialization ID for the class.
	private static final long serialVersionUID = 1L;
	// Singleton instance of the ExplenationWindow class.
	private static ExplenationWindow instance;
	// "BACK" button for the explanation window.
	private JButton backButton;
	// Pane to manage layered items like the button.
	private JLayeredPane buttonPane;
	  
	/**
	 * Singleton pattern to ensure only one instance of ExplenationWindow is created.
	 * 
	 * @return The existing instance of ExplenationWindow, or a new one if none exists.
	 */
	    
	 public static ExplenationWindow getInstance() {
		 // Singleton pattern implementation.
	        if (instance == null)  {
	            instance = new ExplenationWindow();
	        }

	        return instance;
	    }
	    /**
	     * Private constructor for the ExplenationWindow class.
	     * Initializes the UI components, sets the window size, and configures event listeners.
	     */
	    private ExplenationWindow() {
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

	        // Initializing the "BACK" button and adding it to the pane.
	        this.backButton = new JButton("BACK");
	        backButton.setBounds(350, 0, 110, 50);
	        backButton.addActionListener(this);
	        buttonPane.add(backButton);

	        // Adding the pane to the bottom of the window.
	        this.add(buttonPane, BorderLayout.PAGE_END);

	        // Initializing a text area, possibly to display more detailed instructions.
	       	JTextArea textArea = new JTextArea();
	        this.add(textArea);

	        // Initializing the main instruction label.
	        JLabel questionLabel = new JLabel("<html><br/>"
	        		+ "<br/>"
	        		+ "<br/>"
	        		+ "<br/>"
	        		+ "Here is the Explenation how to set up your game");
	        questionLabel.setHorizontalAlignment(JLabel.CENTER);
	        questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 30));
	        this.add(questionLabel, BorderLayout.NORTH);
	        
	        // Initializing the step-by-step instructions label.
	        JLabel explenationLabel = new JLabel("<html>1. Make a folder on your desktop and put your data (Text files) in it.<br/>"
	        		+ "2. Choose the Folder you wane use.<br/>"
	        		+ "3. After you choosed click yes if you ready or not.<br/>"
	        		+ "4. At the end you can see your score/time and rating. ");
	        explenationLabel.setHorizontalAlignment(JLabel.CENTER);
	        explenationLabel.setFont(explenationLabel.getFont().deriveFont(Font.BOLD, 17));
	        this.add(explenationLabel, BorderLayout.CENTER);
	    }
	    
	    /**
	     * Event handling method for the actions performed on the UI components of the ExplenationWindow.
	     * This method currently handles the click event for the "BACK" button.
	     * 
	     * @param event The triggered action event.
	     */
	    
		@Override

		public void actionPerformed(ActionEvent event) {
			// Handling the action when the "BACK" button is pressed.
			ExplenationWindow explenationWindow = ExplenationWindow.getInstance(); 
			explenationWindow.setVisible(false);
			MainWindow mainWindow = MainWindow.getInstance(); 
			mainWindow.setVisible(true); 

		}  
}
	
	 