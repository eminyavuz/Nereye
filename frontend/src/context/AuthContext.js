import React, { createContext, useState, useEffect } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));
  const [userRole, setUserRole] = useState(localStorage.getItem('userRole') || 'USER');

  useEffect(() => {
    setIsLoggedIn(!!localStorage.getItem('token'));
    setUserRole(localStorage.getItem('userRole') || 'USER');
  }, []);

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
  };

  const isAdmin = userRole === 'ADMIN' || userRole === 1;
  console.log('AuthContext - userRole:', userRole, 'isAdmin:', isAdmin);

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout, userRole, isAdmin, updateRole }}>
      {children}
    </AuthContext.Provider>
  );
}; 