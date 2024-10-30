// Operating Systems Concepts and Design Programming Assignment 2:
// CPSC 340
// Riley Wasdyke
// 10/29/2024

public class Process {
    int id;
    String name;
    String user;
    int executionTime;
    int remainingTime;
    int responseTime;
    int startTime;

    Process next;
    Process prev;

    public Process(int id, String name, String user, int executionTime) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.executionTime = executionTime;
        this.remainingTime = executionTime; // Initially, remaining time is the total execution time
        this.responseTime = -1; // Initialized to -1, will be set on first execution
        this.startTime = 0; // Indicates if the process has been started
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + user + "," + executionTime;
    }
}
