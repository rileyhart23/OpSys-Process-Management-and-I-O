public class Process {
    int id;
    String name;
    String user;
    int executionTime;
    Process next;
    Process prev;

    public Process(int id, String name, String user, int executionTime) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.executionTime = executionTime;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + user + "," + executionTime;
    }
}
