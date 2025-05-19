import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

function Header({ isLoggedIn }) {
  return (
    <header className="header">
      <div className="logo">Rental Car Company</div>
      <nav>
        <Link to="/home" className="nav-link">Home</Link>
        <Link to="/login" className="nav-link">Login</Link>
        {isLoggedIn && <Link to="/profile" className="nav-link">Profile</Link>}
      </nav>
    </header>
  );
}

export default Header;
