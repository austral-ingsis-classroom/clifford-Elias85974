package edu.austral.ingsis.clifford.filesystem.node;

import java.util.*;

public class DirectoryNode implements FileSystemNode {
    private final String name;
    private final DirectoryNode parent;
    private final Map<String, FileSystemNode> children;

    public DirectoryNode(String name, DirectoryNode parent) {
        this.name = name;
        this.parent = parent;
        this.children = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public Optional<DirectoryNode> getParent() {
        return Optional.ofNullable(parent);
    }

    public void addChild(FileSystemNode child) {
        children.put(child.getName(), child);
    }

    public void removeChild(String child) {
        children.remove(child);
    }

    public Optional<FileSystemNode> getChild(String path) {
        String next = path.substring(0, path.indexOf("/"));
        String remaining = path.substring(path.indexOf("/") + 1);
        if (remaining.isEmpty()) {
            return Optional.ofNullable(children.get(next));
        } else {
            FileSystemNode child = children.get(next);
            if (child == null || !child.isDirectory()) {
                return Optional.empty();
            }
            DirectoryNode child1 = (DirectoryNode) child;
            return child1.getChild(remaining);
        }
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public Map<String, FileSystemNode> getChildren() {
        return children;
    }
}
