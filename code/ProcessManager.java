package code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessManager {
    Process head;

    public void addProcess(Process newProcess) {
        if (head == null) {
            head = newProcess;
        } else {
            Process temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.previous = temp;
        }
    }

    public void deleteProcessByID(int productID) {
        Process temp = head;
        while (temp != null) {
            if (temp.productID == productID) {
                if (temp.previous != null) {
                    temp.previous.next = temp.next;
                } else {
                    head = temp.next;
                }
                if (temp.next != null) {
                    temp.next.previous = temp.previous;
                }
                return;
            }
            temp = temp.next;
        }
    }

    public void deleteAllProcessesByUser(String user) {
        Process temp = head;

        while (temp != null) {
            if (temp.user == user) {
                Process toDelete = temp;
                temp = temp.next;
                deleteProcessByID(toDelete.productID);
            } else {
                temp = temp.next;
            }
        }
    }

    public void printProccesList() {
        Process temp = head;
        while (temp != null) {
            System.out.println("Product ID: " + temp.productID +
                    "\nName: " + temp.name +
                    "\nUser: " + temp.user +
                    "\nExecution Time: " + temp.executionTime);
            temp = temp.next;
        }
    }

    public void saveProcessList(String filenameToSave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filenameToSave))) {
            Process temp = head;
            while (temp != null) {
                writer.write(temp.productID + "," + temp.name + "," + temp.user + "," + temp.executionTime + "\n");
                temp = temp.next;
            }
        } catch (IOException xeption) {
            xeption.printStackTrace();
        }
    }

    public void loadProcessList(String filenameToLoad) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filenameToLoad))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int productID = Integer.parseInt(data[0]);
                String name = data[1];
                String user = data[2];
                int executionTime = Integer.parseInt(data[3]);
                Process newProcess = new Process(productID, name, user, executionTime);
                addProcess(newProcess);
            }
        } catch (IOException xeption) {
            xeption.printStackTrace();
        }
    }

}
