import React from 'react'
import { Link } from 'react-router-dom'
import '../styles/RegisterFooter.css'

export const RegisterFooter = () => {
    return (
        <>
            <footer className="register_footer">
                <p><span style={{ color: '#e87910' }}>{new Date().getFullYear()}  ATono.</span> Todos los derechos reservados.</p>
                <Link to="/terms">Términos y condiciones</Link>
            </footer>
        </>
    )
}
