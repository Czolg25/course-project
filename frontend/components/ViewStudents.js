import React, { useState, useEffect } from "react";
import Connection from "./data/Connection"
import { NavLink } from 'react-router-dom';

function ViewStudents() {
  const [students, setStudents] = useState([]);
  const [filter, setFilter] = useState("");
  const [courses, setCourses] = useState([]);
  const connection = new Connection();

  useEffect(() => {
    const fetchStudents = async () => {
      const result = await connection.getStudents();
      setStudents(result);
    };
    const fetchCourses = async () => {
      const result = await connection.getCourses();
      setCourses(result);
    };
    fetchStudents();
    fetchCourses();
  }, []);

  const handleDelete = async (id) => {
    await connection.deleteEnrolment(id);
    setStudents(students.filter((student) => student.enrollmentId !== id));
  };

  const filteredStudents = filter
    ? students.filter((student) => student.course.courseId === parseInt(filter))
    : students;
    
  return (
    <div>
      <h2>View Students and Enrollments</h2>
      <select onChange={(e) => setFilter(e.target.value)} value={filter}>
        <option value="">All Courses</option>
        {courses.map((course) => (
          <option key={course.courseId} value={course.courseId}>
            {course.courseName}
          </option>
        ))}
      </select>

      <ul>
        {filteredStudents.map((student) => (
          <li key={student.enrollmentId}>
            {student.student.firstName} {student.student.lastName} - {student.course.courseName}
            <button onClick={() => handleDelete(student.enrollmentId)}>Delete</button>
            <NavLink to="/update-students" state={{ studentId: student.student.studentId }}>Update</NavLink>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ViewStudents;
