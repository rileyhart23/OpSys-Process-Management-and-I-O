package code;

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
                    head = temp.next; // If head is to be deleted
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
            if (temp.user.equals(user)) { // Use .equals for string comparison
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
            temp = temp.next; // Move to the next node
        }
    }
}
