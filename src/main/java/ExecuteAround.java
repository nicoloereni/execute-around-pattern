import java.io.*;
import java.util.function.Function;

public class ExecuteAround {

    public static final String TEXT_TXT = "text.txt";

    public String processFile(Function<BufferedReader, String> bufferedReaderProcessor) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(createFileReader(TEXT_TXT))){
           return bufferedReaderProcessor.apply(bufferedReader);
        }
    }

    FileReader createFileReader(String fileName) throws FileNotFoundException {
        return new FileReader(
                new File(
                        getClass().getClassLoader().getResource(fileName).getFile()));
    }
}
