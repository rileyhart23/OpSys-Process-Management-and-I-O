import java.io.*;
import java.util.Scanner;

public class ProcessManager {
    private Process head;
    private Process tail;
    private int processIdCounter = 1; // Simple counter for process IDs

    public ProcessManager() {
        head = null;
        tail = null;
    }

    // Method to add a process
    public void addProcess(String name, String user, int executionTime) {
        Process newProcess = new Process(processIdCounter++, name, user, executionTime);
        if (head == null) {
            head = newProcess;
            tail = newProcess;
        } else {
            tail.next = newProcess;
            newProcess.prev = tail;
            tail = newProcess;
        }
        System.out.println("Process added: " + newProcess);
    }

    // Method to remove a process by ID
    public void removeProcessById(int id) {
        Process current = head;
        while (current != null) {
            if (current.id == id) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // Removing head
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // Removing tail
                }
                System.out.println("Process removed: " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("Process ID not found.");
    }

    // Method to remove all processes by user
    public void removeProcessesByUser(String user) {
        Process current = head;
        boolean found = false;
        while (current != null) {
            if (current.user.equals(user)) {
                found = true;
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // Removing head
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // Removing tail
                }
                System.out.println("Process removed: " + current);
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No processes found for user: " + user);
        }
    }

    // Method to display the process list
    public void printProcessList() {
        Process current = head;
        if (current == null) {
            System.out.println("Process list is empty.");
            return;
        }
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    // Method to save the process list to a file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Process current = head;
            while (current != null) {
                writer.write(current.toString());
                writer.newLine();
                current = current.next;
            }
            System.out.println("Process list saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Method to load the process list from a file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    addProcess(parts[1], parts[2], Integer.parseInt(parts[3]));
                }
            }
            System.out.println("Process list loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}