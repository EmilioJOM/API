import React from 'react';
import '../styles/AboutUs.css';
import { Footer } from '../components/Footer';
import { Header } from '../components/Header';

export const AboutUs = () => {
    return (
        <>
        <Header />
            <div className='about-us'>
                <h1>Sobre Nosotros</h1>
                <section className='mission-vision'>
                    <div className='section'>
                        <h2>Nuestra Misión</h2>
                        <p>
                        En ATono, la música es nuestra pasión y nuestra misión es ayudarte a expresarla. Desde guitarras eléctricas que hacen vibrar los escenarios hasta pianos que transforman cada nota en emoción, contamos con una amplia selección de instrumentos para músicos de todos los niveles.
                        </p>
                    </div>
                    <div className='section'>
                        <h2>Nuestra Visión</h2>
                        <p>
                        Nuestra tienda no es solo un catálogo digital; somos músicos apasionados, listos para asesorarte y acompañarte en cada paso de tu recorrido musical. Ya sea que estés buscando tu primer instrumento o perfeccionando tu colección, nuestro equipo está aquí para brindarte el mejor servicio posible.

                        Queremos ser el destino preferido para músicos de todo el mundo, contribuyendo a hacer de la música una parte esencial en la vida de las personas.
                        </p>
                    </div>
                </section>
                
            </div>
            <Footer />
        </>
    );
};
