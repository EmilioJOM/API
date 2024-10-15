import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Home } from './views/Home.jsx';
import { AboutUs } from './views/AboutUs.jsx';
import PrivacyPolicy from './views/PrivacyPolicy.jsx';
import Terms from './views/Terms.jsx';
import { RegisterOptions } from './views/RegisterOptions.jsx';
import { Login } from './views/Login.jsx';
import './styles/index.css';
import { RegisterComprador} from './views/RegisterComprador.jsx';
import { RegisterVendedor } from './views/RegisterVendedor.jsx'
import { LoginVendedor } from './views/LoginVendedor.jsx';
import { LoginOptions } from './views/LoginOptions.jsx';
import Shop from './views/Shop.jsx';


function App() {

     return (
        <Router>
            <main>
                <Routes>
                    <Route path="/" element={<Home />}/>
                        <Route path="about-us" element={<AboutUs />} />
                        <Route path="privacy-policy" element={<PrivacyPolicy />} />
                        <Route path="terms" element={<Terms />} />
                        <Route path="*" element={<Home />} />
                        <Route path="register" element={<RegisterOptions />} />
                        <Route path="login" element={<Login />} />
                        <Route path="register/comprador" element={<RegisterComprador />} />
                        <Route path="login/vendedor" element={<LoginVendedor />} />
                        <Route path="register/vendedor" element={<RegisterVendedor />} />
                        <Route path="login/options" element={<LoginOptions />} />
                        <Route path="shop" element={<Shop />} />
                </Routes>
            </main>
        </Router>
    );
}

export default App;