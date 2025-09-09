import React, { useEffect, useState } from 'react';
import { carService, brandService, colorService } from '../../services/api';
import { uploadImage } from '../../services/cloudinary';

const AdminCars = () => {
  const [items, setItems] = useState([]);
  const [form, setForm] = useState({ model: '', year: 2020, km: 0, gear_type: false, capacity: 4, img_url: '', fuel_type: 'GASOLINE', brand: null, color: null });
  const [uploading, setUploading] = useState(false);
  const [brands, setBrands] = useState([]);
  const [colors, setColors] = useState([]);

  const load = async () => {
    const { data } = await carService.getAll();
    const list = Array.isArray(data) ? data : (Array.isArray(data?.content) ? data.content : []);
    setItems(list);
  };

  useEffect(() => {
    load();
    (async () => {
      const [b, c] = await Promise.all([brandService.getAll(), colorService.getAll()]);
      setBrands(b.data || []);
      setColors(c.data || []);
    })();
  }, []);

  const onDelete = async (id) => {
    if (!window.confirm('Silmek istediğinize emin misiniz?')) return;
    await carService.delete(id);
    load();
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await carService.create(form);
    setForm({ model: '', year: 2020, km: 0, gear_type: false, capacity: 4, img_url: '', fuel_type: 'GASOLINE', brand: null, color: null });
    load();
  };

  return (
    <div>
      <h2 style={{ marginTop: 0 }}>Arabalar</h2>
      <form onSubmit={onSubmit} style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 8, marginBottom: 16, alignItems: 'center' }}>
        <input placeholder="Model" value={form.model} onChange={(e)=>setForm({...form, model:e.target.value})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="number" placeholder="Yıl" value={form.year} onChange={(e)=>setForm({...form, year:Number(e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="number" placeholder="KM" value={form.km} onChange={(e)=>setForm({...form, km:Number(e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <select value={form.fuel_type} onChange={(e)=>setForm({...form, fuel_type:e.target.value})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="GASOLINE">Benzin</option>
          <option value="DIESEL">Dizel</option>
          <option value="ELECTRIC">Elektrik</option>
          <option value="HYBRID">Hibrit</option>
        </select>
        <select value={form.brand?.id || form.brand?.brand_id || ''} onChange={(e)=>setForm({...form, brand: brands.find(b=> (b.id||b.brand_id) == e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="">Marka seçin</option>
          {brands.map(b => (<option key={b.id||b.brand_id} value={b.id||b.brand_id}>{b.brand_name||b.name}</option>))}
        </select>
        <select value={form.color?.id || form.color?.color_id || ''} onChange={(e)=>setForm({...form, color: colors.find(c=> (c.id||c.color_id) == e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="">Renk seçin</option>
          {colors.map(c => (<option key={c.id||c.color_id} value={c.id||c.color_id}>{c.color_name||c.name}</option>))}
        </select>
        <div style={{ display: 'flex', gap: 8 }}>
          <input placeholder="Görsel URL" value={form.img_url} onChange={(e)=>setForm({...form, img_url:e.target.value})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd', flex: 1 }} />
          <label style={{ display: 'inline-flex', alignItems: 'center', gap: 8, padding: '10px 12px', borderRadius: 8, background: '#e5e7eb', cursor: 'pointer' }}>
            <input type="file" accept="image/*" style={{ display: 'none' }} onChange={async (e)=>{
              const file = e.target.files?.[0];
              if (!file) return;
              setUploading(true);
              try {
                const url = await uploadImage(file);
                setForm((f)=>({ ...f, img_url: url }));
              } finally {
                setUploading(false);
              }
            }} />
            {uploading ? 'Yükleniyor...' : 'Resim Yükle'}
          </label>
        </div>
        <input type="number" placeholder="Kapasite" value={form.capacity} onChange={(e)=>setForm({...form, capacity:Number(e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <label style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
          <input type="checkbox" checked={form.gear_type} onChange={(e)=>setForm({...form, gear_type:e.target.checked})} /> Otomatik vites
        </label>
        <button type="submit" style={{ padding: '10px 16px', borderRadius: 8, background: '#4f46e5', color: '#fff', border: 'none' }}>Ekle</button>
      </form>
      <table style={{ width: '100%', borderCollapse: 'separate', borderSpacing: 0 }}>
        <thead>
          <tr style={{ background: '#f3f4f6' }}>
            <th style={{ textAlign: 'left', padding: 12 }}>car_id</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Model</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Yıl</th>
            <th style={{ textAlign: 'left', padding: 12 }}>KM</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Yakıt</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Vites</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Kapasite</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Görsel</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Marka</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Renk</th>
            <th style={{ textAlign: 'left', padding: 12 }}>Aksiyon</th>
          </tr>
        </thead>
        <tbody>
          {items.map((x) => (
            <tr key={x.id || x.car_id} style={{ borderBottom: '1px solid #eee' }}>
              <td style={{ padding: 12 }}>{x.car_id || x.id}</td>
              <td style={{ padding: 12 }}>{x.model}</td>
              <td style={{ padding: 12 }}>{x.year}</td>
              <td style={{ padding: 12 }}>{x.km}</td>
              <td style={{ padding: 12 }}>{x.fuel_type}</td>
              <td style={{ padding: 12 }}>{x.gear_type ? 'Oto' : 'Manuel'}</td>
              <td style={{ padding: 12 }}>{x.capacity}</td>
              <td style={{ padding: 12 }}>{x.img_url ? 'Var' : ''}</td>
              <td style={{ padding: 12 }}>{x.brand?.brand_name || x.brand?.name}</td>
              <td style={{ padding: 12 }}>{x.color?.color_name || x.color?.name}</td>
              <td style={{ padding: 12 }}>
                <button onClick={() => onDelete(x.id || x.car_id)} style={{ padding: '6px 10px', borderRadius: 6, background: '#ef4444', color: '#fff', border: 'none' }}>Sil</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminCars;
