package pl.dykacz.courses.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.dykacz.courses.courses.objects.Student;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.repositories.DataRepository;

import java.util.List;

@Service
public class StudentService {
    private final DataRepository dataRepository;

    @Autowired
    public StudentService(@NonNull final DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Student> getAllStudents() {
        return this.dataRepository.getAllStudents();
    }

    public Student findStudentById(@NonNull final Id id) {
        return this.dataRepository.getStudent(id);
    }

    public boolean updateStudent(@NonNull final Student student) {
        return this.dataRepository.updateStudent(student);
    }

    public Student addStudent(@NonNull final Student student) {
        return this.dataRepository.addStudent(student);
    }

    public boolean deleteStudent(Id studentId) {
        return this.dataRepository.deleteStudent(studentId);
    }
}
