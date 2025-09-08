import React from 'react';
import { Link, Outlet, useLocation } from 'react-router-dom';

const AdminLayout = () => {
  const location = useLocation();
  const isActive = (path) => location.pathname === path || location.pathname.startsWith(path + '/');
  const navItems = [
    { path: '/admin/ads', label: 'İlanlar', icon: '📢' },
    { path: '/admin/cars', label: 'Arabalar', icon: '🚗' },
    { path: '/admin/brands', label: 'Markalar', icon: '🏷️' },
    { path: '/admin/colors', label: 'Renkler', icon: '🎨' },
    { path: '/admin/users', label: 'Kullanıcılar', icon: '👤' }
  ];

  return (
    <div style={{ position: 'relative', minHeight: '100vh', paddingTop: 80, background: "url('https://images.unsplash.com/photo-1503376780353-7e6692767b70?auto=format&fit=crop&w=1920&q=80') no-repeat center center/cover" }}>
      <div style={{ position: 'absolute', inset: 0, background: 'rgba(0,0,0,0.45)' }} />
      <div style={{ position: 'relative', display: 'flex' }}>
      <aside style={{ width: 280, padding: 20, margin: 16, marginRight: 0, background: 'rgba(255,255,255,0.96)', backdropFilter: 'blur(6px)', borderRadius: 16, boxShadow: '0 8px 24px rgba(0,0,0,0.08)', border: '1px solid #e5e7eb' }}>
        <h3 style={{ marginTop: 4, marginBottom: 16, color: '#111827' }}>Admin Paneli</h3>
        <nav style={{ display: 'flex', flexDirection: 'column', gap: 10 }}>
          {navItems.map(item => {
            const active = isActive(item.path);
            return (
              <Link
                key={item.path}
                to={item.path}
                style={{
                  display: 'flex',
                  alignItems: 'center',
                  gap: 12,
                  padding: '12px 14px',
                  borderRadius: 12,
                  textDecoration: 'none',
                  color: active ? '#111827' : '#374151',
                  background: active ? '#eef2ff' : '#ffffff',
                  border: '1px solid #e5e7eb',
                  boxShadow: active ? 'inset 0 0 0 1px #c7d2fe' : 'none',
                  transition: 'background-color 0.2s ease, box-shadow 0.2s ease'
                }}
                onMouseEnter={(e)=>{ e.currentTarget.style.background = active ? '#eef2ff' : '#f9fafb'; }}
                onMouseLeave={(e)=>{ e.currentTarget.style.background = active ? '#eef2ff' : '#ffffff'; }}
              >
                <span style={{ fontSize: 18, width: 22, display: 'inline-flex', justifyContent: 'center' }}>{item.icon}</span>
                <span style={{ fontWeight: 600, letterSpacing: 0.2 }}>{item.label}</span>
              </Link>
            );
          })}
        </nav>
      </aside>
      <main style={{ flex: 1, padding: 24 }}>
        <div style={{ background: 'rgba(255,255,255,0.98)', borderRadius: 12, padding: 20, boxShadow: '0 6px 20px rgba(0,0,0,0.12)', backdropFilter: 'blur(6px)' }}>
          <Outlet />
        </div>
      </main>
      </div>
    </div>
  );
};

export default AdminLayout;


