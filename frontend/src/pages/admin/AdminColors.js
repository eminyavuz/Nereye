import React, { useEffect, useState } from 'react';
import { colorService } from '../../services/api';

const AdminColors = () => {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ color_name: '', color_code: '#000000' });

  const load = async () => {
    const { data } = await colorService.getAll();
    const list = Array.isArray(data) ? data : (Array.isArray(data?.content) ? data.content : []);
    setItems(list);
  };

  useEffect(() => { load(); }, []);

  const onDelete = async (id) => {
    if (!window.confirm('Silmek istediğinize emin misiniz?')) return;
    await colorService.delete(id);
    load();
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await colorService.create(form);
    setForm({ color_name: '' });
    load();
  };

  return (
    <div>
      <h2 style={{ marginTop: 0 }}>Renkler</h2>
      <form onSubmit={onSubmit} style={{ display: 'flex', gap: 8, marginBottom: 16 }}>
        <input placeholder="Renk adı" value={form.color_name} onChange={(e)=>setForm({...form, color_name:e.target.value})} style={{ flex: 1, padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="color" value={form.color_code} onChange={(e)=>setForm({...form, color_code:e.target.value})} style={{ width: 50, height: 40, border: 'none', background: 'transparent' }} />
        <button type="submit" style={{ padding: '10px 16px', borderRadius: 8, background: '#4f46e5', color: '#fff', border: 'none' }}>Ekle</button>
      </form>
      <div style={{ overflow: 'auto' }}>
        <table style={{ width: '100%', borderCollapse: 'separate', borderSpacing: 0 }}>
          <thead>
            <tr style={{ background: '#f3f4f6' }}>
              <th style={{ textAlign: 'left', padding: 12 }}>ID</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Ad</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Renk</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Aksiyon</th>
            </tr>
          </thead>
          <tbody>
            {items.map((x) => (
              <tr key={x.id || x.color_id} style={{ borderBottom: '1px solid #eee' }}>
                <td style={{ padding: 12 }}>{x.id || x.color_id}</td>
                <td style={{ padding: 12 }}>{x.color_name || x.name}</td>
                <td style={{ padding: 12 }}>
                  <span style={{ display: 'inline-block', width: 18, height: 18, borderRadius: 4, background: x.color_code || '#000', marginRight: 8 }} />
                  {x.color_code}
                </td>
                <td style={{ padding: 12 }}>
                  <button onClick={() => onDelete(x.id || x.color_id)} style={{ padding: '6px 10px', borderRadius: 6, background: '#ef4444', color: '#fff', border: 'none' }}>Sil</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminColors;


