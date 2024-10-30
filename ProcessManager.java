// Operating Systems Concepts and Design Programming Assignment 2:
// CPSC 340
// Riley Wasdyke
// 10/29/2024

import java.text.DecimalFormat;
import java.io.*;

public class ProcessManager {
    private Process head;
    private Process tail;
    private int processIdCounter = 1;
    private int currentTime = 0;
    private int totalTurnaroundTime = 0;
    private int totalResponseTime = 0;
    private int processCount = 0;
    private int completedProcesses = 0;
    private float averageTurnaroundTime = 0;
    private float averageResponseTime = 0;

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

    public void printProcessList() {
        if (head == null) {
            System.out.println("No processes to print. \nAdd some processes or load from a file before printing.");
            return;
        } else {
            System.out.println("Process List:\n");
        }
        Process temp = head;
        System.out.printf("Process ID:\tName\t\tExecution Time\tResponse Time\tTurnaround Time\n");
        while (temp != null) {
            System.out.println(temp.id + "\t\t" + temp.name + "\t" + temp.executionTime +
                    "\t\t" + temp.responseTime + "\t\t" + temp.startTime);
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
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        } else {
            System.out.println("Running FCFS Scheduling \n");
        }

        currentTime = 0;
        totalTurnaroundTime = 0;
        totalResponseTime = 0;
        processCount = 0;

        Process temp = head;

        System.out.println("\nFCFS Scheduling:");
        System.out.printf("Process ID:\tName\tExecution Time\tResponse Time\tTurnaround Time\n");

        while (temp != null) {
            int responseTime = currentTime;
            int turnaroundTime = responseTime + temp.executionTime;
            currentTime += temp.executionTime;

            totalTurnaroundTime += turnaroundTime;
            totalResponseTime += responseTime;
            processCount++;

            System.out.println(temp.id + "\t" + temp.name + "\t" + temp.executionTime +
                    "\t\t" + responseTime + "\t\t" + turnaroundTime);

            temp = temp.next;
        }

        if (processCount > 0) {
            averageTurnaroundTime = (float) totalTurnaroundTime / processCount;
            averageResponseTime = (float) totalResponseTime / processCount;

            DecimalFormat df = new DecimalFormat("#.00");

            String formattedTurnaroundTime = df.format(averageTurnaroundTime);
            String formattedResponseTime = df.format(averageResponseTime);

            System.out.println("\nAverage Turnaround Time: " + formattedTurnaroundTime);
            System.out.println("Average Response Time: " + formattedResponseTime);
        }
    }

    public void RoundRobinScheduling(int quantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        } else {
            System.out.println("Running Round Robin Scheduling");
        }

        currentTime = 0;
        totalTurnaroundTime = 0;
        totalResponseTime = 0;
        processCount = 0;
        completedProcesses = 0;

        Process temp = head;
        System.out.printf("Process ID:\tName\tExecution Time\tResponse Time\tTurnaround Time\n");

        while (temp != null) {
            temp.remainingTime = temp.executionTime;
            temp.responseTime = -1;
            processCount++;
            temp = temp.next;
        }

        temp = head;

        while (completedProcesses < processCount) {
            if (temp.remainingTime > 0) {
                if (temp.responseTime == -1) {
                    temp.responseTime = currentTime;
                }

                int timeToRun = Math.min(temp.remainingTime, quantum);
                temp.remainingTime -= timeToRun;
                currentTime += timeToRun;

                if (temp.remainingTime == 0) {
                    int turnaroundTime = currentTime;
                    System.out.println(temp.id + "\t" + temp.name + "\t" + temp.executionTime +
                            "\t\t" + temp.responseTime + "\t\t" + turnaroundTime);

                    totalTurnaroundTime += turnaroundTime;
                    totalResponseTime += temp.responseTime;
                    completedProcesses++;
                }
            }

            temp = (temp.next != null) ? temp.next : head;
        }

        if (processCount > 0) {
            averageTurnaroundTime = (float) totalTurnaroundTime / processCount;
            averageResponseTime = (float) totalResponseTime / processCount;

            DecimalFormat df = new DecimalFormat("#.00");

            String formattedTurnaroundTime = df.format(averageTurnaroundTime);
            String formattedResponseTime = df.format(averageResponseTime);

            System.out.println("\nAverage Turnaround Time: " + formattedTurnaroundTime);
            System.out.println("Average Response Time: " + formattedResponseTime);
        }
    }
}
