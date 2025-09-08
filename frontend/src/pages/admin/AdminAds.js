import React, { useEffect, useState } from 'react';
import { advertisementService, carService } from '../../services/api';

const AdminAds = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [form, setForm] = useState({ daily_price: 0, location: '', deposit: 0, car: null });
  const [cars, setCars] = useState([]);

  const load = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await advertisementService.getAll();
      console.log('Advertisement API Response:', response);
      console.log('Advertisement Data:', response?.data);
      
      // Veri yapısını kontrol et ve array'e çevir
      let itemsData = [];
      if (Array.isArray(response?.data)) {
        itemsData = response.data;
      } else if (Array.isArray(response?.data?.content)) {
        itemsData = response.data.content;
      } else if (Array.isArray(response?.data?.items)) {
        itemsData = response.data.items;
      } else if (response?.data) {
        itemsData = [response.data];
      }
      
      console.log('Processed items:', itemsData);
      setItems(itemsData);
    } catch (e) {
      console.error('Advertisement load error:', e);
      setError('İlanlar yüklenemedi');
      setItems([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
    (async () => {
      try {
        const { data } = await carService.getAll();
        const list = Array.isArray(data) ? data : (Array.isArray(data?.content) ? data.content : []);
        setCars(list);
      } catch (e) {
        setCars([]);
      }
    })();
  }, []);

  const onDelete = async (id) => {
    if (!window.confirm('Silmek istediğinize emin misiniz?')) return;
    await advertisementService.delete(id);
    load();
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await advertisementService.create(form);
    setForm({ daily_price: 0, location: '', deposit: 0, car: null });
    load();
  };

  return (
    <div>
      <h2 style={{ marginTop: 0 }}>İlanlar</h2>
      <form onSubmit={onSubmit} style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 8, marginBottom: 16 }}>
        <input type="number" placeholder="Günlük Fiyat" value={form.daily_price} onChange={(e)=>setForm({...form, daily_price:Number(e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input placeholder="Konum" value={form.location} onChange={(e)=>setForm({...form, location:e.target.value})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="number" placeholder="Depozito" value={form.deposit} onChange={(e)=>setForm({...form, deposit:Number(e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <select value={form.car?.id || form.car?.car_id || ''} onChange={(e)=>setForm({...form, car: (cars || []).find(c=> (c.id||c.car_id) == e.target.value)})} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="">Araç seçin</option>
          {(cars || []).map(c => (<option key={c.id||c.car_id} value={c.id||c.car_id}>{c.model} - {c.brand?.brand_name || c.brand?.name}</option>))}
        </select>
        <button type="submit" style={{ padding: '10px 16px', borderRadius: 8, background: '#4f46e5', color: '#fff', border: 'none' }}>Ekle</button>
      </form>
      {loading ? 'Yükleniyor...' : error || (
        <table style={{ width: '100%', borderCollapse: 'collapse' }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Konum</th>
              <th>Günlük</th>
              <th>Araç</th>
              <th>Aksiyon</th>
            </tr>
          </thead>
          <tbody>
            {Array.isArray(items) ? items.map((x) => (
              <tr key={x.id || x.ad_id}>
                <td>{x.id || x.ad_id}</td>
                <td>{x.location}</td>
                <td>{x.daily_price}</td>
                <td>{x.car?.model}</td>
                <td>
                  <button onClick={() => onDelete(x.id || x.ad_id)}>Sil</button>
                </td>
              </tr>
            )) : (
              <tr>
                <td colSpan="5" style={{ textAlign: 'center', padding: '20px' }}>
                  Veri yükleniyor veya hata oluştu...
                </td>
              </tr>
            )}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AdminAds;


