import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import './Homepage.css';

const Homepage = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedBrand, setSelectedBrand] = useState('Tümü');

  const brands = [
    'Tümü',
    'BMW',
    'Mercedes',
    'Audi',
    'Volkswagen',
    'Toyota',
    'Honda',
    'Ford',
    'Renault'
  ];

  const cars = [
    {
      id: 1,
      name: 'BMW 320i',
      brand: 'BMW',
      image: 'https://images.unsplash.com/photo-1555215695-300b0ca6ba4d?auto=format&fit=crop&w=800&q=80',
      price: '1200',
      year: '2022',
      transmission: 'Otomatik',
      fuel: 'Benzin',
      km: '15.000'
    },
    {
      id: 2,
      name: 'Mercedes C200',
      brand: 'Mercedes',
      image: 'https://images.unsplash.com/photo-1618843479313-40f8afb4b4d8?auto=format&fit=crop&w=800&q=80',
      price: '1500',
      year: '2023',
      transmission: 'Otomatik',
      fuel: 'Benzin',
      km: '8.000'
    },
    {
      id: 3,
      name: 'Audi A4',
      brand: 'Audi',
      image: 'https://images.unsplash.com/photo-1603584173870-7f23fdae1b7a?auto=format&fit=crop&w=800&q=80',
      price: '1300',
      year: '2022',
      transmission: 'Otomatik',
      fuel: 'Dizel',
      km: '20.000'
    }
  ];

  const filteredCars = cars.filter(car => {
    const matchesSearch = car.name.toLowerCase().includes(searchQuery.toLowerCase());
    const matchesBrand = selectedBrand === 'Tümü' /*|| car.brand === selectedBrand*/;
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
          {filteredCars.map(car => (
            <div key={car.id} className="car-card">
              <img src={car.image} alt={car.name} className="car-image" />
              <div className="car-details">
                <h3>{car.name}</h3>
                <div className="car-info">
                  <div className="car-info-item">
                    <i className="fas fa-calendar"></i>
                    <span>{car.year}</span>
                  </div>
                  <div className="car-info-item">
                    <i className="fas fa-cog"></i>
                    <span>{car.transmission}</span>
                  </div>
                  <div className="car-info-item">
                    <i className="fas fa-gas-pump"></i>
                    <span>{car.fuel}</span>
                  </div>
                  <div className="car-info-item">
                    <i className="fas fa-tachometer-alt"></i>
                    <span>{car.km} km</span>
                  </div>
                </div>
                <div className="car-price">
                  {car.price} TL <span>/ günlük</span>
                </div>
                <button className="rent-button">Kirala</button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Homepage;
