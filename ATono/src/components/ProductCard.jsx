import "../styles/ProductCard.css";

function ProductCard({ producto, onAgregar }) {
    return (
        <div className="producto-card">
        <img src={producto.imagen} alt={producto.nombre} className="producto-imagen" />
        <h3>{producto.nombre}</h3>
        <p>Precio: ${producto.precio}</p>
        <button onClick={onAgregar} className="btn-agregar">Agregar al carrito</button>
      </div>
    );
  }
  
  export default ProductCard;