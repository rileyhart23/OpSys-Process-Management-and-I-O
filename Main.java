// Operating Systems Concepts and Design Programming Assignment 1: Process Management and I/O
// CPSC 340
// Riley Wasdyke
// 10/03/2024

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ProcessManager manager = new ProcessManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nProcess Manager Menu:");
            System.out.println("1. Add a process to the list.");
            System.out.println("2. Delete a process from the list by its ID.");
            System.out.println("3. Delete all processes by a user.");
            System.out.println("4. Print the entire process list, from beginning to end.");
            System.out.println("5. Save the process list to a file.");
            System.out.println("6. Load the process list from a file.");
            System.out.println("7. Schedule the processes using FCFS and display results.");
            System.out.println("8. Schedule the processes using RR, provide time quantum, and display results.");
            System.out.println("9. Exit the program.");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter process name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user: ");
                    String user = scanner.nextLine();
                    System.out.print("Enter execution time (in seconds): ");
                    int executionTime = scanner.nextInt();
                    manager.addProcess(name, user, executionTime);
                    break;
                case 2:
                    System.out.print("Enter process ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    manager.removeProcessById(idToDelete);
                    break;
                case 3:
                    System.out.print("Enter user name to delete all processes: ");
                    String userToDelete = scanner.nextLine();
                    manager.deleteProcessesByUser(userToDelete);
                    break;
                case 4:
                    manager.printProccesList();
                    break;
                case 5:
                    System.out.print("Enter filename to save: ");
                    String saveFilename = scanner.nextLine();
                    manager.saveToFile(saveFilename);
                    break;
                case 6:
                    System.out.print("Enter filename to load: ");
                    String loadFilename = scanner.nextLine();
                    manager.loadFromFile(loadFilename);
                    break;
                case 7:
                    manager.FCFSScheduling();
                    break;
                case 8:
                    System.out.print("Enter time quantum: ");
                    int timeQuantum = scanner.nextInt();
                    manager.RoundRobinScheduling(timeQuantum);
                    break;
                case 9:
                    System.out.println("Exiting program now.\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);

        scanner.close();
    }
}
