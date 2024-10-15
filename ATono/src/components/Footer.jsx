import React from "react";
import { Link } from "react-router-dom";
import "../styles/Footer.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faFacebook, faInstagram } from "@fortawesome/free-brands-svg-icons";

export const Footer = () => {
    return (
        <footer className="footer">
            <div className="terms-block">
                
                    <Link to="/terms" className="terms-text">Términos y condiciones.  </Link>
                    <Link to="/privacy-policy" className="terms-text">Política de privacidad.</Link>
                
            </div>
            <div className="copyright-block">
            &copy; {new Date().getFullYear()} ATono | Tu tienda musical en línea de confianza.
            </div>
            
            <div className="icons-block">
                <FontAwesomeIcon icon={faFacebook} className="icon" />
                <FontAwesomeIcon icon={faInstagram} className="icon" />
            </div>
        </footer>
    );
};