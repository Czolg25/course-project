package pl.dykacz.courses.courses.objects;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.api.objects.StudentApi;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.objects.values.Mail;
import pl.dykacz.courses.courses.objects.values.Name;

public record Student(Id studentId, Name firstName, Name lastName, Mail mail) {
    public Student(@NonNull StudentApi studentApi){
        this(studentApi.getStudentId() != null?new Id(studentApi.getStudentId()):null,new Name(studentApi.getFirstName()),new Name(studentApi.getLastName()),
                new Mail.Builder(studentApi.getEmail()).build());
    }

    public Student(final Id studentId, @NonNull final Name firstName, @NonNull final Name lastName, @NonNull final Mail mail) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", mail=" + mail +
                '}';
    }
}
