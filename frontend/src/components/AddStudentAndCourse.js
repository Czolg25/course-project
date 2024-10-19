import React, {Component } from "react";
import Connection from "./data/Connection";

class AddStudentAndCourse extends Component{
  constructor(props) {
    super(props); 

    this.state = {
      student: { firstName: "", lastName: "", email: "" },
      courses: [],
      selectedCourse: "",
    };

    this.connection = new Connection();
  }

  componentDidMount() {
    this.fetchCourses();
  }

  fetchCourses = async () => {
    try {
      const result = await this.connection.getCourses();
      this.setState({ courses: result });
    } catch (error) {
      console.error("Error fetching courses: ", error);
    }
  };

  handleChange = (e) => {
    const { name, value } = e.target;
    this.setState((prevState) => ({
      student: { ...prevState.student, [name]: value },
    }));
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newStudent = await this.connection.addStudent(this.state.student);
      await this.connection.addEnrolment(newStudent.studentId, this.state.selectedCourse);
      alert("Student added and enrolled!");
    } catch (err) {
      console.error("Error adding student: ", err);
      alert("Error adding student");
    }
    
  }

  handleCourseChange = (e) => {
    this.setState({ selectedCourse: e.target.value }); // Update selectedCourse in state
  };

  render(){
    if(this.state.courses.length === 0) return(
      <h1>No course exists!</h1>
    );
    return (
      <div>
        <h2>Add Student and Enroll in Course</h2>
        <form onSubmit={this.handleSubmit}>
          <input name="firstName" placeholder="First Name" value={this.state.student.firstName} onChange={this.handleChange} />
          <input name="lastName" placeholder="Last Name" value={this.state.student.lastName} onChange={this.handleChange} />
          <input name="email" placeholder="Email" value={this.state.student.email} onChange={this.handleChange} />
  
          <select value={this.state.selectedCourse} onChange={this.handleCourseChange}>
            <option value="">Select a Course</option>
            {this.state.courses.map((course) => (
              <option key={course.courseId} value={course.courseId}>
                {course.courseName}
              </option>
            ))}
          </select>
  
          <button type="submit">Submit</button>
        </form>
      </div>
    );
  }
}

export default AddStudentAndCourse;
