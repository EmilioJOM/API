import React from 'react';
import '../styles/PrivacyPolicy.css';
import { Footer } from '../components/Footer';
import { Header } from '../components/Header';

const PrivacyPolicy = () => {
    return (
        <><Header />
        <div className='privacy-policy'>
            <h1>Política de Privacidad</h1>
            <p>En ATono, nos comprometemos a proteger la privacidad y la seguridad de la información personal de nuestros usuarios. Esta política de privacidad describe cómo recopilamos, utilizamos, compartimos y protegemos su información cuando utiliza nuestro sitio web.</p>

            <h2>Información que Recopilamos</h2>
            <p>Recopilamos información personal que usted nos proporciona, como su nombre, dirección de correo electrónico, número de teléfono y otra información de contacto. También recopilamos información sobre su historial de navegación y actividad en nuestro sitio web.</p>

            <h2>Cómo Utilizamos su Información</h2>
            <p>Utilizamos su información personal para proporcionarle nuestros servicios y mejorar su experiencia de usuario. También utilizamos su información para enviarle actualizaciones y comunicaciones relacionadas con nuestros servicios.</p>

            <h2>Compartir su Información</h2>
            <p>No compartimos su información personal con terceros sin su consentimiento, excepto en los siguientes casos:</p>
            <li>Con su consentimiento explícito.</li>
            <li>Con proveedores de servicios que nos ayudan a operar nuestro sitio web y proporcionar nuestros servicios.</li>
            <li>Cuando sea requerido por ley o para proteger nuestros derechos y seguridad.</li>
             <p className='relleno'></p>   

            <h2>Seguridad de la Información</h2>
            <p>Tomamos medidas para proteger su información personal y garantizar su seguridad. Sin embargo, no podemos garantizar la seguridad de la información que transmite a través de Internet.</p>

        </div><Footer /></>
    );
};

export default PrivacyPolicy;
