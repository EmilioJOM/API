import React, { useState } from 'react';
import { createTrial } from '../../apis/trialApi';
import { provincias } from '../../assets/enums/Provincias';
import { estados } from '../../assets/enums/Estados';
import { enfermedades } from '../../assets/enums/Categorias';
import { subCat } from '../../assets/enums/Subcat';
import '../../styles/CreateVenta.css';

export const CreateTrialView = () => {
    const [formData, setFormData] = useState({
        producto: '',
        categoria: '',
        subcat: '',
        status: '',
        descripcion: '',
        precio: '',
        provincia: '',
    });

       const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevFormData => ({
            ...prevFormData,
            [name]: value
        }));
    }

    const submitForm = async (e) => {
        e.preventDefault();
        try {
            await createTrial(formData);
            alert('Venta creada correctamente');
        } catch (error) {
            alert('Error al crear la venta');
        }
    }

    return (
        <div className="create-trial-container">
            <h2 className="create-trial-title">Crear nueva venta</h2>
            <form onSubmit={submitForm} className="create-trial-form">
                <label>Nombre del producto</label>
                <input
                    type="text"
                    name="name"
                    placeholder="Nombre del ensayo"
                    value={formData.name}
                    onChange={handleInputChange}
                    className="create-trial-input"
                />
                <label>Categoria</label>
                <select
                    name="categoria"
                    value={formData.enfermedad}
                    onChange={handleInputChange}
                    className="create-trial-select"
                >
                    <option value="">Selecciona una categoria</option>
                    {enfermedades.map((enfermedad, index) => (
                        <option key={index} value={enfermedad}>{enfermedad}</option>
                    ))}
                </select>

                <label>Sub-Categoria</label>
                <select
                    name="categoria"
                    value={formData.subCat}
                    onChange={handleInputChange}
                    className="create-trial-select"
                >
                    <option value="">Selecciona una categoria</option>
                    {subCat.map((subCat, index) => (
                        <option key={index} value={subCat}>{subCat}</option>
                    ))}
                </select>

                <label>Estado</label>
                <select
                    name="status"
                    value={formData.status}
                    onChange={handleInputChange}
                    className="create-trial-select"
                >
                    <option value="">Selecciona un estado</option>
                    {estados.map((estado, index) => (
                        <option key={index} value={estado}>{estado}</option>
                    ))}
                </select>
                
                <label>Provincia</label>
                <select
                    name="provincia"
                    value={formData.provincia}
                    onChange={handleInputChange}
                    className="create-trial-select"
                >
                    <option value="">Selecciona una provincia</option>
                    {provincias.map((provincia, index) => (
                        <option key={index} value={provincia}>{provincia}</option>
                    ))}
                </select>
                
                <div className="age-range-container">
                    <label>Precio:</label>
                    <input
                        type="number"
                        name="precio"
                        placeholder="Valor"
                        className="create-trial-input age-range-input"
                    />
                  
                </div>
                <div className="description-container">
                    <label>Descripción</label>
                    <textarea
                        name="descripcion"
                        value={formData.descripcion}
                        onChange={handleInputChange}
                        className="create-trial-textarea"
                        style={{ height: '16vh'}}
                    />
                </div>
                <button type="submit" className="create-trial-button">
                    Crear Venta
                </button>
            </form>
        </div>
    );
}
