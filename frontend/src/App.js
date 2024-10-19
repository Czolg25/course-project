import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AddStudent from "./components/AddStudent";
import AddStudentAndCourse from "./components/AddStudentAndCourse";
import ViewStudents from "./components/ViewStudents";
import UpdateStudentAndCourse from "./components/UpdateStudent";
import Menu from "./components/Menu";

function App() {
  return (
    <Router>
      <Menu />
      <Routes>
        <Route path="/add-student" element={<AddStudent />} />
        <Route path="/add-student-course" element={<AddStudentAndCourse />} />
        <Route path="/view-students" element={<ViewStudents />} />
        <Route path="/update-students" element={<UpdateStudentAndCourse />}/>
      </Routes>
    </Router>
  );
}

export default App;
