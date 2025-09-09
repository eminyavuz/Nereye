import React, { useEffect, useState } from 'react';
import { userService } from '../../services/api';

const AdminUsers = () => {
  const [items, setItems] = useState([]);
  const [editing, setEditing] = useState({});

  const load = async () => {
    const { data } = await userService.getAll();
    const list = Array.isArray(data) ? data : (Array.isArray(data?.content) ? data.content : []);
    setItems(list);
  };

  useEffect(() => { load(); }, []);

  const onDelete = async (id) => {
    if (!window.confirm('Silmek istediğinize emin misiniz?')) return;
    await userService.delete(id);
    load();
  };

  const onRoleChange = (id, role) => {
    setEditing((prev) => ({ ...prev, [id]: { ...(prev[id] || {}), role } }));
  };

  const onSave = async (user) => {
    const id = user.id || user.user_id;
    const next = editing[id];
    if (!next) return;
    await userService.update(id, { role: next.role || user.role });
    setEditing((prev) => ({ ...prev, [id]: undefined }));
    load();
  };

  return (
    <div>
      <h2 style={{ marginTop: 0 }}>Kullanıcılar</h2>
      <table style={{ width: '100%', borderCollapse: 'separate', borderSpacing: 0 }}>
        <thead>
          <tr style={{ background: '#f3f4f6' }}>
            <th style={{ textAlign: 'left', padding: 12 }}>ID</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Kullanıcı Adı</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Ad</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Soyad</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Rol</th>
            <th style={{ textAlign: 'left', padding: 12, minWidth: 160 }}>Aksiyon</th>
          </tr>
        </thead>
        <tbody>
          {items.map((x) => (
            <tr key={x.id || x.user_id} style={{ borderBottom: '1px solid #eee' }}>
              <td style={{ padding: 12 }}>{x.id || x.user_id}</td>
              <td style={{ padding: 12 }}>{x.user_name || x.username}</td>
              <td style={{ padding: 12 }}>{x.first_name}</td>
              <td style={{ padding: 12 }}>{x.last_name}</td>
              <td style={{ padding: 12 }}>
                <select
                  value={(editing[x.id || x.user_id]?.role) || x.role || 'USER'}
                  onChange={(e)=>onRoleChange(x.id || x.user_id, e.target.value)}
                  style={{ padding: 8, borderRadius: 6, border: '1px solid #ddd' }}
                >
                  <option value="ADMIN">ADMIN</option>
                  <option value="USER">USER</option>
                </select>
              </td>
              <td style={{ padding: 12, display: 'flex', gap: 8 }}>
                <button onClick={() => onSave(x)} style={{ padding: '6px 10px', borderRadius: 6, background: '#10b981', color: '#fff', border: 'none' }}>Güncelle</button>
                <button onClick={() => onDelete(x.id || x.user_id)} style={{ padding: '6px 10px', borderRadius: 6, background: '#ef4444', color: '#fff', border: 'none' }}>Sil</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminUsers;


