package pl.dykacz.courses.courses.data.sql;

import org.springframework.lang.NonNull;
import pl.dykacz.courses.courses.configurations.SQLConfiguration;
import pl.dykacz.courses.courses.objects.Course;
import pl.dykacz.courses.courses.objects.Enrollment;
import pl.dykacz.courses.courses.objects.Student;
import pl.dykacz.courses.courses.objects.values.Id;
import pl.dykacz.courses.courses.objects.values.Mail;
import pl.dykacz.courses.courses.objects.values.Name;
import pl.dykacz.courses.courses.repositories.DataRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class SQLQueries implements DataRepository {
    protected final SQLConfiguration sqlConfiguration;

    public SQLQueries(@NonNull final SQLConfiguration sqlConfiguration) {
        this.sqlConfiguration = sqlConfiguration;
    }

    public List<Course> getAllCourses() {
        final List<Course> courses = new ArrayList<>();

        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT course_id,course_name, course_description FROM courses");
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                courses.add(new Course(new Id(resultSet.getLong("course_id")),new Name(resultSet.getString("course_name")),
                        new Name(resultSet.getString("course_description"))));
            }
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return courses;
    }

    public Course getCourse(@NonNull final Id courseId) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT course_id,course_name, course_description" +
                    " FROM courses WHERE course_id=?");
            preparedStatement.setLong(1,courseId.getIdAsLong());
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Course(new Id(resultSet.getLong("course_id")),new Name(resultSet.getString("course_name")),
                        new Name(resultSet.getString("course_description")));
            }
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public boolean updateCourse(@NonNull final Course course) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE courses SET course_name=?,course_description=? WHERE course_id=?");
            preparedStatement.setString(1,course.courseName().toString());
            preparedStatement.setString(2,course.courseDescription().toString());
            preparedStatement.setLong(3,course.courseId().getIdAsLong());

            preparedStatement.executeUpdate();

            connection.commit();

            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    public boolean deleteCourse(@NonNull final Id curseId) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM courses WHERE course_id=?");
            preparedStatement.setLong(1,curseId.getIdAsLong());

            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean addCourse(@NonNull final Course course) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses(course_name,course_description) VALUES(?,?)");

            preparedStatement.setString(1,course.courseName().toString());
            preparedStatement.setString(2,course.courseDescription().toString());
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }


    public List<Student> getAllStudents() {
        final List<Student> students = new ArrayList<>();

        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT student_id, first_name, last_name, email FROM students");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(new Student(new Id(resultSet.getLong("student_id")),new Name(resultSet.getString("first_name")),
                        new Name(resultSet.getString("last_name")),new Mail.Builder("email").build()));
            }
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return students;
    }
    public Student getStudent(@NonNull final Id studentId) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT student_id, first_name, last_name, email FROM students WHERE student_id=?");
            preparedStatement.setLong(1,studentId.getIdAsLong());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Student(new Id(resultSet.getLong("student_id")),new Name(resultSet.getString("first_name")),
                        new Name(resultSet.getString("last_name")),new Mail.Builder(resultSet.getString("email")).build());
            }
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
    public boolean updateStudent(@NonNull final Student student) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET first_name=?,last_name=?,email=? WHERE student_id=?");
            preparedStatement.setString(1,student.firstName().toString());
            preparedStatement.setString(2,student.lastName().toString());
            preparedStatement.setString(3,student.mail().toString());
            preparedStatement.setLong(4,student.studentId().getIdAsLong());

            preparedStatement.executeUpdate();

            connection.commit();

            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
    public boolean deleteStudent(@NonNull final Id studentId) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE student_id=?");
            preparedStatement.setLong(1,studentId.getIdAsLong());

            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public Student addStudent(@NonNull final Student student) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students(first_name,last_name,email) VALUES(?,?,?) RETURNING student_id");

            preparedStatement.setString(1,student.firstName().toString());
            preparedStatement.setString(2,student.lastName().toString());
            preparedStatement.setString(3,student.mail().toString());
            final ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()) {
                final Id studentId = new Id(resultSet.getLong("student_id"));
                connection.commit();

                return new Student(studentId,student.firstName(),student.lastName(),student.mail());
            }

            return null;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public List<Enrollment> getAllEnrollments() {
        final List<Enrollment> enrollments = new ArrayList<>();

        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM enrollments INNER JOIN students ON enrollments.student_id = students.student_id " +
                    "INNER JOIN courses ON enrollments.course_id = courses.course_id");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final Student student = new Student(new Id(resultSet.getLong("student_id")),new Name(resultSet.getString("first_name")),
                        new Name(resultSet.getString("last_name")),new Mail.Builder(resultSet.getString("email")).build());
                final Course course = new Course(new Id(resultSet.getLong("course_id")),new Name(resultSet.getString("course_name")),
                        new Name(resultSet.getString("course_description")));
                final Id enrollmentId = new Id(resultSet.getLong("enrollment_id"));

                final Enrollment enrollment = new Enrollment(enrollmentId,student,course);
                enrollments.add(enrollment);
            }
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }

        return enrollments;
    }

    public boolean addEnrollment(@NonNull final Enrollment enrollment) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO enrollments (student_id,course_id) VALUES(?,?)");

            preparedStatement.setLong(1,enrollment.student().studentId().getIdAsLong());
            preparedStatement.setLong(2,enrollment.course().courseId().getIdAsLong());
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
    public boolean deleteEnrolment(@NonNull final Id enrollmentId) {
        try(final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM enrollments WHERE enrollment_id=?");
            preparedStatement.setLong(1,enrollmentId.getIdAsLong());

            preparedStatement.executeUpdate();

            connection.commit();
            return true;
        }catch (final SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    protected abstract Connection getConnection() throws SQLException;
}
