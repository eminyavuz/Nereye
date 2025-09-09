import React, { useEffect, useRef, useState } from 'react';
import { advertisementService, brandService, colorService } from '../../services/api';
import { uploadImage } from '../../services/cloudinary';

const AdminAds = () => {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [brands, setBrands] = useState([]);
  const [colors, setColors] = useState([]);
  const fileInputRef = useRef(null);
  const [selectedImage, setSelectedImage] = useState(null);
  const [uploadingImage, setUploadingImage] = useState(false);
  const [imagePreview, setImagePreview] = useState('');
  const [form, setForm] = useState({
    daily_price: '',
    location: '',
    deposit: '',
    car: {
      fuel_type: 'Benzinli',
      km: '',
      gear_type: true,
      capacity: '',
      model: '',
      year: '',
      img_url: '',
      brand: {},
      color: {}
    }
  });

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
        const [b, c] = await Promise.all([brandService.getAll(), colorService.getAll()]);
        setBrands(Array.isArray(b.data) ? b.data : (b.data?.content || []));
        setColors(Array.isArray(c.data) ? c.data : (c.data?.content || []));
      } catch (_) {
        setBrands([]);
        setColors([]);
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
    if (!form.car.img_url) {
      alert('Lütfen araç resmi yükleyin.');
      return;
    }
    await advertisementService.create(form);
    setForm({
      daily_price: '',
      location: '',
      deposit: '',
      car: { fuel_type: 'Benzinli', km: '', gear_type: true, capacity: '', model: '', year: '', img_url: '', brand: {}, color: {} }
    });
    setSelectedImage(null);
    setImagePreview('');
    if (fileInputRef.current) fileInputRef.current.value = '';
    load();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    const coerceBoolean = (v) => (v === true || v === 'true');
    const path = name.split('.');
    if (path[0] === 'car') {
      setForm((prev) => {
        const nextCar = { ...prev.car };
        if (path.length === 2) {
          const field = path[1];
          if (field === 'gear_type') nextCar.gear_type = coerceBoolean(value);
          else if (['km', 'capacity', 'year'].includes(field)) nextCar[field] = value === '' ? '' : Number(value);
          else if (['fuel_type', 'model', 'img_url'].includes(field)) nextCar[field] = value;
        }
        if (path.length === 3) {
          const obj = path[1];
          const key = path[2];
          if (obj === 'brand' && key === 'id') nextCar.brand = { id: value === '' ? '' : Number(value) };
          if (obj === 'color' && (key === 'color_id' || key === 'id')) nextCar.color = { color_id: value === '' ? '' : Number(value) };
        }
        return { ...prev, car: nextCar };
      });
      return;
    }
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const onImageSelect = (e) => {
    const file = e.target.files?.[0];
    if (!file) return;
    setSelectedImage(file);
    const reader = new FileReader();
    reader.onload = (ev) => setImagePreview(ev.target.result);
    reader.readAsDataURL(file);
  };

  const onUpload = async () => {
    if (!selectedImage) return;
    setUploadingImage(true);
    try {
      const url = await uploadImage(selectedImage);
      setForm((prev) => ({ ...prev, car: { ...prev.car, img_url: url } }));
      alert('Resim yüklendi');
    } finally {
      setUploadingImage(false);
    }
  };

  return (
    <div>
      <h2 style={{ marginTop: 0 }}>İlanlar</h2>
      <form onSubmit={onSubmit} style={{ display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 8, marginBottom: 16 }}>
        <input type="number" placeholder="Günlük Fiyat" name="daily_price" value={form.daily_price} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input placeholder="Konum" name="location" value={form.location} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="number" placeholder="Depozito" name="deposit" value={form.deposit} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <div />
        <select id="brand" name="car.brand.id" value={form.car.brand.id || ''} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="">Marka seçin</option>
          {brands.map((b)=> (
            <option key={b.id || b.brand_id} value={b.id || b.brand_id}>{b.name || b.brand_name}</option>
          ))}
        </select>
        <input type="text" placeholder="Model" name="car.model" value={form.car.model} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="number" placeholder="Yıl" name="car.year" value={form.car.year} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <input type="number" placeholder="KM" name="car.km" value={form.car.km} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <select id="fuel_type" name="car.fuel_type" value={form.car.fuel_type} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="Benzinli">Benzinli</option>
          <option value="Dizel">Dizel</option>
          <option value="Elektrikli">Elektrikli</option>
          <option value="LPG">LPG</option>
        </select>
        <select id="gear_type" name="car.gear_type" value={form.car.gear_type} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="true">Otomatik</option>
          <option value="false">Manuel</option>
        </select>
        <input type="number" placeholder="Kapasite" name="car.capacity" value={form.car.capacity} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }} />
        <select id="color" name="car.color.color_id" value={form.car.color.color_id || ''} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd' }}>
          <option value="">Renk seçin</option>
          {colors.map((c)=> (
            <option key={c.id || c.color_id} value={c.id || c.color_id}>{c.color_name || c.name}</option>
          ))}
        </select>
        <div style={{ display: 'flex', gap: 8, alignItems: 'center' }}>
          <input name="car.img_url" placeholder="Görsel URL" value={form.car.img_url} onChange={handleChange} style={{ padding: 10, borderRadius: 8, border: '1px solid #ddd', flex: 1 }} />
          <input ref={fileInputRef} type="file" accept="image/*" onChange={onImageSelect} style={{ display: 'none' }} />
          <button type="button" onClick={()=>fileInputRef.current?.click()} style={{ padding: '10px 12px', borderRadius: 8, background: '#e5e7eb', border: 'none' }}>Resim Seç</button>
          <button type="button" onClick={onUpload} disabled={uploadingImage || !selectedImage} style={{ padding: '10px 12px', borderRadius: 8, background: '#4f46e5', color: '#fff', border: 'none' }}>{uploadingImage ? 'Yükleniyor...' : 'Yükle'}</button>
        </div>
        <button type="submit" style={{ padding: '10px 16px', borderRadius: 8, background: '#4f46e5', color: '#fff', border: 'none' }}>Ekle</button>
      </form>
      {imagePreview && (
        <div style={{ marginBottom: 16 }}>
          <img src={imagePreview} alt="Önizleme" style={{ height: 120, borderRadius: 8 }} />
          {form.car.img_url && <div><small>Yüklenen URL: {form.car.img_url}</small></div>}
        </div>
      )}
      {loading ? 'Yükleniyor...' : error || (
        <table style={{ width: '100%', borderCollapse: 'separate', borderSpacing: 0 }}>
          <thead>
            <tr style={{ background: '#f3f4f6' }}>
              <th style={{ textAlign: 'left', padding: 12 }}>ad_id</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Konum</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Günlük</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Araç</th>
              <th style={{ textAlign: 'left', padding: 12 }}>Aksiyon</th>
            </tr>
          </thead>
          <tbody>
            {Array.isArray(items) ? items.map((x) => (
              <tr key={x.id || x.ad_id} style={{ borderBottom: '1px solid #eee' }}>
                <td style={{ padding: 12 }}>{x.ad_id || x.id}</td>
                <td style={{ padding: 12 }}>{x.location}</td>
                <td style={{ padding: 12 }}>{x.daily_price}</td>
                <td style={{ padding: 12 }}>{x.car?.model}</td>
                <td style={{ padding: 12 }}>
                  <button onClick={() => onDelete(x.id || x.ad_id)} style={{ padding: '6px 10px', borderRadius: 6, background: '#ef4444', color: '#fff', border: 'none' }}>Sil</button>
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


