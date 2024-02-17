package Parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseCommand {

    private String inputCommands;
    private String cmd, arg, by, from, to;

    public ParseCommand(String inputCommands){
        this.inputCommands = inputCommands ;
    }

    public String TodoCommand(){
        Pattern pattern = Pattern.compile("todo\\s(.*?)"); //argument
        Matcher matcher = pattern.matcher(inputCommands); //deadline when
        if(matcher.matches()){
           arg = matcher.group(1);
        }

        return arg;
    }

    public String [] DeadlineCommand(){
        Pattern pattern = Pattern.compile("deadline\\s(.*?)\\s/by\\s(.*?)");
        Matcher matcher = pattern.matcher(inputCommands);
        if(matcher.matches()){
            arg = matcher.group(1); //argument
            by = matcher.group(2); //deadline
        }

        return new String[]{arg, by};
    }

    public String [] EventCommand(){
        Pattern pattern = Pattern.compile("event\\s(.*?)\\s/from\\s(.*?)\\s/to\\s(.*?)");
        Matcher matcher = pattern.matcher(inputCommands);
        if(matcher.matches()){
            arg = matcher.group(1);
            from = matcher.group(2);
            to = matcher.group(3);
        }
        return new String[]{arg, from, to};
    }

}
