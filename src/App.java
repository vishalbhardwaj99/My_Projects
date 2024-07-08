import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Students_Data {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentsDAO studentsDAO = new StudentsDAO();
        int choice;

        do {
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Remove Student");
            System.out.println("4. Get All Students");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(studentsDAO, sc);
                    break;

                case 2:
                    updateStudent(studentsDAO, sc);
                    break;

                case 3:
                    removeStudent(studentsDAO, sc);

                case 4:
                    getAllStudents(studentsDAO);
                    break;

                case 5:
                    System.out.println("Exting.....");
                    break;

                default:
                    System.out.println("Invalid choice, Try again ");
                    break;
            }

        } while (choice != 5);
    }

    private static void addStudent(StudentsDAO studentsDAO, Scanner sc) {
        System.out.println("Enter Student age:- ");
        int age = sc.nextInt();
        System.out.println("Enter Student Name:-");
        String name = sc.nextLine();
        sc.nextLine();
        System.out.println("Enter the Course");
        String course = sc.nextLine();

        Students students = new Students(course, age, name);
        studentsDAO.AddStudents(students);
    }

    private static void getAllStudents(StudentsDAO studentsDAO) {
        if (studentsDAO.getAllStudents().isEmpty()) {
            System.out.println("Enter the data first.");
        }

        else {
            for (Students student : studentsDAO.getAllStudents()) {
                System.out.println(student);
            }

        }
    }

    private static void removeStudent(StudentsDAO studentsDAO, Scanner sc) {
        System.out.println("Enter the Student ID to remove");
        int ID = sc.nextInt();
        studentsDAO.deleteStudent(ID);
    }

    private static void updateStudent(StudentsDAO studentsDAO, Scanner sc) {
        System.out.println("Enter student ID to update:-");
        int Id = sc.nextInt();
        System.out.println("Enter Student new Name :-");
        String name = sc.nextLine();
        sc.nextLine();
        System.out.println("Enter the new Course:-");
        String course = sc.nextLine();
        sc.nextLine();
        System.out.println("Enter the student Age to update:-");
        int age = sc.nextInt();

        Students students = new Students(Id, age, name, course);
        studentsDAO.UpdateStudents(students);
    }

}

class Students {
    private int id;
    private String name;
    private int age;
    private String course;

    public Students(String course, int age, String name) {
        this.age = age;
        this.course = course;
        this.name = name;
    }

    public Students(int id, int age, String name, String course) {
        this.course = course;
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Students [id=" + id + ", name=" + name + ", age=" + age + ", course=" + course + "]";
    }

}

class StudentsDAO {
    private List<Students> Students;
    private int currentId;

    public StudentsDAO() {
        Students = new ArrayList<>();
        currentId = 1;
    }

    public void AddStudents(Students student) {
        student.setId(currentId++);
        Students.add(student);
    }

    public void UpdateStudents(Students student) {
        for (Students s : Students) {
            if (s.getId() == student.getId()) {
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());
                s.setName(student.getName());

                if (s.getId() != student.getId()) {
                    System.out.println("There's no data of Students");
                    break;
                }
            }
        }
    }

    public void deleteStudent(int id) {
        Students.removeIf(student -> student.getId() == id);
        System.out.println("Students [id=" + id + "] Has been removed");

    }

    public List<Students> getAllStudents() {
        return new ArrayList<>(Students);

    }
}