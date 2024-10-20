package pl.dykacz.courses.courses.objects;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.api.objects.CourseApi;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.objects.values.Name;

public record Course(Id courseId, Name courseName, Name courseDescription) {
    public Course(@NonNull final CourseApi courseApi){
        this(new Id(courseApi.getCourseId()),new Name(courseApi.getCourseName()),new Name(courseApi.getDescription()));
    }

    public Course(@NonNull final Id courseId, @NonNull final Name courseName, @NonNull final Name courseDescription) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName=" + courseName +
                ", courseDescription=" + courseDescription +
                '}';
    }
}
