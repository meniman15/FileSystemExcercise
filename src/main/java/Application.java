import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        FileSystemIIQ fileSystemIIQ = new FileSystemIIQ();
/*        fileSystemIIQ.mkdir("dir1_1");
        fileSystemIIQ.mkdir("dir2_2");
        fileSystemIIQ.cd("/root/dir1_1");
        fileSystemIIQ.mkfile("myFile1_1");
        fileSystemIIQ.cdToParentDir();
        fileSystemIIQ.deleteDirs("dir1_1");
        fileSystemIIQ.deleteDirs("dir2_2");
        fileSystemIIQ.findFile("myFile1_1");
        fileSystemIIQ.print();*/

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your input");
        String userInput = scanner.nextLine();
        while (!userInput.equals("exit")){
            if (userInput.startsWith("mkdir")){
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)){
                    System.out.println("Bad Command");
                }
                else{
                    fileSystemIIQ.mkdir(command[1]);
                }
            }
            else if (userInput.startsWith("mkFile" )){
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)){
                    System.out.println("Bad Command");
                }
                else{
                    fileSystemIIQ.mkfile(command[1]);
                }
            }
            else if (userInput.startsWith("cd") && !userInput.startsWith("cd..")) {
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)) {
                    System.out.println("Bad Command");
                } else {
                    fileSystemIIQ.cd(command[1]);
                }
            }
            else if (userInput.startsWith("cd.." )) {
                fileSystemIIQ.cdToParentDir();
            }
            else if (userInput.startsWith("deleteFiles")) {
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)) {
                    System.out.println("Bad Command");
                } else {
                    fileSystemIIQ.deleteFiles(command[1]);
                }
            }
            else if (userInput.startsWith("deleteDirs")) {
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)) {
                    System.out.println("Bad Command");
                } else {
                    fileSystemIIQ.deleteDirs(command[1]);
                }
            }
            else if (userInput.startsWith("printFile")) {
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)) {
                    System.out.println("Bad Command");
                } else {
                    fileSystemIIQ.printFile(command[1]);
                }
            }
            else if (userInput.startsWith("findFile")) {
                String[] command = userInput.split(" ");
                if (!isValidInput(userInput)) {
                    System.out.println("Bad Command");
                } else {
                    fileSystemIIQ.findFile(command[1]);
                }
            }
            else if (userInput.startsWith("print")) {
                fileSystemIIQ.print();
            }

            userInput = scanner.nextLine();
        }
    }

    private static boolean isValidInput(String userInput){
        String[] command = userInput.split(" ");
        return command.length >= 2;
    }
}
