import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        String sourceCodeName = "sourceCode.azathoth";
        String hexOutputName = "";
        FileHandler fileHandler = new FileHandler(sourceCodeName, hexOutputName);
        String[] inputLines = fileHandler.loadSourceCode();
        Parser parser = new Parser();
        parser.parse(inputLines);
    }


}
