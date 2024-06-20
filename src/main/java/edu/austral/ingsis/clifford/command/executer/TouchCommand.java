package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;
import edu.austral.ingsis.clifford.filesystem.node.FileNode;

public class TouchCommand implements Command {
    @Override
    public String execute(FileSystem fileSystem, String argument) {
        DirectoryNode currentDirectory = fileSystem.getCurrent();
        if (argument.contains("/") || argument.contains(" ")) {
            return "Only file name allowed";
        }
        else {
            FileNode fileNode = new FileNode(argument);
            currentDirectory.addChild(fileNode);
            return argument + "file created";
        }
    }
}
