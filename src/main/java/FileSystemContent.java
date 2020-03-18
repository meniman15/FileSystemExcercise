public abstract class FileSystemContent {
    String path;
    String name;
    FileSystemContent parent;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public FileSystemContent getParent() {
        return parent;
    }
}
