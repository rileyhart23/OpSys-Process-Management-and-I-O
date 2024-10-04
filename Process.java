// Operating Systems Concepts and Design Programming Assignment 1: Process Management and I/O
// CPSC 340
// Riley Wasdyke
// 10/03/2024

public class Process {
    int id, executionTime;
    String name, user;
    Process next, prev;

    public Process(int id, String name, String user, int executionTime) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.executionTime = executionTime;
        this.next = null;
        this.prev = null;
    }

}
