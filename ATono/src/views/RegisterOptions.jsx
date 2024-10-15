import React from 'react'
import { RegisterFooter } from '../components/RegisterFooter'
import '../styles/RegisterOptions.css'
import { Link, Navigate } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'

export const RegisterOptions = () => {

    const navigate = useNavigate();

    const handleComp = () => {
        return navigate("/register/comprador") 
    }

    const handleVend = () => {
        return navigate("/register/vendedor")
    }

    return (
        <div className="login">
            
                <div className='login_container'>
                    <div className='registerOptions'>
                        <h1><span style={{ color: '#e87910' }}>Bienvenido a ATono</span></h1>
                        <h2>Gracias por confiar en nosotros.</h2>
                        <h2>Por favor elegi como deseas registrarte:</h2>
                        <div className='loginBtn'>
                            <button onClick={handleComp}>Comprador</button>
                            <button onClick={handleVend}>Vendedor</button>
                        </div>
                        <p>¿Ya tenes una cuenta? <Link to="/login/options">Inicia sesión</Link></p>
                    </div>
                
                <RegisterFooter />
            </div>
        </div>
    )
}
