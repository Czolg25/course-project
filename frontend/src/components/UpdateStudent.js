import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom"; 
import Connection from "./data/Connection";
import { useNavigate } from "react-router-dom";

function UpdateStudent() {
  const location = useLocation();
  const navigate = useNavigate();
  const { studentId } = location.state || {}; 
  const [student, setStudent] = useState({ firstName: "", lastName: "", email: "" });
  const connection = new Connection();

  useEffect(() => {
    const fetchStudent = async () => {
      if (studentId) {
        try {
          const result = await connection.getStudentById(studentId);
          setStudent(result);
        } catch (error) {
          console.error("Error fetching student:", error);
          alert("Failed to fetch student details.");
        }
      }
    };

    fetchStudent();
  }, [studentId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent({ ...student, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (studentId) {
        await connection.updateStudent(studentId,student)
        alert("Student updated successfully!");
        navigate("/view-students");
      } else {
        alert("No student ID provided.");
      }
    } catch (err) {
      console.error(err);
      alert("Error updating student");
    }
  };

  return (
    <div>
      <h2>Update Student</h2>
      <form onSubmit={handleSubmit}>
        <input
          name="firstName"
          placeholder="First Name"
          value={student.firstName}
          onChange={handleChange}
        />
        <input
          name="lastName"
          placeholder="Last Name"
          value={student.lastName}
          onChange={handleChange}
        />
        <input
          name="email"
          placeholder="Email"
          value={student.email}
          onChange={handleChange}
        />
        <button type="submit">Update Student</button>
      </form>
    </div>
  );
}

export default UpdateStudent;
