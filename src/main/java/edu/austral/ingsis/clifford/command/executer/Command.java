package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.FileSystemNode;

public interface Command {
    String execute(FileSystem fileSystem, String argument);
}
