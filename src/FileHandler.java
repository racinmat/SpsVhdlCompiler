import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azathoth on 4. 12. 2014.
 */
public class FileHandler {

    private String sourceCodeName;
    private String hexOutputName;

    public FileHandler(String sourceCodeName, String hexOutputName) {
        this.sourceCodeName = sourceCodeName;
        this.hexOutputName = hexOutputName;
    }

    public String[] loadSourceCode() throws IOException {
        List<String> output = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(this.sourceCodeName));
        String line;
        while ((line = br.readLine()) != null) {
            output.add(line);
        }
        String[] outputArray = new String[output.size()];
        for (int i = 0; i < outputArray.length; i++) {
            outputArray[i] = output.get(i);
        }
        return outputArray;
    }

    public void saveHexOutput(int[] hexOutput) {

    }
}
