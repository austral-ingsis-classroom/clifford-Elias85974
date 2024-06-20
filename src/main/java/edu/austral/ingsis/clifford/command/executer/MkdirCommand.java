package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;

public class MkdirCommand implements Command {
    @Override
    public String execute(FileSystem fileSystem, String argument) {
        DirectoryNode currentDirectory = fileSystem.getCurrent();
        if (argument.contains("/") || argument.contains(" ")) {
            return "Only directory name allowed";
        }
        else {
            DirectoryNode directoryNode = new DirectoryNode(argument, currentDirectory);
            currentDirectory.addChild(directoryNode);
            return "'" + argument + "'" + " directory created";
        }
    }
}
