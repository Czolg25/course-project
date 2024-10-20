package pl.dykacz.courses.courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dykacz.courses.courses.entities.Enrollments;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollments, Long> {
}
