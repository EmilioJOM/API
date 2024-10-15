import React from 'react';
import '../styles/PrivacyPolicy.css';
import { Footer } from '../components/Footer';
import { Header } from '../components/Header';

const Terms = () => {
    return (
        <><Header />
        <div className='privacy-policy'>
            <h1>Terminos y Condiciones</h1>
            <p>Bienvenido a ATono. Al acceder y utilizar nuestro sitio web, usted acepta cumplir con los siguientes términos y condiciones. Si no está de acuerdo con estos términos, por favor, no utilice nuestro sitio web.</p>

            <h2>Uso del Sitio Web</h2>
            <h3>Elegibilidad</h3>
            <p>Al utilizar nuestro sitio web, usted declara que tiene al menos 18 años de edad y que tiene la capacidad legal para celebrar un contrato vinculante. Si utiliza el sitio web en nombre de una entidad, declara y garantiza que tiene la autoridad para aceptar estos términos en nombre de dicha entidad.</p>

            <h3>Propósito</h3>
            <p>Nuestro ecommerce ha sido diseñado para ofrecer a músicos, productores y amantes de la música una plataforma cómoda, segura y accesible donde puedan encontrar todo lo necesario para potenciar su talento. Queremos que descubras nuevos instrumentos, accedas a las mejores marcas y encuentres inspiración sin importar tu ubicación. Ya sea que estés iniciando tu camino en la música o perfeccionando tus habilidades, aquí tendrás acceso a recursos, productos y atención especializada que harán de tu experiencia algo memorable.</p>

            <h2>Registro y Seguridad de la Cuenta</h2>
            <p>Para utilizar ciertos servicios de nuestro sitio web, es posible que deba registrarse y crear una cuenta. Usted es responsable de mantener la confidencialidad de su información de inicio de sesión y de todas las actividades que ocurran bajo su cuenta. Debe notificarnos inmediatamente sobre cualquier uso no autorizado de su cuenta.</p>

            <h2>Conducta del Usuario</h2>
            <p>Usted se compromete a no utilizar nuestro sitio web para:</p>
            <li>Publicar, transmitir o distribuir contenido ilegal, dañino, amenazante, abusivo, acosador, difamatorio, obsceno o de cualquier otra forma objetable.</li>
            <li>Suplantar a cualquier persona o entidad o falsificar su afiliación con una persona o entidad.</li>
            <li>Interferir o interrumpir el funcionamiento del sitio web o los servidores o redes conectados al sitio web.</li>
            <li>Recopilar o almacenar datos personales de otros usuarios sin su consentimiento expreso.</li>
             <p className='relleno'></p>   

        </div><Footer /></>
    );
};

export default Terms;
