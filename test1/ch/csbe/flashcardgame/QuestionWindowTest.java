package ch.csbe.flashcardgame;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import ch.csbe.flashcardgame.ui.QuestionWindow;

public class QuestionWindowTest {

    private QuestionWindow questionWindow;
    private File folder;
    

    @Before
    public void setUp() throws Exception {
        questionWindow = QuestionWindow.getInstance();
        folder = new File("C:\\Users\\Administrator\\Desktop\\asa");
    }

    @Test
    public void testLoadFilesFromFolder() {
        questionWindow.setSelectedFolder(folder);
        
        // Angenommen, es gibt 10 Dateien in Ihrem Ordner. Ändern Sie die Zahl entsprechend.
        int expectedFileCount = 3; 

        assertNotNull(questionWindow.getListOfFiles());
        assertEquals(expectedFileCount, questionWindow.getListOfFiles().length);
    }
}
