import React from "react";
import { NavLink } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome, faInfoCircle, faUserPlus, faSignInAlt,faSearch } from "@fortawesome/free-solid-svg-icons";
import logo from "../assets/images/Logo.png";
import "../styles/Header.css";
import {AiOutlineMenu, AiOutlineClose} from "react-icons/ai";
import {useState} from "react";

export const Header = () => {
    const [nav, setNav] = useState(false);
    return (
        <header className="header">
            <NavLink to="/" className="logo-container">
                <img src={logo} alt="ATono" className="logo-img" /> 
                <h2 className="h2">ATono</h2>
            </NavLink>
            
            <div className="menu">

            <div className="search-bar">
          <input type="text" placeholder="Buscar..." />
          <span className="search-icon">&#128269;</span> {}
        </div>
            <nav className= {nav ? ["menu", "active", "nav-link"].join(" "): ["menu"]}>
                <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>
                    <FontAwesomeIcon icon={faHome} className="icon" /> Inicio
                </NavLink>
                <NavLink to="/about-us" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>
                    <FontAwesomeIcon icon={faInfoCircle} className="icon" /> Sobre Nosotros
                </NavLink>
                <NavLink to="/register" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>
                    <FontAwesomeIcon icon={faUserPlus} className="icon" /> Registrarse
                </NavLink>
                <NavLink to="/login/options" className={({ isActive }) => isActive ? "nav-link active" : "nav-link"}>
                    <FontAwesomeIcon icon={faSignInAlt} className="icon" /> Iniciar Sesión
                </NavLink> 
                               
            </nav>
            </div>
            <div onClick={() => setNav(!nav)} className="mobile_btn">
        {nav ? < AiOutlineClose size={25} />: <  AiOutlineMenu size ={25} />  }
    </div>
        </header>
    );
};