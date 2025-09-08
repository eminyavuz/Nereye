import React, { useEffect, useState } from 'react';
import { userService } from '../../services/api';

const AdminUsers = () => {
  const [items, setItems] = useState([]);

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

  return (
    <div>
      <h2>Kullanıcılar</h2>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Kullanıcı Adı</th>
            <th>Ad</th>
            <th>Soyad</th>
            <th>Rol</th>
            <th>Aksiyon</th>
          </tr>
        </thead>
        <tbody>
          {items.map((x) => (
            <tr key={x.id || x.user_id}>
              <td>{x.id || x.user_id}</td>
              <td>{x.user_name || x.username}</td>
              <td>{x.first_name}</td>
              <td>{x.last_name}</td>
              <td>{x.role}</td>
              <td>
                <button onClick={() => onDelete(x.id || x.user_id)}>Sil</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminUsers;


