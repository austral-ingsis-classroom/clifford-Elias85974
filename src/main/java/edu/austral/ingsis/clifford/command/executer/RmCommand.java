package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;

public class RmCommand implements Command {
    @Override
    public String execute(FileSystem fileSystem, String argument) {
        DirectoryNode currentDirectory = fileSystem.getCurrent();
        if (argument.startsWith("--recursive")) {
            if (currentDirectory.getChildren().containsKey(argument)) {
                currentDirectory.removeChild(argument);
                return argument + " removed";
            }
            else {
                return "File not found";
            }
        }
        else {
            return "Invalid argument";
        }
    }
}
