# OpSys-Process-Management-and-I-O

Operating Systems Concepts and Design Programming Assignment 1: Process Management and I/O

CPSC 340
Operating Systems Concepts and Design
Programming Assignment 1: Process Management and I/O
Objective
The objective of this assignment is to review and practice using fundamental operating
system concepts such as process management and file I/O, while enhancing your skills in C
programming. You will use C structs, pointers, and file operations to implement a process
manager simulation using a doubly linked list. Alternatively, you can use java for this
assignment.

Write a C program or java program that allows the user to manage processes. The program
will simulate a basic process manager by allowing users to:
• Add new processes,
• Remove a process by its ID,
• Remove all processes by a specific user,
• Display the process list,
• Save the process list to a file,
• Load the process list from a file.

Your source code should include:

- A .h header file that declares the process struct.
- A .c file that implements the functions.
  The process struct should contain:
- Process ID (stored as an int),
- Process Name (stored as a char\*),
- User (stored as a char\*),
- Execution Time in seconds (stored as an int),
- A next pointer to the next process struct (for doubly linked list functionality),
- A prev pointer to the previous process struct (for doubly linked list functionality).

                              Program Menu

  When the program runs, prompt the user with the following menu options:

1.  Add a process to the list.
2.  Delete a process from the list by its ID.
3.  Delete all processes by a user.
4.  Print the entire process list, from beginning to end.
5.  Save the process list to a file.
6.  Load the process list from a file.
7.  Exit the program.

                        Program Operation

8.  Add a process: Prompt the user to input the process name, user, and execution time.
    Create a new node dynamically and add it to the tail of the doubly linked list.
9.  Delete by ID: Prompt the user for the process ID and remove the matching node.
10. Delete by user: Prompt the user for the user’s name and remove all processes associated
    with that user.
11. Print the process list: Traverse the list from the head to the tail and print each process’s
    details.
12. Save to file: Save the process list to a file. Each process should be written as a separate
    line, with fields delimited by commas.
13. Load from file: Load the process list from a file, recreating the list in memory.
14. Exit: Free all dynamically allocated memory before exiting to avoid memory leaks.
