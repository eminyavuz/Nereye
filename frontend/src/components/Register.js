import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from './Navbar';
import { userService } from '../services/api';
import './Register.css';

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    user_name: '',
    password: '',
    email: '',
    phone_number: '',
    first_name: '',
    last_name: '',
    role: 'USER'
  });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    
    // Telefon numarası için özel kontrol
    if (name === 'phone_number') {
      // Sadece sayıları al
      const numbersOnly = value.replace(/[^0-9]/g, '');
      // Maksimum 10 karakter
      const limitedNumbers = numbersOnly.slice(0, 10);
      
      setFormData(prevState => ({
        ...prevState,
        [name]: limitedNumbers
      }));
    } else {
      setFormData(prevState => ({
        ...prevState,
        [name]: value
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const response = await userService.register(formData);
      if (response) {
        navigate('/login');
      }
    } catch (err) {
      setError('Kayıt işlemi başarısız. Lütfen bilgilerinizi kontrol edin.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-page">
      <Navbar siteName="Nereye" />
      <div className="register-container">
        <h2>Kayıt Ol</h2>
        {error && <div className="error-message">{error}</div>}
        <form className="register-form" onSubmit={handleSubmit}>
          <div className="input-group">
            <input
              type="text"
              id="first_name"
              placeholder=" "
              name="first_name"
              value={formData.first_name}
              onChange={handleChange}
              required
            />
            <label htmlFor="first_name">Ad</label>
          </div>
          <div className="input-group">
            <input
              type="text"
              id="last_name"
              placeholder=" "
              name="last_name"
              value={formData.last_name}
              onChange={handleChange}
              required
            />
            <label htmlFor="last_name">Soyad</label>
          </div>
          <div className="input-group">
            <input
              type="text"
              id="username"
              placeholder=" "
              name="user_name"
              value={formData.user_name}
              onChange={handleChange}
              required
            />
            <label htmlFor="username">Kullanıcı Adı</label>
          </div>
          <div className="input-group">
            <input
              type="email"
              id="email"
              placeholder=" "
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />
            <label htmlFor="email">E-posta</label>
          </div>
          <div className="input-group">
            <input
              type="password"
              id="password"
              placeholder=" "
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
            <label htmlFor="password">Şifre</label>
          </div>
          <div className="input-group">
            <input
              type="tel"
              id="phone"
              placeholder=" "
              name="phone_number"
              value={formData.phone_number}
              onChange={handleChange}
              maxLength="10"
              required
            />
            <label htmlFor="phone">Telefon (5XX XXX XX XX)</label>
          </div>
          <button type="submit" disabled={loading}>
            {loading ? 'Kayıt Yapılıyor...' : 'Kayıt Ol'}
          </button>
        </form>
        <p className="login-link">
          Zaten hesabınız var mı? <a href="/login">Giriş Yap</a>
        </p>
      </div>
    </div>
  );
};

export default Register; 