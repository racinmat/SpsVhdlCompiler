import java.io.*;
import java.util.ArrayList;
import java.util.List;
import hades.utils.IntelHexFile;

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

    public void saveHexOutput(int[] hexOutput) throws Exception {
        IntelHexFile hexFile = new IntelHexFile();

//        long[][] hexLongOutput = new long[hexOutput.length][1];
//        for (int i = 0; i < hexOutput.length; i++) {
//            hexLongOutput[i][0] = hexOutput[i];
//        }
//        for (long[] longs : hexLongOutput) {
//            writeLineToFile(hexOutputName, longs);
//        }

        long[] hexLongOutput = new long[hexOutput.length];
        for (int i = 0; i < hexOutput.length; i++) {
            hexLongOutput[i] = hexOutput[i];
        }
        hexLongOutput = changePairs(hexLongOutput);
        File file = new File(hexOutputName);
        PrintWriter outputFile;
        if (!file.exists()) {
            outputFile = new PrintWriter(hexOutputName);
        } else {
            outputFile = new PrintWriter(new BufferedWriter(new FileWriter(hexOutputName, false)));
        }
        hexFile.writeHexFile16Bit(outputFile, hexLongOutput);
        outputFile.close();


    }

    private void writeLineToFile(String fileName, long[] content) throws Exception {
        IntelHexFile hexFile = new IntelHexFile();
        File file = new File(fileName);
        PrintWriter outputFile;
        if (!file.exists()) {
            outputFile = new PrintWriter(fileName);
        } else {
            removeLastLine(fileName);
            outputFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
        }
        hexFile.writeHexFile16Bit(outputFile, content);
        outputFile.close();
    }

    public void removeLastLine(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> content = new ArrayList<>();
        do {
            content.add(reader.readLine());
        } while (reader.ready());

        content.remove(content.size()-1);   //odebrat poslední prvek

        PrintWriter outputFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, false)));
        for (String s : content) {
            outputFile.println(s);
        }
        outputFile.close();
    }

    public long[] changePairs(long[] input) {
        long[] output = new long[input.length];
        for (int i = 0; i < input.length; i++) {
            String textForm = Long.toHexString(input[i]);
            if (textForm.length() == 3) {
                textForm = "0"+textForm;
            }
            if (textForm.length() == 2) {
                textForm = "00"+textForm;
            }
            if (textForm.length() == 1) {
                textForm = "000"+textForm;
            }
            System.out.println(textForm);
            textForm = textForm.substring(2, 4)+textForm.substring(0, 2);
            System.out.println(textForm);
            long temp = Long.decode("0x"+textForm);
            output[i] = temp;
//            System.out.println(input[i]);
//            System.out.println(output[i]);
        }
        return output;
    }
}
