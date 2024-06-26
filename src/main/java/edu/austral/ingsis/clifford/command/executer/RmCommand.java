package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;
import edu.austral.ingsis.clifford.filesystem.node.FileSystemNode;
import java.util.Optional;

public class RmCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem, String argument) {
    DirectoryNode currentDirectory = fileSystem.getCurrent();
    if (argument.startsWith("--recursive")) {
      String childName = argument.substring(12);
      if (currentDirectory.hasChild(childName)) {
        currentDirectory.removeChild(childName);
        return "'" + childName + "' removed";
      }
    } else {
      Optional<FileSystemNode> possibleChild = currentDirectory.getChild(argument);
      if (possibleChild.isPresent()) {
        FileSystemNode child = possibleChild.get();
        if (child.isDirectory()) {
          return "cannot remove '" + argument + "', is a directory";
        }
        currentDirectory.removeChild(argument);
        return "'" + argument + "' removed";
      }
      return "Invalid argument";
    }
    return "File not found";
  }
}
