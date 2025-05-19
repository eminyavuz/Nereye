import React from 'react';
import './CarGrid.css';

const cars = [
  { id: 1, name: 'Toyota Camry', brand: 'Toyota', price: '$50/day', image: 'https://via.placeholder.com/200x120?text=Toyota+Camry' },
  { id: 2, name: 'Honda Accord', brand: 'Honda', price: '$55/day', image: 'https://via.placeholder.com/200x120?text=Honda+Accord' },
  { id: 3, name: 'Ford Mustang', brand: 'Ford', price: '$80/day', image: 'https://via.placeholder.com/200x120?text=Ford+Mustang' },
  { id: 4, name: 'Chevrolet Malibu', brand: 'Chevrolet', price: '$60/day', image: 'https://via.placeholder.com/200x120?text=Chevrolet+Malibu' },
  { id: 5, name: 'BMW 3 Series', brand: 'BMW', price: '$90/day', image: 'https://via.placeholder.com/200x120?text=BMW+3+Series' },
  { id: 6, name: 'Audi A4', brand: 'Audi', price: '$95/day', image: 'https://via.placeholder.com/200x120?text=Audi+A4' },
];

function CarGrid({ searchQuery, selectedBrand }) {
  const filteredCars = cars.filter(car => {
    const matchesSearch = car.name.toLowerCase().includes(searchQuery.toLowerCase());
    const matchesBrand = selectedBrand ? car.brand === selectedBrand : true;
    return matchesSearch && matchesBrand;
  });

  return (
    <div className="car-grid">
      {filteredCars.map(car => (
        <div key={car.id} className="car-card">
          <img src={car.image} alt={car.name} />
          <div className="car-info">
            <h3>{car.name}</h3>
            <p>{car.price}</p>
          </div>
        </div>
      ))}
      {filteredCars.length === 0 && <p>No cars found.</p>}
    </div>
  );
}

export default CarGrid;
