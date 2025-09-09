import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import './Homepage.css';
import { advertisementService, brandService } from '../services/api';

const Homepage = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedBrand, setSelectedBrand] = useState('Tümü');
  const [brands, setBrands] = useState(['Tümü']);
  const [ads, setAds] = useState([]);

  useEffect(() => {
    (async () => {
      try {
        const [adsRes, brandsRes] = await Promise.all([
          advertisementService.getAll(),
          brandService.getAll()
        ]);
        const list = Array.isArray(adsRes.data) ? adsRes.data : (adsRes.data?.content || []);
        setAds(list);
        const brandList = Array.isArray(brandsRes.data) ? brandsRes.data : (brandsRes.data?.content || []);
        setBrands(['Tümü', ...brandList.map(b => b.brand_name || b.name).filter(Boolean)]);
      } catch (e) {
        setAds([]);
      }
    })();
  }, []);

  const getSafeImageUrl = (url) => {
    const placeholder = 'https://res.cloudinary.com/dqtkblhwr/image/upload/v1720000000/placeholder_car.png';
    if (!url || typeof url !== 'string') return placeholder;
    let u = url.trim();
    if (u.startsWith('http://')) u = u.replace('http://', 'https://');
    if (!u.startsWith('http')) {
      // Public ID ya da relatif değer olabilir → Cloudinary tam URL'ye çevir
      u = `https://res.cloudinary.com/dqtkblhwr/image/upload/${u}`;
    }
    return u;
  };

  const filteredAds = ads.filter(ad => {
    const carName = `${ad.car?.brand?.brand_name || ''} ${ad.car?.model || ''}`.trim();
    const matchesSearch = carName.toLowerCase().includes(searchQuery.toLowerCase());
    const matchesBrand = selectedBrand === 'Tümü' || (ad.car?.brand?.brand_name === selectedBrand || ad.car?.brand?.name === selectedBrand);
    return matchesSearch && matchesBrand;
  });

  return (
    <div className="homepage">
      <Navbar isLoggedIn={true} siteName="Nereye" fontFamily="Poppins" />
      <div className="banner">
        <h1>Sen Nereye istersen Oraya</h1>
        <p>En uygun fiyatlarla, en kaliteli araçları sizlerle buluşturuyoruz.</p>
      </div>

      <div className="search-container">
        <div className="search-bar">
          <i className="fas fa-search"></i>
          <input
            type="text"
            placeholder="Araç ara..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
        </div>
      </div>

      <div className="content-wrapper">
        <div className="brand-filter">
          <h3>Markalar</h3>
          <ul>
            {brands.map(brand => (
              <li
                key={brand}
                className={selectedBrand === brand ? 'active' : ''}
                onClick={() => setSelectedBrand(brand)}
              >
                {brand}
              </li>
            ))}
          </ul>
        </div>

        <div className="car-list">
          {filteredAds.map((ad) => {
            const name = `${ad.car?.brand?.brand_name || ad.car?.brand?.name || ''} ${ad.car?.model || ''}`.trim();
            return (
            <div key={ad.ad_id} className="car-card">
              <img
                src={getSafeImageUrl(ad.car?.img_url)}
                alt={name}
                className="car-image"
                onError={(e)=>{ e.currentTarget.src = getSafeImageUrl(''); }}
              />
              <div className="car-details">
                <h3>{name}</h3>
                <div className="car-info">
                  <div className="car-info-item">
                    <i className="fas fa-calendar"></i>
                    <span>{ad.car?.year}</span>
                  </div>
                  <div className="car-info-item">
                    <i className="fas fa-cog"></i>
                    <span>{ad.car?.gear_type ? 'Otomatik' : 'Manuel'}</span>
                  </div>
                  <div className="car-info-item">
                    <i className="fas fa-gas-pump"></i>
                    <span>{ad.car?.fuel_type}</span>
                  </div>
                  <div className="car-info-item">
                    <i className="fas fa-tachometer-alt"></i>
                    <span>{ad.car?.km} km</span>
                  </div>
                </div>
                <div className="car-price">
                  {ad.daily_price} TL <span>/ günlük</span>
                </div>
                <button className="rent-button">Kirala</button>
              </div>
            </div>
            );
          })}
        </div>
      </div>
    </div>
  );
};

export default Homepage;
