import React from "react";
import "../styles/Banner.css";

const Banner = () => {
  return (
    <div className="banner">
      <div className="banner-content">
        <h1>¡Ofertas exclusivas de inauguración!</h1>
        <p>Descuentos de hasta el 50% en productos seleccionados. ¡No te lo pierdas!</p>
        <div className="banner-buttons">
          <a href="/shop" className="btn-primary">Ver Ofertas</a>
          <a href="/shop" className="btn-secondary">Comprar Ahora</a>
        </div>
      </div>
    </div>
  );
};

export default Banner;