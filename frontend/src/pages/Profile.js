import React, { useEffect, useState, useContext } from 'react';
import Navbar from '../components/Navbar';
import api from '../services/api';
import './Profile.css';
import { AuthContext } from '../context/AuthContext';

const Profile = () => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const { isLoggedIn } = useContext(AuthContext);

  // Token'dan username'i decode etmek için basit bir JWT decode fonksiyonu
  function parseJwt(token) {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      return null;
    }
  }

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        let username = '';
        if (token) {
          const decoded = parseJwt(token);
          username = decoded?.sub;
        }
        if (!username) {
          setUser(null);
          setLoading(false);
          return;
        }
        // Query parametresi ile GET isteği
        const response = await api.get(`/user/profile?username=${username}`);
        setUser(response.data);
      } catch (error) {
        setUser(null);
      } finally {
        setLoading(false);
      }
    };
    fetchProfile();
  }, []);

  if (loading) {
    return (
      <div className="profile-page">
        <Navbar siteName="Nereye" />
        <div className="profile-container">
          <h2>Profil</h2>
          <p>Yükleniyor...</p>
        </div>
      </div>
    );
  }

  if (!user) {
    return (
      <div className="profile-page">
        <Navbar siteName="Nereye" />
        <div className="profile-container">
          <h2>Profil</h2>
          <p>Profil bilgileri alınamadı.</p>
        </div>
      </div>
    );
  }

  return (
    <div className="profile-page" style={{ minHeight: '100vh', background: "url('https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=1920&q=80') no-repeat center center/cover", position: 'relative' }}>
      <div className="profile-bg-overlay"></div>
      <Navbar siteName="Nereye" />
      <div className="profile-container">
        <h2 className="profile-title">Profil Bilgilerim</h2>
        <div className="profile-card">
          <div className="profile-row">
            <span className="profile-label">Kullanıcı Adı:</span>
            <span className="profile-value">{user.user_name}</span>
          </div>
          <div className="profile-row">
            <span className="profile-label">Ad:</span>
            <span className="profile-value">{user.first_name}</span>
          </div>
          <div className="profile-row">
            <span className="profile-label">Soyad:</span>
            <span className="profile-value">{user.last_name}</span>
          </div>
          <div className="profile-row">
            <span className="profile-label">E-posta:</span>
            <span className="profile-value">{user.email}</span>
          </div>
          <div className="profile-row">
            <span className="profile-label">Telefon:</span>
            <span className="profile-value">{user.phone_number}</span>
          </div>
        </div>
        <button className="profile-logout-btn" onClick={() => { if(window.confirm('Çıkış yapmak istiyor musunuz?')) { localStorage.removeItem('token'); window.location.href = '/login'; } }}>Çıkış Yap</button>
      </div>
    </div>
  );
};

export default Profile;
