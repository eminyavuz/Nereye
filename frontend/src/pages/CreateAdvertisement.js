import React, { useState, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { advertisementService, carService, brandService, colorService } from '../services/api';
import { uploadImage } from '../services/cloudinary';
import './CreateAdvertisement.css';

const CreateAdvertisement = () => {
  const navigate = useNavigate();
  const fileInputRef = useRef(null);
  const [selectedImage, setSelectedImage] = useState(null);
  const [imagePreview, setImagePreview] = useState('');
  const [uploadingImage, setUploadingImage] = useState(false);
  const [formData, setFormData] = useState({
    daily_price: '',
    location: '',
    deposit: '',
    car: {
      fuel_type: 'BENZIN',
      km: '',
      gear_type: true,
      capacity: '',
      model: '',
      year: '',
      img_url: '',
      brand: { id: '' },
      color: { id: '' }
    }
  });

  const [brands, setBrands] = useState([]);
  const [colors, setColors] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchBrandsAndColors();
  }, []);

  const fetchBrandsAndColors = async () => {
    try {
      const [brandsResponse, colorsResponse] = await Promise.all([
        brandService.getAll(),
        colorService.getAll()
      ]);
      setBrands(brandsResponse.data);
      setColors(colorsResponse.data);
    } catch (error) {
      console.error('Marka ve renk yükleme hatası:', error);
      setError('Marka ve renk bilgileri yüklenirken hata oluştu.');
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (name.includes('.')) {
      const [parent, child] = name.split('.');
      setFormData(prev => ({
        ...prev,
        [parent]: {
          ...prev[parent],
          [child]: value
        }
      }));
    } else {
      setFormData(prev => ({
        ...prev,
        [name]: value
      }));
    }
  };

  const handleImageSelect = async (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedImage(file);
      
      // Create preview URL
      const reader = new FileReader();
      reader.onload = (e) => {
        setImagePreview(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const handleImageRemove = () => {
    setSelectedImage(null);
    setImagePreview('');
    setFormData(prev => ({
      ...prev,
      car: {
        ...prev.car,
        img_url: ''
      }
    }));
    if (fileInputRef.current) {
      fileInputRef.current.value = '';
    }
  };

  const handleImageButtonClick = () => {
    fileInputRef.current.click();
  };

  const uploadImageToCloudinary = async (file) => {
    setUploadingImage(true);
    try {
      console.log('Resim yükleniyor:', file.name, file.size);
      const imageUrl = await uploadImage(file);
      console.log('Yüklenen URL:', imageUrl);
      
      setFormData(prev => ({
        ...prev,
        car: {
          ...prev.car,
          img_url: imageUrl
        }
      }));
      return imageUrl;
    } catch (error) {
      console.error('Resim yükleme hatası:', error);
      setError(`Resim yüklenirken bir hata oluştu: ${error.message}`);
      throw error;
    } finally {
      setUploadingImage(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      // Resim yüklenmemişse hata ver
      if (!formData.car.img_url) {
        setError('Lütfen önce araç resmini yükleyin.');
        setLoading(false);
        return;
      }

      await advertisementService.create(formData);
      alert('İlan başarıyla oluşturuldu!');
      navigate('/home');
    } catch (error) {
      console.error('İlan oluşturma hatası:', error);
      setError('İlan oluşturulurken bir hata oluştu. Lütfen tekrar deneyin.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="create-advertisement-container">
      <div className="create-advertisement-card">
        <h2>Yeni İlan Oluştur</h2>
        
        {error && <div className="error-message">{error}</div>}
        
        <form onSubmit={handleSubmit} className="create-advertisement-form">
          <div className="form-section">
            <h3>İlan Bilgileri</h3>
            
            <div className="form-group">
              <label htmlFor="daily_price">Günlük Fiyat (TL)</label>
              <input
                type="number"
                id="daily_price"
                name="daily_price"
                value={formData.daily_price}
                onChange={handleInputChange}
                placeholder="Günlük fiyat"
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="location">Konum</label>
              <input
                type="text"
                id="location"
                name="location"
                value={formData.location}
                onChange={handleInputChange}
                placeholder="Şehir, ilçe"
                required
              />
            </div>

            <div className="form-group">
              <label htmlFor="deposit">Depozito (TL)</label>
              <input
                type="number"
                id="deposit"
                name="deposit"
                value={formData.deposit}
                onChange={handleInputChange}
                placeholder="Depozito miktarı"
                required
              />
            </div>
          </div>

          <div className="form-section">
            <h3>Araç Bilgileri</h3>
            
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="brand">Marka</label>
                <select
                  id="brand"
                  name="car.brand.id"
                  value={formData.car.brand.id}
                  onChange={handleInputChange}
                  required
                >
                  <option value="">Marka seçin</option>
                  {brands.map(brand => (
                    <option key={brand.id} value={brand.id}>
                      {brand.name || brand.brand_name}
                    </option>
                  ))}
                </select>
              </div>

              <div className="form-group">
                <label htmlFor="model">Model</label>
                <input
                  type="text"
                  id="model"
                  name="car.model"
                  value={formData.car.model}
                  onChange={handleInputChange}
                  placeholder="Araç modeli"
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="year">Yıl</label>
                <input
                  type="number"
                  id="year"
                  name="car.year"
                  value={formData.car.year}
                  onChange={handleInputChange}
                  placeholder="Üretim yılı"
                  min="1900"
                  max={new Date().getFullYear() + 1}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="km">Kilometre</label>
                <input
                  type="number"
                  id="km"
                  name="car.km"
                  value={formData.car.km}
                  onChange={handleInputChange}
                  placeholder="Kilometre"
                  required
                />
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="fuel_type">Yakıt Tipi</label>
                <select
                  id="fuel_type"
                  name="car.fuel_type"
                  value={formData.car.fuel_type}
                  onChange={handleInputChange}
                  required
                >
                  <option value="BENZIN">Benzin</option>
                  <option value="DIZEL">Dizel</option>
                  <option value="ELEKTRIK">Elektrik</option>
                  <option value="HYBRID">Hibrit</option>
                  <option value="LPG">LPG</option>
                </select>
              </div>

              <div className="form-group">
                <label htmlFor="gear_type">Vites Tipi</label>
                <select
                  id="gear_type"
                  name="car.gear_type"
                  value={formData.car.gear_type}
                  onChange={handleInputChange}
                  required
                >
                  <option value={true}>Otomatik</option>
                  <option value={false}>Manuel</option>
                </select>
              </div>
            </div>

            <div className="form-row">
              <div className="form-group">
                <label htmlFor="capacity">Kapasite</label>
                <input
                  type="number"
                  id="capacity"
                  name="car.capacity"
                  value={formData.car.capacity}
                  onChange={handleInputChange}
                  placeholder="Yolcu kapasitesi"
                  min="1"
                  max="10"
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="color">Renk</label>
                <select
                  id="color"
                  name="car.color.id"
                  value={formData.car.color.id}
                  onChange={handleInputChange}
                  required
                >
                  <option value="">Renk seçin</option>
                  {colors.map(color => (
                    <option key={color.id} value={color.id}>
                      {color.name || color.color_name}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            <div className="form-group image-picker-container">
              <label className="image-picker-label">Araç Resmi</label>
              <input
                ref={fileInputRef}
                type="file"
                accept="image/*"
                onChange={handleImageSelect}
                className="image-picker-input"
              />
              <button
                type="button"
                onClick={handleImageButtonClick}
                className="image-picker-button"
                disabled={uploadingImage}
              >
                <i className="fas fa-camera"></i>
                {uploadingImage ? 'Yükleniyor...' : (selectedImage ? 'Resmi Değiştir' : 'Resim Seç')}
              </button>
              
              {imagePreview && (
                <div className="image-preview">
                  <img src={imagePreview} alt="Araç önizleme" />
                  <div className="image-preview-actions">
                    <button
                      type="button"
                      onClick={async () => {
                        try {
                          await uploadImageToCloudinary(selectedImage);
                          alert('Resim başarıyla yüklendi!');
                        } catch (error) {
                          // Error already handled in uploadImageToCloudinary
                        }
                      }}
                      className="image-preview-upload"
                      disabled={uploadingImage}
                    >
                      {uploadingImage ? 'Yükleniyor...' : 'Cloudinary\'ye Yükle'}
                    </button>
                    <button
                      type="button"
                      onClick={handleImageRemove}
                      className="image-preview-remove"
                      disabled={uploadingImage}
                    >
                      Resmi Kaldır
                    </button>
                  </div>
                  {formData.car.img_url && (
                    <div className="image-url-info">
                      <small>Yüklenen URL: {formData.car.img_url}</small>
                    </div>
                  )}
                </div>
              )}
            </div>
          </div>

          <div className="form-actions">
            <button
              type="button"
              onClick={() => navigate('/home')}
              className="btn-secondary"
              disabled={loading}
            >
              İptal
            </button>
            <button
              type="submit"
              className="btn-primary"
              disabled={loading}
            >
              {loading ? 'Oluşturuluyor...' : 'İlan Oluştur'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CreateAdvertisement; 