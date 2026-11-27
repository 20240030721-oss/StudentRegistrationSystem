import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class Student {

    private final String name;
    private final int age;
    private final double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name
                + ", Age: " + age
                + ", Grade: " + grade;
    }
}

public class StudentRegistration {

    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 120;
    private static final double MIN_GRADE = 0.0;
    private static final double MAX_GRADE = 100.0;
    private static final int MAX_NAME_LENGTH = 100;

    private final Map<String, Student> students = new LinkedHashMap<>();

    public void addStudent(String name, int age, double grade) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty.");
        }

        name = name.trim();

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Student name is too long.");
        }

        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException("Age must be between 0 and 120.");
        }

        if (!Double.isFinite(grade) || grade < MIN_GRADE || grade > MAX_GRADE) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }

        String studentKey = name.toLowerCase(Locale.ROOT);

        if (students.containsKey(studentKey)) {
            throw new IllegalArgumentException("Student already exists.");
        }

        Student student = new Student(name, age, grade);
        students.put(studentKey, student);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(
                new ArrayList<>(students.values())
        );
    }

    public void displayStudents() {
        for (Student student : getStudents()) {
            System.out.println(student);
        }
    }

    private void registerAndReport(String name, int age, double grade) {
        try {
            addStudent(name, age, grade);
            System.out.println("Student Added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Registration Failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        StudentRegistration registration = new StudentRegistration();

        registration.registerAndReport("", -5, 150);
        registration.registerAndReport("John", 20, 85);
        registration.registerAndReport("John", 20, 85);

        System.out.println("\nRegistered Students:");
        registration.displayStudents();
    }
}
