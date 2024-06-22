package edu.austral.ingsis.clifford.command.executer;

import edu.austral.ingsis.clifford.filesystem.FileSystem;
import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;
import edu.austral.ingsis.clifford.filesystem.node.FileSystemNode;
import java.util.Optional;

public class CdCommand implements Command {
  @Override
  public String execute(FileSystem fileSystem, String argument) {
    if (argument.startsWith("/")) {
      return handleRootPath(fileSystem, argument);
    } else if (argument.equals("..")) {
      return handleParentDirectory(fileSystem);
    } else {
      return handleChildDirectory(fileSystem, argument);
    }
  }

  private String handleRootPath(FileSystem fileSystem, String argument) {
    if (argument.equals("/")) {
      setCurrentDirectoryAndPath(fileSystem, fileSystem.getRoot(), "/");
    } else {
      setCurrentDirectoryAndPath(fileSystem, fileSystem.getRoot(), argument.substring(1));
    }
    return movedToDirectoryMessage(fileSystem.getCurrent().name());
  }

  private String handleParentDirectory(FileSystem fileSystem) {
    DirectoryNode currentDirectory = fileSystem.getCurrent();
    Optional<DirectoryNode> parent = currentDirectory.getParent();
    parent.ifPresent(
        directoryNode ->
            setCurrentDirectoryAndPath(
                fileSystem,
                directoryNode,
                fileSystem
                    .getCurrentPath()
                    .substring(0, fileSystem.getCurrentPath().lastIndexOf("/") + 1)));
    return movedToDirectoryMessage(fileSystem.getCurrent().name());
  }

  private String handleChildDirectory(FileSystem fileSystem, String argument) {
    DirectoryNode currentDirectory = fileSystem.getCurrent();
    Optional<FileSystemNode> child = currentDirectory.getChild(argument);
    if (child.isPresent() && child.get().isDirectory()) {
      setCurrentDirectoryAndPath(
          fileSystem, (DirectoryNode) child.get(), fileSystem.getCurrentPath() + "/" + argument);
      return movedToDirectoryMessage(fileSystem.getCurrent().name());
    } else {
      return "'" + argument + "' directory does not exist";
    }
  }

  private void setCurrentDirectoryAndPath(
      FileSystem fileSystem, DirectoryNode directory, String path) {
    fileSystem.setCurrent(directory);
    fileSystem.setCurrentPath(path);
  }

  private String movedToDirectoryMessage(String path) {
    return "moved to directory '" + path + "'";
  }
}
