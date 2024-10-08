import React from 'react';
import '../styles/Home.css';
import Navbar from '../components/NavBar';
import Footer from '../components/Footer';
import Banner from '../components/Banner';
import ProductGrid from '../components/ProductGrid';

export const Home = () => {
    const products = [
        { id: 1, name: 'Guitarra Electrica', price: 38000, image: '/electrica.png'},
        { id: 2, name: 'Tambor', price: 12500, image:  '/tambor.png'},
        { id: 3, name: 'Guitarra', price: 23600, image:  '/guitarra.png'},
        { id: 4, name: 'Trompeta', price: 21200, image:  '/trompeta.png'},
      ];

    return (
        <>
        
            <Navbar />
            <div className="home">
            <h1>Bienvenido a Atono</h1>
            <Banner />
            <h1>Productos Destacados</h1>
      <ProductGrid products={products} />   
      <div className="espacio">
          </div>
            <Footer /> 
            </div>
            
        </>
    );
};