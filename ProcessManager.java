// Operating Systems Concepts and Design Programming Assignment 1: Process Management and I/O
// CPSC 340
// Riley Wasdyke
// 10/03/2024

import java.io.*;

public class ProcessManager {
    private Process head;
    private Process tail;
    private int processIdCounter = 1;

    public ProcessManager() {
        head = null;
        tail = null;
    }

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
        System.out.println("Process added sucessfully");
    }

    public void removeProcessById(int id) {
        Process current = head;
        while (current != null) {
            if (current.id == id) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                System.out.println("Process removed: " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("Process ID not found.");
    }

    public void deleteProcessesByUser(String user) {
        Process current = head;
        boolean found = false;
        while (current != null) {
            if (current.user.equals(user)) {
                found = true;
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                System.out.println("Process removed: " + current);
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No processes found for user: " + user);
        }
    }

    public void printProccesList() {
        Process temp = head;
        while (temp != null) {
            System.out.println("Process ID: " + temp.id +
                    "\nName: " + temp.name +
                    "\nUser: " + temp.user +
                    "\nExecution Time: " + temp.executionTime);
            temp = temp.next;
        }
    }

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

    public void FCFSScheduling() {
        int currentTime = 0;
        Process temp = head;
        System.out.println("\nRunning FCFS Scheduling:");

        while (temp != null) {
            int responseTime = currentTime;
            currentTime += temp.executionTime; // Update current time
            int turnaroundTime = currentTime;

            System.out.printf("Process ID: " + temp.id, "Name: " + temp.name,
                    "Execution Time: " + temp.executionTime, "Response Time: " + responseTime,
                    "Turnaround Time: " + turnaroundTime);
            temp = temp.next;
        }
    }

    public void RoundRobinScheduling(int quantum) {
        Process temp = head;
        System.out.println("\nRunning Round Robin Scheduling with Quantum = " + quantum + ":");
        int currentTime = 0;

        if (temp == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        // Create a circular linked list
        tail.next = head; // Connect tail to head for circular behavior

        do {
            boolean allDone = true; // Flag to check if all processes are done
            temp = head; // Start from the head

            do {
                if (temp.executionTime > 0) {
                    allDone = false; // At least one process is not done
                    int timeToExecute = Math.min(temp.executionTime, quantum);
                    currentTime += timeToExecute; // Increase current time
                    temp.executionTime -= timeToExecute; // Decrease execution time

                    // If process is completed
                    if (temp.executionTime == 0) {
                        System.out.printf("Process ID: %d, Name: %s completed at time: %d%n",
                                temp.id, temp.name, currentTime);
                    } else {
                        System.out.printf("Process ID: %d, Name: %s executed for %d time units, remaining time: %d%n",
                                temp.id, temp.name, timeToExecute, temp.executionTime);
                    }
                }
                temp = temp.next; // Move to next process
            } while (temp != head); // Continue until we circle back to head

            if (allDone)
                break; // If all processes are done, exit loop

        } while (true);
    }
}