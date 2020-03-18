import java.util.HashMap;
import java.util.Map;

public class DirectoryIIQ extends FileSystemContent{
    private String name;
    private String path;
    private Map<String, FileSystemContent> nameToDirectoryOrFile;

    public DirectoryIIQ(String name, String path, DirectoryIIQ parent) {
        this.name = name;
        this.path = path;
        nameToDirectoryOrFile = new HashMap<>();
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, FileSystemContent> getNameToDirectoryOrFile() {
        return nameToDirectoryOrFile;
    }

    public void setNameToDirectoryOrFile(Map<String, FileSystemContent> nameToDirectoryOrFile) {
        this.nameToDirectoryOrFile = nameToDirectoryOrFile;
    }
}
