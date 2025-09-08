import React, { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from '../components/Navbar';
import { userService } from '../services/api';
import './Login.css';
import { AuthContext } from '../context/AuthContext';

const Login = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    user_name: '',
    password: ''
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { isLoggedIn, login, updateRole } = useContext(AuthContext);

  useEffect(() => {
    if (isLoggedIn) {
      navigate('/');
    }
  }, [isLoggedIn, navigate]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const response = await userService.login(formData);
      if (response && response.data && response.data.token) {
        console.log('Gelen token:', response.data.token);
        
        // Önce token'ı kaydet
        login(response.data.token, 'USER'); // Geçici olarak USER rolü ver
        
        // Sonra kullanıcı bilgilerini al ve rolü güncelle
        try {
          console.log('getCurrentUser çağrısı yapılıyor...');
          const userInfo = await userService.getCurrentUser();
          console.log('getCurrentUser response:', userInfo);
          console.log('getCurrentUser data:', userInfo?.data);
          
          const userRole = userInfo?.data?.role || 'USER';
          console.log('Kullanıcı rolü (raw):', userRole, 'tip:', typeof userRole);
          
          // Eğer role sayısal değer ise (0 veya 1), string'e çevir
          const roleString = typeof userRole === 'number' ? (userRole === 1 ? 'ADMIN' : 'USER') : userRole;
          console.log('Dönüştürülmüş rol:', roleString);
          
          // Rolü güncelle
          updateRole(roleString);
          console.log('Giriş başarılı, rol güncellendi:', roleString);
        } catch (roleError) {
          console.log('Rol bilgisi alınamadı, varsayılan USER rolü kullanılıyor:', roleError);
        }
        
        navigate('/');
      } else {
        setError('Token alınamadı!');
      }
    } catch (err) {
      setError('Giriş başarısız. Lütfen bilgilerinizi kontrol edin.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-page">
      <Navbar siteName="Nereye" />
      <div className="login-container">
        <h2>Giriş Yap</h2>
        {error && <div className="error-message">{error}</div>}
        <form onSubmit={handleSubmit} className="login-form">
          <div className="input-group">
            <input
              type="text"
              id="user_name"
              name="user_name"
              value={formData.user_name}
              onChange={handleChange}
              placeholder=" "
              required
            />
            <label htmlFor="user_name">Kullanıcı Adı</label>
          </div>
          <div className="input-group">
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              placeholder=" "
              required
            />
            <label htmlFor="password">Şifre</label>
          </div>
          <button type="submit" className="login-button" disabled={loading}>
            {loading ? 'Giriş Yapılıyor...' : 'Giriş Yap'}
          </button>
        </form>
        <p className="register-link">
          Hesabınız yok mu? <a href="/register">Kayıt Ol</a>
        </p>
      </div>
    </div>
  );
};

export default Login;
