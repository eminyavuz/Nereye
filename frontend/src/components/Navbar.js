import React, { useEffect, useState, useContext } from 'react';
import { Link } from 'react-router-dom';
import { userService } from '../services/api';
import './Navbar.css';
import { AuthContext } from '../context/AuthContext';

function Navbar({ siteName }) {
  const [scrolled, setScrolled] = useState(false);
  const [visible, setVisible] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);
  const { isLoggedIn, logout, isAdmin } = useContext(AuthContext);

  useEffect(() => {
    const handleScroll = () => {
      const currentScrollY = window.scrollY;
      if (currentScrollY > lastScrollY) {
        setVisible(false);
      } else {
        setVisible(true);
      }
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
    if (window.confirm('Çıkış yapmak istediğinize emin misiniz?')) {
      logout();
      alert('Çıkış yapıldı.');
    }
  };

  return (
    <nav className={`navbar${scrolled ? ' scrolled' : ''}${visible ? ' visible' : ' hidden'}`}>
      <div className="navbar-left">
        <div className="site-name">
          <Link to="/home">{siteName}</Link>
        </div>
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
            <Link to="/create-advertisement" className="nav-link">İlan Oluştur</Link>
            <Link to="/profile" className="nav-link">Profil</Link>
            {isAdmin && (
              <Link to="/admin" className="nav-link admin-link">Admin Panel</Link>
            )}
            <button onClick={handleLogout} className="nav-link logout-button">Çıkış Yap</button>
          </>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
