import java.util.Scanner;

public class Main {
    static final int MAX = 50;
    static String[] names = new String[MAX];
    static int[] rollNumbers = new int[MAX];
    static int[][] marks = new int[MAX][3];
    static int studentCount = 0;

    public static void menu() {
        System.out.println("Student Grade Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Update Marks");
        System.out.println("3. Remove Student");
        System.out.println("4. View All Students");
        System.out.println("5. Search Student");
        System.out.println("6. Highest Scorer");
        System.out.println("7. Class Average");
        System.out.println("8. Exit");
        System.out.print("Choose option: ");
    }

    public static void addStudent(Scanner sc) {
        if (studentCount >= MAX) {
            System.out.println("Limit reached");
            return;
        }

        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < studentCount; i++) {
            if (rollNumbers[i] == roll) {
                System.out.println("Roll number already exists");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        int[] subMarks = new int[3];
        for (int i = 0; i < 3; i++) {
            int m;
            do {
                System.out.print("Enter Marks of Subject " + (i + 1) + ": ");
                m = sc.nextInt();
            } while (m < 0 || m > 100);
            subMarks[i] = m;
        }

        names[studentCount] = name;
        rollNumbers[studentCount] = roll;
        marks[studentCount] = subMarks;
        studentCount++;

        System.out.println("Student added successfully");
    }

    public static void updateMarks(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (rollNumbers[i] == roll) {
                found = true;
                for (int j = 0; j < 3; j++) {
                    int m;
                    do {
                        System.out.print("Enter new marks for Subject " + (j + 1) + ": ");
                        m = sc.nextInt();
                    } while (m < 0 || m > 100);
                    marks[i][j] = m;
                }
                System.out.println("Marks updated");
                break;
            }
        }
        if (!found) System.out.println("Student not found");
    }

    public static void removeStudent(Scanner sc) {
        System.out.print("Enter Roll No to remove: ");
        int roll = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (rollNumbers[i] == roll) {
                found = true;
                for (int j = i; j < studentCount - 1; j++) {
                    names[j] = names[j + 1];
                    rollNumbers[j] = rollNumbers[j + 1];
                    marks[j] = marks[j + 1];
                }
                studentCount--;
                System.out.println("Student removed");
                break;
            }
        }
        if (!found) System.out.println("Student not found");
    }

    public static void viewAll() {
        if (studentCount == 0) {
            System.out.println("No students yet");
            return;
        }
        System.out.println("RollNo  Name  Marks  Total  Average");
        for (int i = 0; i < studentCount; i++) {
            int total = marks[i][0] + marks[i][1] + marks[i][2];
            double avg = total / 3.0;
            System.out.print(rollNumbers[i] + "  " + names[i] + "  ");
            System.out.print(marks[i][0] + " " + marks[i][1] + " " + marks[i][2] + "  ");
            System.out.print(total + "  " + avg);
            System.out.println();
        }
    }

    public static void searchStudent(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (rollNumbers[i] == roll) {
                found = true;
                int total = marks[i][0] + marks[i][1] + marks[i][2];
                double avg = total / 3.0;
                System.out.println("Student Found");
                System.out.println("Name: " + names[i]);
                System.out.println("Roll No: " + rollNumbers[i]);
                System.out.println("Marks: " + marks[i][0] + " " + marks[i][1] + " " + marks[i][2]);
                System.out.println("Total: " + total + " Average: " + avg);
                break;
            }
        }
        if (!found) System.out.println("Student not found");
    }

    public static void highestScorer() {
        if (studentCount == 0) {
            System.out.println("No students yet");
            return;
        }
        int maxIndex = 0, maxTotal = 0;
        for (int i = 0; i < studentCount; i++) {
            int total = marks[i][0] + marks[i][1] + marks[i][2];
            if (total > maxTotal) {
                maxTotal = total;
                maxIndex = i;
            }
        }
        double avg = maxTotal / 3.0;
        System.out.println("Highest Scorer");
        System.out.println("Name: " + names[maxIndex]);
        System.out.println("Roll No: " + rollNumbers[maxIndex]);
        System.out.println("Total: " + maxTotal + " Average: " + avg);
    }

    public static void classAverage() {
        if (studentCount == 0) {
            System.out.println("No students yet");
            return;
        }
        int sum = 0;
        for (int i = 0; i < studentCount; i++) {
            sum += marks[i][0] + marks[i][1] + marks[i][2];
        }
        double avg = sum / (studentCount * 3.0);
        System.out.println("Class Average: " + avg);
    }

    public static void exitProgram() {
        System.out.println("Exiting");
        System.out.println("Total Students: " + studentCount);
        classAverage();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            menu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1: addStudent(sc); break;
                case 2: updateMarks(sc); break;
                case 3: removeStudent(sc); break;
                case 4: viewAll(); break;
                case 5: searchStudent(sc); break;
                case 6: highestScorer(); break;
                case 7: classAverage(); break;
                case 8: exitProgram(); return;
                default: System.out.println("Invalid choice");
            }
        }
    }
}
