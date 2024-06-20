package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.command.executer.*;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public class CommandParser {
    private final FileSystem fileSystem;

    public CommandParser() {
        this.fileSystem = new FileSystem();
    }

    public String parse(String argument) {
        int endIndex = argument.indexOf(" ");
        if (endIndex == -1) {
            endIndex = argument.length();
        }
        String commandArgument = argument.substring(0, endIndex);
        Command command;
        switch (commandArgument) {
            case "ls":
                command = new LsCommand();
                break;
            case "cd":
                command = new CdCommand();
                break;
            case "touch":
                command = new TouchCommand();
                break;
            case "mkdir":
                command = new MkdirCommand();
                break;
            case "rm":
                command = new RmCommand();
                break;
            case "pwd":
                command = new PwdCommand();
                break;
            default:
                return "Command not found";
        }
        return command.execute(fileSystem, argument.substring(argument.indexOf(" ") + 1));
    }
}
