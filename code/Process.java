package code;

public class Process {
    public int productID, executionTime;
    public String name, user;
    public Process next;
    public Process previous;

    public Process(int productID, String name, String user, int executionTime) {
        this.productID = productID;
        this.name = name;
        this.user = user;
        this.executionTime = executionTime;
        this.next = null;
        this.previous = null;
        
    }
}
