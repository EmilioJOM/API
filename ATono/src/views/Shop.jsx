import { useState } from 'react';
import ProductCard from '../components/ProductCard';
import "../styles/Shop.css";
import {Header } from '../components/Header';
import {Footer } from '../components/Footer';

const productos = [
    { 
        id: 1, 
        nombre: 'Auriculares', 
        precio: 100, 
        imagen: 'https://via.placeholder.com/200?text=Auriculares' 
      },
      { 
        id: 2, 
        nombre: 'Smartphone', 
        precio: 150, 
        imagen: 'https://via.placeholder.com/200?text=Smartphone' 
      },
      { 
        id: 3, 
        nombre: 'Reloj de Pulsera', 
        precio: 200, 
        imagen: 'https://via.placeholder.com/200?text=Reloj' 
      },
    ];

function Shop() {
  const [carrito, setCarrito] = useState([]);
  const [total, setTotal] = useState(0);

  const agregarAlCarrito = (producto) => {
    setCarrito((prev) => [...prev, producto]);
    setTotal((prev) => prev + producto.precio);
  };

  const eliminarDelCarrito = (index) => {
    const productoEliminado = carrito[index];
    setCarrito((prev) => prev.filter((_, i) => i !== index));
    setTotal((prev) => prev - productoEliminado.precio);
  };

  const realizarCompra = () => {
    if (carrito.length === 0) {
      alert('El carrito está vacío.');
    } else {
      alert(`Compra realizada por un total de $${total}`);
      setCarrito([]);
      setTotal(0);
    }
  };

  return (
    <>
    <Header/>
    <div className="contenedor">
      <h1>Ofertas destacadas</h1>
      <div className="productos-grid">
        {productos.map((producto) => (
          <ProductCard
            key={producto.id}
            producto={producto}
            onAgregar={() => agregarAlCarrito(producto)}
          />
        ))}
      </div>
      <div className="carrito">
        <h2>Carrito de Compras</h2>
        {carrito.length === 0 ? (
          <p>El carrito está vacío</p>
        ) : (
          <ul>
            {carrito.map((producto, index) => (
              <li key={index} className="carrito-item">
                <span>{producto.nombre} - ${producto.precio}</span>
                <button onClick={() => eliminarDelCarrito(index)}>Eliminar</button>
              </li>
            ))}
          </ul>
        )}
        <h3>Total: ${total}</h3>
        <button onClick={realizarCompra} className="btn-comprar">Comprar</button>
      </div>
    </div>
        <div><p className='relleno'></p>  </div>


    <Footer/>
    </>
  );
}

export default Shop;