import React from 'react';
import { NavLink } from 'react-router-dom';

const Menu = () => {
  return (
    <nav>
      <ul>
        <li>
          <NavLink to="/add-student">Add student</NavLink>
        </li>
        <li>
          <NavLink to="/add-student-course">Add student with course</NavLink>
        </li>
        <li>
          <NavLink to="/update-students" state={{ studentId: 2 }}>Update student with course</NavLink>
        </li>
        <li>
          <NavLink to='/view-students'>View students</NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default Menu;