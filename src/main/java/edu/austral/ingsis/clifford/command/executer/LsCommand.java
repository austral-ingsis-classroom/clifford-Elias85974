package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;
import edu.austral.ingsis.clifford.filesystem.node.FileSystemNode;
import java.util.Comparator;
import java.util.List;

public class LsCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem, String argument) {
    DirectoryNode currentDirectory = fileSystem.getCurrent();
    StringBuilder result = new StringBuilder();
    if (currentDirectory.isDirectory()) {
      // Sorting the children as needed
      List<FileSystemNode> children = currentDirectory.getChildren();
      if (argument.startsWith("--ord")) {
        if (argument.endsWith("=asc")) {
          children.sort(Comparator.naturalOrder());
        } else if (argument.endsWith("=desc")) {
          children.sort(Comparator.reverseOrder());
        } else {
          return "Invalid argument";
        }
      }
      // Add the name of each child to the result sorted or not depending on the argument
      for (FileSystemNode child : children) {
        result.append(child.name()).append(" ");
      }
      if (!result.isEmpty()) {
        return result.substring(0, result.length() - 1);
      }
      return "";
    } else {
      return "Not a directory";
    }
  }
}
