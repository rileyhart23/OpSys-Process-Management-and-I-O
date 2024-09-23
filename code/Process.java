package code;

public class Process {
    int productID, executionTime;
    String name, user;
    Process next;
    Process previous;

    public Process(int productID, String name, String user, int executionTime) {
        this.productID = productID;
        this.name = name;
        this.user = user;
        this.executionTime = executionTime;
        this.next = null;
        this.previous = null;
    }
}
