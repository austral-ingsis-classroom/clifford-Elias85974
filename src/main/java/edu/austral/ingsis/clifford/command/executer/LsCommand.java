package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;

import java.util.Comparator;
import java.util.Set;

public class LsCommand implements Command {
    @Override
    public String execute(FileSystem fileSystem, String argument) {
        DirectoryNode currentDirectory = fileSystem.getCurrent();
        StringBuilder result = new StringBuilder();
        if (currentDirectory.isDirectory()) {
            Set<String> children = currentDirectory.getChildren().keySet();
            if (argument.startsWith("--ord")) {
                if (argument.endsWith("=asc")) {
                    for (String child : children.stream().sorted().toList()) {
                        result.append(child);
                    }
                } else if (argument.endsWith("=desc")) {
                    for (String child : children.stream().sorted(Comparator.reverseOrder()).toList()) {
                        result.append(child);
                    }
                } else {
                    return "Invalid argument";
                }
            }
            else {
                // Creation date order
                for (String child : children) {
                    result.append(child);
                }
            }
            return result.toString();
        } else {
            return "Not a directory";
        }
    }
}
