import React, { useContext } from 'react'
import { NavLink } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faInfoCircle, faRightFromBracket} from '@fortawesome/free-solid-svg-icons'
import logo from '../assets/images/Logo.png'
import '../styles/Header.css'
import { AuthContext } from '../contexts/AuthContext'

export const UserHeader = () => {

    const { logout } = useContext(AuthContext);

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
                <NavLink to="/" onClick={logout} className={({ isActive }) => isActive ? 'nav-link active' : 'nav-link'}>
                    <FontAwesomeIcon icon={faRightFromBracket} className='icon' /> Logout
                </NavLink>
            </nav>
            </div>
            <div className='relleno'>
                <p>a </p>
            </div>
            <div onClick={() => setNav(!nav)} className="mobile_btn">
        {nav ? < AiOutlineClose size={25} />: <  AiOutlineMenu size ={25} />  }
    </div>
        </header>
    )
}
