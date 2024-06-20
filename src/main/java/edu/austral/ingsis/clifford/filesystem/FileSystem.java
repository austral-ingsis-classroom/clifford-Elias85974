package edu.austral.ingsis.clifford.filesystem;

import edu.austral.ingsis.clifford.filesystem.node.DirectoryNode;
import edu.austral.ingsis.clifford.filesystem.node.FileSystemNode;

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
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
}
