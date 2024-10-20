import React, { Component } from "react";
import Connection from "./data/Connection";

class AddStudent extends Component{

  constructor(props) {
    super(props);

    const connection = new Connection();
    
    this.state = {
      student: {
        firstName: "",
        lastName: "",
        email: "",
      },
    };
    this.handleChange = (e) => {
      const { name, value } = e.target;
      this.setState((prevState) => ({
        student: {
          ...prevState.student,
          [name]: value,
        },
      }));
    };

    this.handleSubmit = async (e) => {
      e.preventDefault();
      try {
        await connection.addStudent(this.state.student);
        alert("Student added!");
      } catch (err) {
        console.error(err);
        alert("Error adding student");
      }
    };
  }

  render(){
    const { student } = this.state;

    return (
      <div>
        <h2>Add Student</h2>
        <form onSubmit={this.handleSubmit}>
          <input name="firstName" placeholder="First Name" value={student.firstName} onChange={this.handleChange} />
          <input name="lastName" placeholder="Last Name" value={student.lastName} onChange={this.handleChange} />
          <input name="email" placeholder="Email" value={student.email} onChange={this.handleChange} />
          <button type="submit">Submit</button>
        </form>
      </div>
    );
  }
}

export default AddStudent;