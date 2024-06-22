package edu.austral.ingsis.clifford.filesystem;

import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;

public class FileSystem {
  private final DirectoryNode root = new DirectoryNode("/", null);
  private DirectoryNode current;
  private String currentPath;

  public FileSystem() {
    this.current = root;
    this.currentPath = "/";
  }

  public void setCurrent(DirectoryNode directoryNode) {
    this.current = directoryNode;
  }

  public DirectoryNode getCurrent() {
    return current;
  }

  public DirectoryNode getRoot() {
    return root;
  }

  public String getCurrentPath() {
    String path = currentPath;
    if (!path.equals("/")) {
      path = currentPath.substring(1);
    }
    return path;
  }

  public void setCurrentPath(String currentPath) {
    this.currentPath = currentPath;
  }
}
