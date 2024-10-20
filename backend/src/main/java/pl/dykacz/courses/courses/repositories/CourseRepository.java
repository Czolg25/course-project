package pl.dykacz.courses.courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dykacz.courses.courses.entities.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {
}
