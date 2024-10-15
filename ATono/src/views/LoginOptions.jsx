import React from 'react'
import { RegisterFooter } from '../components/RegisterFooter'
import '../styles/RegisterOptions.css'
import { useNavigate } from 'react-router-dom'

export const LoginOptions = () => {

    const navigate = useNavigate();

    const handleComp = () => {
        return navigate("/login") 
    }

    const handleVend = () => {
        return navigate("/login/vendedor")
    }

    return (
        
        <div className="login">
                <div className='login_container'>
                    <div className='registerOptions'>
                        <h1><span style={{ color: '#e87910' }}>Bienvenido a ATono</span></h1>
                        <h2>Gracias por confiar en nosotros.</h2>
                        <h2>Por favor elegi como deseas iniciar sesión:</h2>
                        <div className='loginBtn'>
                            <button onClick={handleComp}>Comprador</button>
                            <button onClick={handleVend}>Vendedor</button>
                        </div>
                    </div>
                <RegisterFooter />
            </div>
        </div>
    )
}
