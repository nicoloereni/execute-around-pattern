import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ExecuteAroundTest {

    private ExecuteAround executeAround;

    private ClassLoader classLoader;

    @Before
    public void setUp() throws Exception {

        executeAround = new ExecuteAround();
        classLoader = getClass().getClassLoader();

    }

    @Test
    public void processFileMethodReturnOneLine() throws Exception {

        String result = executeAround.processFile((BufferedReader br) -> {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        });

        assertNotNull(result);
        assertEquals("a text file", result);

    }

    @Test
    public void processFileMethodReturnTwoLines() throws Exception {

        String result = executeAround.processFile((BufferedReader br) -> {
            try {
                return br.readLine() + br.readLine();
            } catch (IOException e) {
                return null;
            }
        });

        assertNotNull(result);
        assertEquals("a text filecontaing some rows", result);

    }

    @Test
    public void createFileReaderReturnProperResponse() throws Exception {

        FileReader fileReader = executeAround.createFileReader("text.txt");

        assertNotNull(fileReader);
        assertEquals("a text file", new BufferedReader(fileReader).readLine());
    }
}
