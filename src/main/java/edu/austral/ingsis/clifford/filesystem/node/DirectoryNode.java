package edu.austral.ingsis.clifford.filesystem.node;

import java.util.*;

public class DirectoryNode implements FileSystemNode {
  private final String name;
  private final DirectoryNode parent;
  private final Map<FileSystemNode, Integer> children;
  private int childCount = 0;

  public DirectoryNode(String name, DirectoryNode parent) {
    this.name = name;
    this.parent = parent;
    this.children = new HashMap<>();
  }

  @Override
  public String name() {
    return name;
  }

  public Optional<DirectoryNode> getParent() {
    return Optional.ofNullable(parent);
  }

  public void addChild(FileSystemNode child) {
    children.put(child, childCount++);
  }

  public boolean hasChild(String child) {
    return getFromName(child).isPresent();
  }

  public void removeChild(String child) {
    Optional<FileSystemNode> childNode = getFromName(child);
    childNode.ifPresent(children::remove);
  }

  public Optional<FileSystemNode> getChild(String path) {
    int nextDir = path.indexOf("/");
    // No more directories to go
    if (nextDir == -1) {
      return getFromName(path);
    }
    String next = path.substring(0, nextDir);
    String remaining = path.substring(nextDir + 1);
    if (remaining.isEmpty()) {
      return getFromName(next);
    } else {
      Optional<FileSystemNode> child = getFromName(next);
      if (child.isEmpty() || !child.get().isDirectory()) {
        return Optional.empty();
      }
      DirectoryNode child1 = (DirectoryNode) child.get();
      return child1.getChild(remaining);
    }
  }

  private Optional<FileSystemNode> getFromName(String path) {
    for (FileSystemNode node : children.keySet()) {
      if (node.name().equals(path)) {
        return Optional.of(node);
      }
    }
    return Optional.empty();
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public List<FileSystemNode> getChildren() {
    List<Map.Entry<FileSystemNode, Integer>> list = new ArrayList<>(children.entrySet());

    list.sort(
        (entry1, entry2) -> {
          FileSystemNode node1 = entry1.getKey();
          FileSystemNode node2 = entry2.getKey();

          if (node1.isDirectory() && !node2.isDirectory()) {
            return -1;
          } else if (!node1.isDirectory() && node2.isDirectory()) {
            return 1;
          } else {
            return entry1.getValue().compareTo(entry2.getValue());
          }
        });

    List<FileSystemNode> sortedKeys = new ArrayList<>();
    for (Map.Entry<FileSystemNode, Integer> entry : list) {
      sortedKeys.add(entry.getKey());
    }
    return sortedKeys;
  }
}
