import React, { useEffect, useState } from 'react';
import { brandService } from '../../services/api';

const AdminBrands = () => {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ brand_name: '' });
  const [editing, setEditing] = useState({});

  const load = async () => {
    const { data } = await brandService.getAll();
    const list = Array.isArray(data) ? data : (Array.isArray(data?.content) ? data.content : []);
    setItems(list);
  };

  useEffect(() => { load(); }, []);

  const onDelete = async (id) => {
    if (!window.confirm('Silmek istediğinize emin misiniz?')) return;
    await brandService.delete(id);
    load();
  };

  const onSave = async (item) => {
    const id = item.id || item.brand_id;
    const draft = editing[id];
    if (!draft || !draft.brand_name) return;
    await brandService.update(id, { brand_name: draft.brand_name });
    setEditing((p)=>({ ...p, [id]: undefined }));
    load();
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await brandService.create(form);
    setForm({ brand_name: '' });
    load();
  };

  return (
    <div>
      <h2 style={{ marginTop: 0 }}>Markalar</h2>
      <form onSubmit={onSubmit} style={{ display: 'flex', gap: 8, marginBottom: 16 }}>
        <input placeholder="Marka adı" value={form.brand_name} onChange={(e)=>setForm({...form, brand_name:e.target.value})} style={{ flex: 1, padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <button type="submit" style={{ padding: '10px 16px', borderRadius: 8, background: '#4f46e5', color: '#fff', border: 'none' }}>Ekle</button>
      </form>
      <div style={{ overflow: 'auto' }}>
        <table style={{ width: '100%', borderCollapse: 'separate', borderSpacing: 0 }}>
          <thead>
            <tr style={{ background: '#f3f4f6' }}>
              <th style={{ textAlign: 'left', padding: 12 }}>ID</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Ad</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Aksiyon</th>
            </tr>
          </thead>
          <tbody>
            {items.map((x) => (
              <tr key={x.id || x.brand_id} style={{ borderBottom: '1px solid #eee' }}>
                <td style={{ padding: 12 }}>{x.id || x.brand_id}</td>
                <td style={{ padding: 12 }}>
                  <input
                    value={(editing[x.id || x.brand_id]?.brand_name) ?? (x.brand_name || x.name) }
                    onChange={(e)=>setEditing((p)=>({ ...p, [x.id || x.brand_id]: { brand_name: e.target.value } }))}
                    style={{ padding: 8, borderRadius: 6, border: '1px solid #ddd', width: '100%' }}
                  />
                </td>
                <td style={{ padding: 12, display: 'flex', gap: 8 }}>
                  <button onClick={() => onSave(x)} style={{ padding: '6px 10px', borderRadius: 6, background: '#10b981', color: '#fff', border: 'none' }}>Güncelle</button>
                  <button onClick={() => onDelete(x.id || x.brand_id)} style={{ padding: '6px 10px', borderRadius: 6, background: '#ef4444', color: '#fff', border: 'none' }}>Sil</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default AdminBrands;


