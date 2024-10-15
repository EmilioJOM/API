import React from 'react';
import '../styles/Home.css';
import { Footer } from '../components/Footer';
import Banner from '../components/Banner';
import ProductGrid from '../components/ProductGrid';
import { Header } from '../components/Header';


export const Home = () => {
    const products = [
        // Aca deberia hacer un fetch al API y obtener los productos del back
        { id: 1, name: 'Guitarra Electrica', price: 38000, image: './assets/images/electrica.png' },
        { id: 2, name: 'Tambor', price: 12500, image: './assets/images/tambor.png' },
        { id: 3, name: 'Guitarra', price: 23600, image: './assets/images/guitarra.png' },
        { id: 4, name: 'Trompeta', price: 21200, image: './assets/images//trompeta.png' },
        { id: 5, name: 'Violin', price: 42000, image: './assets/images//trompeta.png' },
        { id: 6, name: 'Teclado', price: 58000, image: './assets/images//trompeta.png' },
      ];
    return (
        <>
        <Header />
            <div className="home">
            <Banner />
            <h1>Productos Destacados</h1>
            <p>Aca va una lista de tarjetas de productos destacados sacados de la base de datos.</p>
            <ProductGrid products={products} />  
            <h1>Todos los productos</h1>
            <p>Aca va un grid de categorias.</p> 
            
            <div className="contacto">
            <h3>Contacto</h3>
          <p><i className="fas fa-phone"></i> +11 1234 5678</p>
          <p><i className="fas fa-envelope"></i> soporte@atono.com</p>
            </div>
           
            </div>
            <Footer />
        </>
    );
};