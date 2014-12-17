public class Main {

    public static void main(String[] args) throws Exception {
        String sourceCodeName = "sourceCode.azathoth";
        String hexOutputName = "C:\\Users\\Azathoth\\Desktop\\spsProjects\\SPSVga2014\\VGA2.hex";
        FileHandler fileHandler = new FileHandler(sourceCodeName, hexOutputName);
        String[] inputLines = fileHandler.loadSourceCode();
        Parser parser = new Parser();
        int[] compiledCode = parser.parse(inputLines);
        for (int i = 1; i < compiledCode.length+1; i++) {
            System.out.println(i+": "+compiledCode[i-1]);
        }
        String[] hexOutput = parser.convertToHex(compiledCode);
        for (String s : hexOutput) {
            System.out.println(s);
        }
        fileHandler.saveHexOutput(compiledCode);
    }
}
