package pl.dykacz.courses.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.dykacz.courses.courses.objects.Enrollment;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.repositories.DataRepository;

import java.util.List;

@Service
public class EnrollmentService {
    private final DataRepository dataRepository;

    @Autowired
    public EnrollmentService(@NonNull final DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return this.dataRepository.getAllEnrollments();
    }

    public boolean addEnrollment(@NonNull final Enrollment enrollment) {

        return this.dataRepository.addEnrollment(enrollment);
    }

    public boolean deleteEnrolment(Id enrollmentId) {
        return this.dataRepository.deleteEnrolment(enrollmentId);
    }
}
