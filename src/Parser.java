import java.text.ParseException;

/**
 * Created by Azathoth on 4. 12. 2014.
 */
public class Parser {

    private String hexOutput;

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
                return 10000;
            case "SMALL":
                return 20000;
            case "WAIT":
                return 30000;
            case "RESTART":
                return 40000;
            default:
                if (sourceCodeLine.startsWith("DIR ")) {
                    return parseDir(sourceCodeLine);
                } else if (sourceCodeLine.startsWith("SPEED ")) {
                    return parseSpeed(sourceCodeLine);
                } else if (sourceCodeLine.startsWith("LIM ")) {
                    return parseLim(sourceCodeLine);
                }
                throw new ParseException("wrong command in line: "+sourceCodeLine, 0);
        }
    }

    private int parseDir(String input) throws ParseException {
        String arguments = input.substring("DIR ".length());
        String[] argumentsArray = arguments.split(", ");            //oddělovač argumentů je ", "
        if (argumentsArray.length != 2) {
            throw new ParseException("not two arguments", "DIR ".length());
        }
        int arg1 = Integer.parseInt(argumentsArray[0]);
        if (arg1 != 0 && arg1 != 1) {
            throw new ParseException("wrong first argument of DIR command, must be 0 or 1", input.indexOf(arg1));
        }
        int arg2 = Integer.parseInt(argumentsArray[1]);
        if (arg2 != 0 && arg2 != 1) {
            throw new ParseException("wrong second argument of DIR command, must be 0 or 1", input.indexOf(arg2));
        }
        System.out.println(arg1);
        System.out.println(arg2);
        return 50000+arg1*100+arg2;
    }

    private int parseSpeed(String input) throws ParseException {
        String arguments = input.substring("SPEED ".length());
        String[] argumentsArray = arguments.split(", ");            //oddělovač argumentů je ", "
        int arg1 = Integer.parseInt(argumentsArray[0]);
        if (arg1 < 1 || arg1 > 16) {
            throw new ParseException("wrong first argument of SPEED command, must be between 1 and 16", input.indexOf(arg1));
        }
        int arg2 = Integer.parseInt(argumentsArray[1]);
        if (arg2 < 1 || arg2 > 16) {
            throw new ParseException("wrong second argument of SPEED command, must be between 1 and 16", input.indexOf(arg2));
        }
        return 60000+arg1*100+arg2;
    }

    private int parseLim(String input) {
        String arguments = input.substring("LIM ".length());
        String[] argumentsArray = arguments.split(", ");            //oddělovač argumentů je ", "
        int arg1 = Integer.parseInt(argumentsArray[0]);
        return 70000;
    }

    private int[] getArguments(String stringArguments) {
        String[] argumentsArray = stringArguments.split(", ");            //oddělovač argumentů je ", "
        int[] arguments = new int[argumentsArray.length];
        for (int i = 0; i < argumentsArray.length; i++) {
            arguments[i] = Integer.parseInt(argumentsArray[i]);
        }
        return arguments;
    }


}
