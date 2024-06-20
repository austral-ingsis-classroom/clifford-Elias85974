package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;
import edu.austral.ingsis.clifford.filesystem.node.FileSystemNode;

import java.util.Optional;

public class CdCommand implements Command {
    @Override
    public String execute(FileSystem fileSystem, String argument) {
        DirectoryNode currentDirectory = fileSystem.getCurrent();
        if (argument.startsWith("/")) { // Starting from the root
            currentDirectory = fileSystem.getRoot();
            argument = argument.substring(1);
        } else if (argument.equals("..")) {
            Optional<DirectoryNode> parent = currentDirectory.getParent();
            if (parent.isPresent()) {
                fileSystem.setCurrent(parent.get());
                fileSystem.setCurrentPath(fileSystem.getCurrentPath().substring(0, fileSystem.getCurrentPath().lastIndexOf("/")));
                return "Moved to parent directory";
            } else {
                return "Already at root";
            }
        }
        Optional<FileSystemNode> child = currentDirectory.getChild(argument);
        if (child.isPresent() && child.get().isDirectory()) {
            fileSystem.setCurrent((DirectoryNode) child.get());
            fileSystem.setCurrentPath(fileSystem.getCurrentPath() + "/" + argument);
            return "Moved to directory: " + argument.substring(argument.lastIndexOf("/") + 1);
        } else {
            return "Directory not found";
        }
    }
}
