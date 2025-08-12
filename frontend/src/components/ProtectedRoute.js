import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const ProtectedRoute = ({ children }) => {
  const { isLoggedIn } = useContext(AuthContext);

  if (!isLoggedIn) {
    return (
      <div style={{ 
        minHeight: '100vh', 
        display: 'flex', 
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        background: "url('https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=1920&q=80') no-repeat center center/cover",
        position: 'relative'
      }}>
        <div style={{
          position: 'absolute',
          top: 0,
          left: 0,
          right: 0,
          bottom: 0,
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
          zIndex: 1
        }}></div>
        <div style={{
          zIndex: 2,
          textAlign: 'center',
          color: 'white',
          padding: '2rem',
          borderRadius: '10px',
          backgroundColor: 'rgba(0, 0, 0, 0.7)',
          maxWidth: '500px',
          margin: '0 auto'
        }}>
          <h2 style={{ marginBottom: '1rem', color: '#ff6b6b' }}>⚠️ Yetkiniz Yok!</h2>
          <p style={{ marginBottom: '1.5rem', fontSize: '1.1rem' }}>
            Bu sayfaya erişim için giriş yapmanız gerekmektedir.
          </p>
          <button 
            onClick={() => window.location.href = '/login'}
            style={{
              padding: '12px 24px',
              backgroundColor: '#007bff',
              color: 'white',
              border: 'none',
              borderRadius: '5px',
              cursor: 'pointer',
              fontSize: '1rem',
              fontWeight: 'bold',
              transition: 'background-color 0.3s'
            }}
            onMouseOver={(e) => e.target.style.backgroundColor = '#0056b3'}
            onMouseOut={(e) => e.target.style.backgroundColor = '#007bff'}
          >
            Giriş Yap
          </button>
        </div>
      </div>
    );
  }

  return children;
};

export default ProtectedRoute; 