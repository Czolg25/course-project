package pl.dykacz.courses.courses.api.objects;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.objects.Course;

public class CourseApi {
    private final Long courseId;
    private final String courseName;
    private final String description;

    public CourseApi(@NonNull Course course) {
        this(course.courseId() != null ? course.courseId().getIdAsLong() : null,course.courseName().toString(),
                course.courseDescription().toString());
    }

    public CourseApi(Long courseId, String name, String description) {
        this.courseId = courseId;
        this.courseName = name;
        this.description = description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CourseApi{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
