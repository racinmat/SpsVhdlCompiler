import java.text.ParseException;

/**
 * Created by Azathoth on 4. 12. 2014.
 *
 * parsing:
 * FS:          1
 * SMALL:       2
 * RESTART:     3
 * DIR:       1XY
 * WAIT:     1XXX
 * SPEED:   1XXYY
 * LIM:     2XXYY
 *  xx-1. argument, yy-2. argument
 */
public class Parser {

    public Parser(){
    }

    public int[] parse(String[] sourceCodeLines) throws ParseException {
        int[] output = new int[sourceCodeLines.length];
        for (int i = 0; i < output.length; i++) {
            output[i] = parseLine(sourceCodeLines[i]);
        }
        return output;
    }

    private int parseLine(String sourceCodeLine) throws ParseException {
        System.out.println(sourceCodeLine);
        switch (sourceCodeLine) {
            case "FS":
                return 1;
            case "SMALL":
                return 2;
            case "RESTART":
                return 3;
            default:
                if (sourceCodeLine.startsWith("WAIT ")) {
                    return parseWait(sourceCodeLine);
                } else if (sourceCodeLine.startsWith("DIR ")) {
                    return parseDir(sourceCodeLine);
                } else if (sourceCodeLine.startsWith("SPEED ")) {
                    return parseSpeed(sourceCodeLine);
                } else if (sourceCodeLine.startsWith("LIM ")) {
                    return parseLim(sourceCodeLine);
                }
                throw new ParseException("wrong command in line: "+sourceCodeLine, 0);
        }
    }

    private int parseWait(String input) throws ParseException {
        String arguments = input.substring("WAIT ".length());
        int[] argumentsArray = getArguments(arguments, 1);
        int arg1 = argumentsArray[0];
        if (arg1 < 0 || arg1 > 255) {
            throw new ParseException("wrong first argument of WAIT command, must be from zero to 255", input.indexOf(arg1));
        }
        return 1000+arg1;
    }

    private int parseDir(String input) throws ParseException {
        String arguments = input.substring("DIR ".length());
        int[] argumentsArray = getArguments(arguments, 2);
        int arg1 = argumentsArray[0];
        if (arg1 != 0 && arg1 != 1) {
            throw new ParseException("wrong first argument of DIR command, must be 0 or 1", input.indexOf(arg1));
        }
        int arg2 = argumentsArray[1];
        if (arg2 != 0 && arg2 != 1) {
            throw new ParseException("wrong second argument of DIR command, must be 0 or 1", input.indexOf(arg2));
        }
        return 100+arg1*10+arg2;
    }

    private int parseSpeed(String input) throws ParseException {
        String arguments = input.substring("SPEED ".length());
        int[] argumentsArray = getArguments(arguments, 2);
        int arg1 = argumentsArray[0];
        if (arg1 < 1 || arg1 > 15) {
            throw new ParseException("wrong first argument of SPEED command, must be between 1 and 15", input.indexOf(arg1));
        }
        int arg2 = argumentsArray[1];
        if (arg2 < 1 || arg2 > 15) {
            throw new ParseException("wrong second argument of SPEED command, must be between 1 and 15", input.indexOf(arg2));
        }
        return 10000+arg1*100+arg2;
    }

    private int parseLim(String input) throws ParseException {
        String arguments = input.substring("LIM ".length());
        int[] argumentsArray = getArguments(arguments, 2);
        int arg1 = argumentsArray[0];
        if (arg1 < 0 || arg1 > 15) {
            throw new ParseException("wrong first argument of LIM command, must be between 0 and 15", input.indexOf(arg1));
        }
        int arg2 = argumentsArray[1];
        if (arg2 < 0 || arg2 > 15) {
            throw new ParseException("wrong second argument of LIM command, must be between 0 and 15", input.indexOf(arg2));
        }
        return 20000+arg1*100+arg2;
    }

    private int[] getArguments(String stringArguments, int numberOfExpectedArguments) throws ParseException {
        String[] argumentsArray = stringArguments.split(", ");            //oddělovač argumentů je ", "
        int[] arguments = new int[argumentsArray.length];
        if (arguments.length != numberOfExpectedArguments) {
            throw new ParseException("wrong number of arguments, expected "+numberOfExpectedArguments+", got "+arguments.length+".", 0);
        }
        for (int i = 0; i < argumentsArray.length; i++) {
            arguments[i] = Integer.parseInt(argumentsArray[i]);
        }
        return arguments;
    }

    public String[] convertToHex(int[] compiledCode) {
        String[] hexOutput = new String[compiledCode.length];
        for (int i = 0; i < compiledCode.length; i++) {
            hexOutput[i] = Integer.toHexString(compiledCode[i]);
        }
        return hexOutput;
    }
}
