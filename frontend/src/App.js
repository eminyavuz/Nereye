import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Homepage from './pages/Homepage';
import Profile from './pages/Profile';
import Register from './components/Register';
import CreateAdvertisement from './pages/CreateAdvertisement';
import AdminLayout from './pages/admin/AdminLayout';
import AdminAds from './pages/admin/AdminAds';
import AdminCars from './pages/admin/AdminCars';
import AdminBrands from './pages/admin/AdminBrands';
import AdminColors from './pages/admin/AdminColors';
import AdminUsers from './pages/admin/AdminUsers';
import Navbar from './components/Navbar';
import ProtectedRoute from './components/ProtectedRoute';
import { AuthProvider } from './context/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Navbar siteName="Nereye" />
      <Routes>
        <Route path="/admin" element={
          <ProtectedRoute requireAdmin={true}>
            <AdminLayout />
          </ProtectedRoute>
        }>
          <Route index element={<AdminAds />} />
          <Route path="ads" element={<AdminAds />} />
          <Route path="cars" element={<AdminCars />} />
          <Route path="brands" element={<AdminBrands />} />
          <Route path="colors" element={<AdminColors />} />
          <Route path="users" element={<AdminUsers />} />
        </Route>
        <Route path="/" element={<Navigate to="/home" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Homepage />} />
        <Route path="/profile" element={
          <ProtectedRoute>
            <Profile />
          </ProtectedRoute>
        } />
        <Route path="/create-advertisement" element={
          <ProtectedRoute>
            <CreateAdvertisement />
          </ProtectedRoute>
        } />
      </Routes>
    </AuthProvider>
  );
}

export default App;
