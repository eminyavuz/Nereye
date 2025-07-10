import React, { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Homepage from './pages/Homepage';
import Profile from './pages/Profile';
import Register from './components/Register';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  return (
    <Routes>
      <Route path="/" element={<Navigate to="/home" replace />} />
      <Route path="/login" element={<Login onLogin={handleLogin} />} />
      <Route path="/register" element={<Register />} />
      <Route path="/home" element={<Homepage isLoggedIn={isLoggedIn} />} />
      <Route
        path="/profile"
        element={isLoggedIn ? <Profile /> : <Navigate to="/login" replace />}
      />
    </Routes>
  );
}

export default App;
