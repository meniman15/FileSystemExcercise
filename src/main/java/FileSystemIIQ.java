import java.util.*;

public class FileSystemIIQ {
    DirectoryIIQ rootDir;
    DirectoryIIQ currentDir;
    Map<String, FileSystemContent> pathToFileOrDir;
    Map<String, List<FileSystemContent>> nameToFileOrDirList;
    final String DELIMITER = "/";

    public FileSystemIIQ() {
        String defaultRootName = "root";
        String rootPath = DELIMITER + defaultRootName;
        rootDir = new DirectoryIIQ(defaultRootName,rootPath, null);
        currentDir = rootDir;
        pathToFileOrDir = new HashMap<>();
        nameToFileOrDirList = new HashMap<>();
        pathToFileOrDir.put(rootPath, rootDir);
        List<FileSystemContent> fileSystemContents = new ArrayList<>();
        fileSystemContents.add(rootDir);
        nameToFileOrDirList.put(defaultRootName, fileSystemContents);
    }

    public void mkdir(String dirName){
        if (currentDir.getNameToDirectoryOrFile().containsKey(dirName)){
            System.out.println("ERROR: there is already directory with the following name: " + dirName);
        }
        else {
            String newDirPath = currentDir.getPath() + DELIMITER + dirName;
            DirectoryIIQ newDir =  new DirectoryIIQ(dirName , newDirPath , currentDir);
            currentDir.getNameToDirectoryOrFile().put(dirName , newDir);
            List<FileSystemContent> contentList = nameToFileOrDirList.getOrDefault(dirName,new ArrayList<>());
            contentList.add(newDir);
            nameToFileOrDirList.put(dirName, contentList);
            pathToFileOrDir.put(newDirPath , newDir);
        }
    }

    public void mkfile(String fileName){
        if (currentDir.getNameToDirectoryOrFile().containsKey(fileName)){
            System.out.println("ERROR: there is already file with the following name: " + fileName);
        }
        else{
            String newFilePath = currentDir.getPath() + DELIMITER + fileName;
            FileInt file = new FileIIQ(fileName, newFilePath, currentDir);
            currentDir.getNameToDirectoryOrFile().put(fileName, file);
            List<FileSystemContent> contentList = nameToFileOrDirList.getOrDefault(newFilePath,new ArrayList<>());
            contentList.add(file);
            nameToFileOrDirList.put(fileName, contentList);
            pathToFileOrDir.put(newFilePath, file);
        }
    }

    public void cd(String path){
        //means path is relative
        if (!path.startsWith(DELIMITER)) {
            path = currentDir.getPath() + DELIMITER + path;
        }

        FileSystemContent dir = pathToFileOrDir.get(path);
        if (dir == null){
            System.out.println("Path not found: "+ path);
        }
        else{
            currentDir = (DirectoryIIQ) dir;
        }
    }

    public void cdToParentDir(){
        if (currentDir.getParent() != null){
            cd(currentDir.getParent().getPath());
        }
        else {
            System.out.println(currentDir.getName() + " does not have a parent.");
        }
    }

    public void print(){
        int indentations = 0;
        print(currentDir, indentations);
    }

    public void print(FileSystemContent content , int indentations){
        printWithIndentations(content, indentations);

        if (content instanceof DirectoryIIQ){
            for (Map.Entry<String,FileSystemContent> entry : ((DirectoryIIQ)content).getNameToDirectoryOrFile().entrySet()){
                print(entry.getValue(),indentations + 1);
            }
        }
    }

    private void printWithIndentations(FileSystemContent content , int indentations){
        for (int i=0; i<indentations; i++){
            System.out.print("   ");
        }
        System.out.println(content.getPath());
    }

    private List<FileInt> getFilesByName(String fileName){
        List<FileInt> files = new ArrayList<>();
        List<FileSystemContent> contents = nameToFileOrDirList.get(fileName);
        if (contents != null && !contents.isEmpty()){
            for(FileSystemContent content : contents){
                if (content instanceof FileInt){
                    files.add((FileInt)content);
                }
            }
        }
        return files;
    }

    public void findFile(String fileName){
        List<FileInt> files = getFilesByName(fileName);
        if (files.isEmpty()){
            System.out.println("Not Found file by name: "+ fileName);
        }
        for (FileInt fileInt : files){
            System.out.println(fileInt.getPath());
        }
    }

    public void printFile(String fileName){
        List<FileInt> files = getFilesByName(fileName);
        for (FileInt fileInt : files){
            fileInt.printFile();
        }
    }

    /*****************************************
     * DELETE SECTION
     *****************************************/
    public void deleteFiles(String fileName){
        deleteContentByName(fileName, FileIIQ.class.getName());
    }

    public void deleteDirs(String dirName){
        deleteContentByName(dirName, DirectoryIIQ.class.getName());
    }

    public void deleteContentByName(String contentName, String className){
        List<FileSystemContent> contentList = nameToFileOrDirList.get(contentName);
        if (contentList == null){
            System.out.println("Nothing to delete for content name: " + contentName);
            return;
        }
        List<FileSystemContent> leftAfterRemoval = new ArrayList<>();
        for(FileSystemContent content : contentList){
            if (!content.getClass().getName().equals(className)){
                leftAfterRemoval.add(content);
            }
            else{
                if (content instanceof DirectoryIIQ && !((DirectoryIIQ)content).getNameToDirectoryOrFile().isEmpty()){
                    System.out.println("Cannot remove directory: " + contentName +" since it is not empty.");
                }
                else {
                    ((DirectoryIIQ)content.getParent()).getNameToDirectoryOrFile().remove(contentName);
                    pathToFileOrDir.remove(content.getPath());
                }
            }
        }
        if (leftAfterRemoval.isEmpty()){
            nameToFileOrDirList.remove(contentName);
        }
        else{
            nameToFileOrDirList.put(contentName, leftAfterRemoval);
        }
    }
}