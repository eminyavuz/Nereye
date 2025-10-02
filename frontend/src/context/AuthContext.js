import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));
  const [userRole, setUserRole] = useState(localStorage.getItem('userRole') || 'USER');
  const [user, setUser] = useState(null);

  useEffect(() => {
    setIsLoggedIn(!!localStorage.getItem('token'));
    setUserRole(localStorage.getItem('userRole') || 'USER');
  }, []);

  // User bilgilerini yükle
  useEffect(() => {
    const fetchUserInfo = async () => {
      if (isLoggedIn) {
        try {
          const token = localStorage.getItem('token');
          if (token) {
            // JWT token'dan user bilgilerini decode et
            function parseJwt(token) {
              try {
                return JSON.parse(atob(token.split('.')[1]));
              } catch (e) {
                return null;
              }
            }
            
            const decoded = parseJwt(token);
            if (decoded) {
              // Backend'den user bilgilerini al
              const { userService } = await import('../services/api');
              const response = await userService.getById(decoded.userId || decoded.sub);
              setUser(response.data);
            }
          }
        } catch (error) {
          console.error('AuthContext - Kullanıcı bilgileri yüklenirken hata:', error);
        }
      } else {
        setUser(null);
      }
    };

    fetchUserInfo();
  }, [isLoggedIn]);

  const login = (token, role = 'USER') => {
    localStorage.setItem('token', token);
    localStorage.setItem('userRole', role);
    setIsLoggedIn(true);
    setUserRole(role);
  };

  const updateRole = (role) => {
    console.log('AuthContext - Rol güncelleniyor:', role);
    localStorage.setItem('userRole', role);
    setUserRole(role);
    console.log('AuthContext - Yeni rol:', role, 'isAdmin:', role === 'ADMIN' || role === 1);
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userRole');
    setIsLoggedIn(false);
    setUserRole('USER');
    setUser(null);
  };

  const isAdmin = userRole === 'ADMIN' || userRole === 1;
  console.log('AuthContext - userRole:', userRole, 'isAdmin:', isAdmin, 'user:', user);

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout, userRole, isAdmin, updateRole, user }}>
      {children}
    </AuthContext.Provider>
  );
}; 