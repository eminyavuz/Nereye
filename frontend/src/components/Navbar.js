import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { userService } from '../services/api';
import './Navbar.css';

function Navbar({ siteName }) {
  const [scrolled, setScrolled] = useState(false);
  const [visible, setVisible] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem('token');
    setIsLoggedIn(!!token);
  }, []);

  useEffect(() => {
    const handleScroll = () => {
      const currentScrollY = window.scrollY;
      
      // Scroll yönünü belirle
      if (currentScrollY > lastScrollY) {
        // Aşağı scroll
        setVisible(false);
      } else {
        // Yukarı scroll
        setVisible(true);
      }

      // Navbar stilini güncelle
      if (currentScrollY > 50) {
        setScrolled(true);
      } else {
        setScrolled(false);
      }
      
      setLastScrollY(currentScrollY);
    };

    window.addEventListener('scroll', handleScroll, { passive: true });
    return () => window.removeEventListener('scroll', handleScroll);
  }, [lastScrollY]);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setIsLoggedIn(false);
  };

  return (
    <nav className={`navbar${scrolled ? ' scrolled' : ''}${visible ? ' visible' : ' hidden'}`}>
      <div className="navbar-left">
        <div className="site-name">{siteName || 'Nereye'}</div>
      </div>
      <div className="navbar-right">
        <Link to="/home" className="nav-link">Ana Sayfa</Link>
        {!isLoggedIn ? (
          <>
            <Link to="/login" className="nav-link">Giriş Yap</Link>
            <Link to="/register" className="nav-link">Kayıt Ol</Link>
          </>
        ) : (
          <>
            <Link to="/profile" className="nav-link">Profil</Link>
            <button onClick={handleLogout} className="nav-link logout-button">Çıkış Yap</button>
          </>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
