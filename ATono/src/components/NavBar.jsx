import React from 'react';
import '../styles/NavBar.css'; 

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="nav-left"> {}
        <div className="logo">
        <a href="/">
            <img src={'/Logo.png'} alt="Logo" className="logo-img" /> 
          </a>
        </div>
        <button className="nav-btn">
          <span className="home-icon">&#8962;</span> {}
        </button>
      </div>
      <div className="nav-center">
        <div className="search-bar">
          <input type="text" placeholder="Buscar..." />
          <span className="search-icon">&#128269;</span> {}
        </div>
      </div>
      <div className="nav-right">
        <button className="nav-btn">Registrarse</button>
        <button className="nav-btn">Iniciar Sesión</button>
      </div>
    </nav>
  );
};

export default Navbar;