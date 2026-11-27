public class StudentRegistrationTest {
    public static void main(String[] args) {
        StudentRegistration reg = new StudentRegistration();

        // Test 1: Valid addition
        reg.addStudent("Alice", 22, 92.5);
        assert reg.getStudents().size() == 1 : "Expected 1 student";

        // Test 2: Invalid age throws exception
        boolean threwException = false;
        try {
            reg.addStudent("Bob", -1, 80.0);
        } catch (IllegalArgumentException e) {
            threwException = true;
        }
        assert threwException : "Expected IllegalArgumentException for negative age";

        System.out.println("All plain Java tests passed!");
    }
}