import React from "react";
import '../styles/Footer.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faInstagram } from "@fortawesome/free-brands-svg-icons";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-section about">
          <p>
          Tu tienda en línea de confianza. Ofrecemos una gran variedad de instrumentos musicales de alta calidad a precios competitivos.
          </p>
        </div>
        <div className="footer-bottom">

        <div className="footer-marca">
        &copy; {new Date().getFullYear()} ATono | Terminos y condiciones
      </div>

        <div className="footer-section contact">
          <h3>Contacto</h3>
          <p><i className="fas fa-phone"></i> +11 1234 5678</p>
          <p><i className="fas fa-envelope"></i> soporte@atono.com</p>
        </div>
        
      <div className="footer-redes">
            <p>Seguinos en:</p>
            <div className="social-icons">
            <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">
            <FontAwesomeIcon icon={faFacebook} />
          </a>
          <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">
            <FontAwesomeIcon icon={faInstagram} />
          </a>
            </div>
          </div>
        </div>
        </div>
    </footer>
  );
};

export default Footer;