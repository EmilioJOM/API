import React from 'react';
import '../styles/ProductGrid.css';

const ProductGrid = ({ products }) => {
  return (
    <div className="product-grid">
      {products.map((product) => (
        <div key={product.id} className="product-card">
          <img src={product.image} alt={product.name} className="product-image" />
          <h3 className="product-name">{product.name}</h3>
          <p className="product-price">${product.price.toFixed(2)}</p>
          <button className="product-button">Agregar al carrito</button>
           </div>
      ))}
    </div>
  );
};

export default ProductGrid;