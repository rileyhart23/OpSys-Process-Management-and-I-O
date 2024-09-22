import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        processManager manager = new processManager();
        Scanner scanner = new Scanner(System.in);
        int userChoice;

        while(true){
            System.out.println("\n");
            System.out.println("Menu:");
            System.out.println("1. Add a process to the list");
            System.out.println("2. Delete a process from the list by its ID");
            System.out.println("3. Delete all processes by a user");
            System.out.println("4. Print the entire process list");
            System.out.println("5. Save the process list to a file");
            System.out.println("6. Load the process list from a file");
            System.out.println("7. Exit the program");
            System.out.print("Enter your choice: ");
            userChoice = scanner.nextInt();
            scanner.nextLine(); 


            switch (userChoice) {
                case 1:
                    System.out.println("Please enter the Process ID: ");
                    int processID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Please enter the Process Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Please enter the User: ");
                    String user = scanner.nextLine();

                    System.out.println("Please enter the Execution Time: ");
                    int exectionTime = scanner.nextInt();

                    //process newProcess = new process(processID, name, user, exectionTime);
                    // manager.addprocess(newProcess);
                    break;
                case 2:
                    System.out.println("Please enter the ID of the process you want to delete: ");
                    int processIDDelete = scanner.nextInt();
                    //manager.deleteProcessByID(processIDDelete);
                    break;
                case 3:
                    System.out.println("Enter User to delete all processes: ");
                    String userDelete = scanner.nextLine();
                    //manager.deleteAllProcessesByUser(userDelete);
                    break;
                case 4:
                    //manager.printProccesList();
                    break;
                case 5:
                    System.out.println("Enter filename to save: ");
                    String filenameToSave = scanner.nextLine();
                    //manager.saveProcessList(filenameToSave);
                    break;
                case 6:
                    System.out.println("Enter filename to load: ");
                    String  filenameToLoad = scanner.nextLine();
                    break;
                case 7:
                    scanner.close();
                    System.out.println("Exiting code now.\n");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.\n");
        
            }
        }
    }
}