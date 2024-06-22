package edu.austral.ingsis.clifford.filesystem.node;

public record FileNode(String name) implements FileSystemNode {
  @Override
  public boolean isDirectory() {
    return false;
  }
}
