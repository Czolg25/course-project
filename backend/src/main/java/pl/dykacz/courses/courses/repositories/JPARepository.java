package pl.dykacz.courses.courses.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.dykacz.courses.courses.entities.Courses;
import pl.dykacz.courses.courses.entities.Enrollments;
import pl.dykacz.courses.courses.entities.Students;
import pl.dykacz.courses.courses.objects.Course;
import pl.dykacz.courses.courses.objects.Enrollment;
import pl.dykacz.courses.courses.objects.Student;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.objects.values.Mail;
import pl.dykacz.courses.courses.objects.values.Name;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("JPA")
public class JPARepository implements DataRepository{
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public JPARepository(@NonNull final CourseRepository courseRepository, @NonNull final StudentRepository studentRepository,
                         @NonNull final EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        final List<Course> courses = new ArrayList<>();
        final List<Courses> coursesList = courseRepository.findAll();

        for (final Courses course : coursesList) {
            courses.add(this.getCourseObject(course));
        }
        return courses;
    }

    @Override
    public Course getCourse(@NonNull final Id courseId) {
        final Courses course = this.courseRepository.findById(courseId.getIdAsLong()).orElse(null);
        return this.getCourseObject(course);
    }

    @Override
    public boolean updateCourse(@NonNull final Course course) {
        final Courses courses = this.courseRepository.findById(course.courseId().getIdAsLong()).orElse(null);
        if (courses == null) return false;

        courses.setCourseName(course.courseName().toString());
        courses.setCourseDescription(course.courseDescription().toString());

        this.courseRepository.save(courses);
        return true;
    }

    @Override
    public boolean deleteCourse(@NonNull final Id curseId) {
        this.courseRepository.deleteById(curseId.getIdAsLong());
        return true;
    }

    @Override
    public boolean addCourse(@NonNull final Course course) {
        this.courseRepository.save(this.getCourseEntity(course));
        return true;
    }

    @Override
    public List<Student> getAllStudents() {
        final List<Student> students = new ArrayList<>();
        final List<Students> studentsList = this.studentRepository.findAll();

        for (final Students studentsEntity : studentsList) {
            students.add(this.getStudentObject(studentsEntity));
        }
        return students;
    }

    @Override
    public Student getStudent(@NonNull final Id studentId) {
        final Students students = this.studentRepository.findById(studentId.getIdAsLong()).orElse(null);
        return this.getStudentObject(students);
    }

    @Override
    public boolean updateStudent(@NonNull final Student student) {
        final Students students = this.studentRepository.findById(student.studentId().getIdAsLong()).orElse(null);
        if (students == null) return false;

        students.setFirstName(student.firstName().toString());
        students.setLastName(student.lastName().toString());
        students.setEmail(student.mail().toString());

        this.studentRepository.save(students);
        return true;
    }

    @Override
    public boolean deleteStudent(@NonNull final Id studentId) {
        this.studentRepository.deleteById(studentId.getIdAsLong());
        return true;
    }

    @Override
    public Student addStudent(@NonNull final Student student) {
        final Students students = this.studentRepository.save(this.getStudentEntity(student));
        return this.getStudentObject(students);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        final List<Enrollment> enrollments = new ArrayList<>();
        final List<Enrollments> enrollmentsList = this.enrollmentRepository.findAll();

        for (final Enrollments enrollmentEntity : enrollmentsList) {
            enrollments.add(this.getEnrolmentObject(enrollmentEntity));
        }
        return enrollments;
    }

    @Override
    public boolean addEnrollment(@NonNull final Enrollment enrollment) {

        try {
            final Students student = studentRepository.findById(enrollment.student().studentId().getIdAsLong()).
                    orElseThrow(() -> new Exception("Student not found with id: " + enrollment.student().studentId()));
            final Courses courses = courseRepository.findById(enrollment.course().courseId().getIdAsLong())
                    .orElseThrow(() -> new Exception("Course not found with id: " + enrollment.course().courseId()));

            final Enrollments enrollments = new Enrollments();
            enrollments.setStudent(student);
            enrollments.setCourse(courses);

            this.enrollmentRepository.save(enrollments);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteEnrolment(@NonNull final Id enrollmentId) {
        this.enrollmentRepository.deleteById(enrollmentId.getIdAsLong());
        return true;
    }

    private Course getCourseObject(final Courses course){
        if(course == null) return null;

        return new Course(new Id(course.getCourseId()),new Name(course.getCourseName()),new Name(course.getCourseDescription()));
    }
    private Student getStudentObject(final Students students){
        if(students == null) return null;

        return new Student(new Id(students.getStudentId()),new Name(students.getFirstName()),new Name(students.getLastName()),
                new Mail.Builder(students.getEmail()).build());
    }
    private Enrollment getEnrolmentObject(final Enrollments enrollments){
        if(enrollments == null) return null;

        return new Enrollment(new Id(enrollments.getEnrollmentId()),this.getStudentObject(enrollments.getStudent()),this.getCourseObject(enrollments.getCourse()));
    }

    private Courses getCourseEntity(@NonNull final Course course){
        final Courses courses = new Courses();

        if(courses.getCourseId() != null) courses.setCourseId(course.courseId().getIdAsLong());
        courses.setCourseName(course.courseName().toString());
        courses.setCourseDescription(course.courseDescription().toString());

        return courses;
    }
    private Students getStudentEntity(@NonNull final Student student){
        final Students students = new Students();

        if(student.studentId() != null) students.setStudentId(student.studentId().getIdAsLong());
        students.setFirstName(student.firstName().toString());
        students.setLastName(student.lastName().toString());
        students.setEmail(student.mail().toString());

        return students;

    }
    private Enrollments getEnrollmentEntity(@NonNull final Enrollment enrollment){
        final Enrollments enrollments = new Enrollments();

        if(enrollments.getEnrollmentId() != null) enrollments.setEnrollmentId(enrollment.enrollmentId().getIdAsLong());
        enrollments.setCourse(this.getCourseEntity(enrollment.course()));
        enrollments.setStudent(this.getStudentEntity(enrollment.student()));

        return enrollments;
    }
}
