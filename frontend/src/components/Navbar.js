import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar({ isLoggedIn, siteName }) {
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      const offset = window.scrollY;
      if (offset > 50) {
        setScrolled(true);
      } else {
        setScrolled(false);
      }
    };

    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <nav className={`navbar${scrolled ? ' scrolled' : ''}`}>
      <div className="navbar-left">
        <div className="site-name">{siteName || 'Nereye'}</div>
      </div>
      <div className="navbar-right">
        <Link to="/home" className="nav-link">Home</Link>
        <Link to="/login" className="nav-link">Login</Link>
        {isLoggedIn && <Link to="/profile" className="nav-link">Profile</Link>}
      </div>
    </nav>
  );
}

export default Navbar;
