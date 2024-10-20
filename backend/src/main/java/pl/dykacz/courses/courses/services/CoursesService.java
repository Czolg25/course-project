package pl.dykacz.courses.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.dykacz.courses.courses.objects.Course;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.repositories.DataRepository;

import java.util.List;

@Service
public class CoursesService {
    private final DataRepository dataRepository;

    @Autowired
    public CoursesService(@NonNull final DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Course> getAllCourses() {
        return this.dataRepository.getAllCourses();
    }

    public Course findCourseById(@NonNull final Id id) {
        return this.dataRepository.getCourse(id);
    }

    public boolean updateCourse(@NonNull final Course course) {
        return this.dataRepository.updateCourse(course);
    }

    public boolean addCourse(@NonNull final Course course) {
        return this.dataRepository.addCourse(course);
    }

    public boolean deleteCourse(@NonNull final Id courseId) {
        return this.dataRepository.deleteCourse(courseId);
    }
}
