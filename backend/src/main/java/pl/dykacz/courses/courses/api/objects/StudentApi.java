package pl.dykacz.courses.courses.api.objects;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.objects.Student;

public class StudentApi {
    private final Long studentId;
    private final String firstName;
    private final String lastName;
    private final String email;

    public StudentApi(@NonNull final Student student){
        this(student.studentId() != null ? student.studentId().getIdAsLong() : null,student.firstName().toString(),
                student.lastName().toString(),student.mail().toString());
    }

    public StudentApi(final Long studentId, final String firstName, final String lastName, final String mail) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = mail;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "StudentApi{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
