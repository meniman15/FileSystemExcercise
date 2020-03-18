import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FileIIQ extends FileInt {

    DirectoryIIQ parent;
    long time;

    public FileIIQ(String name, String path, DirectoryIIQ parent) {
        this.name = name;
        this.path = path;
        this.time = System.currentTimeMillis();
        this.parent = parent;
    }

    public void printFile() {
        System.out.println("file: " + name + ", in time: " + time);
    }
}
