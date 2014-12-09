public class Main {

    public static void main(String[] args) throws Exception {
        String sourceCodeName = "sourceCode.azathoth";
        String hexOutputName = "compiledCode.hex";
        FileHandler fileHandler = new FileHandler(sourceCodeName, hexOutputName);
        String[] inputLines = fileHandler.loadSourceCode();
        Parser parser = new Parser();
        int[] compiledCode = parser.parse(inputLines);
        for (int i : compiledCode) {
            System.out.println(i);
        }
        String[] hexOutput = parser.convertToHex(compiledCode);
        for (String s : hexOutput) {
            System.out.println(s);
        }
        fileHandler.saveHexOutput(compiledCode);
    }
}
