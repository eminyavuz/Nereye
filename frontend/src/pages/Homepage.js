import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import Banner from '../components/Banner';
import SearchBar from '../components/SearchBar';
import CarGrid from '../components/CarGrid';
import Footer from '../components/Footer';

const carBrands = [
  'Toyota',
  'Honda',
  'Ford',
  'Chevrolet',
  'BMW',
  'Audi',
  'Mercedes',
  'Volkswagen',
];

function Homepage({ isLoggedIn }) {
  const [searchQuery, setSearchQuery] = useState('');
  const [selectedBrand, setSelectedBrand] = useState('');

  const handleSearch = (query) => {
    setSearchQuery(query);
  };

  const handleBrandSelect = (brand) => {
    setSelectedBrand(brand);
    setSearchQuery(''); // reset search when brand selected
  };

  return (
    <>
      <div style={{ position: 'relative' }}>
        <Banner />
        <Navbar isLoggedIn={isLoggedIn} />
      </div>
      <div className="container flex flex-column" style={{ gap: '1.5rem', marginTop: '1rem' }}>
        <SearchBar onSearch={handleSearch} />
        <div className="flex" style={{ gap: '2rem', minHeight: '600px' }}>
          <aside style={{
            width: '400px',
            backgroundColor: '#f9fafb',
            borderRadius: '8px',
            padding: '1rem',
            boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
            maxHeight: '600px',
            overflowY: 'auto',
            fontWeight: '600',
            color: '#1e40af',
          }}>
            <h3>Car Brands</h3>
            <ul style={{ listStyle: 'none', padding: 0, margin: 0 }}>
              <li
                style={{
                  padding: '0.5rem',
                  cursor: 'pointer',
                  backgroundColor: selectedBrand === '' ? '#dbeafe' : 'transparent',
                  borderRadius: '4px',
                  marginBottom: '0.25rem',
                  transition: 'background-color 0.3s ease',
                }}
                onClick={() => handleBrandSelect('')}
              >
                All Brands
              </li>
              {carBrands.map((brand) => (
                <li
                  key={brand}
                  style={{
                    padding: '0.5rem',
                    cursor: 'pointer',
                    backgroundColor: selectedBrand === brand ? '#dbeafe' : 'transparent',
                    borderRadius: '4px',
                    marginBottom: '0.25rem',
                    transition: 'background-color 0.3s ease',
                  }}
                  onClick={() => handleBrandSelect(brand)}
                >
                  {brand}
                </li>
              ))}
            </ul>
          </aside>
          <main style={{ flex: 1, display: 'flex', justifyContent: 'center' }}>
            <CarGrid searchQuery={searchQuery} selectedBrand={selectedBrand} />
          </main>
        </div>
      </div>

      <Footer />
    </>
  );
}

export default Homepage;
