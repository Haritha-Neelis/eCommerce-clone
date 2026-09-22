import { Link } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function ProductsPage() {
  const { products, addToCart, toggleFavorite, favorites } = useShop();

  return (
    <>
      <div className="page-header">
        <h1>Shop collection</h1>
        <p className="muted">Curated essentials for work, lifestyle, and wellness.</p>
      </div>

      <div className="toolbar">
        <input className="input" placeholder="Search for products" />
        <select className="input select-inline">
          <option>Sort by featured</option>
          <option>Price: Low to High</option>
          <option>Price: High to Low</option>
          <option>Customer rating</option>
        </select>
      </div>

      <div className="product-grid">
        {products.map((product) => {
          const isFavorite = favorites.includes(product.id);

          return (
            <article key={product.id} className="product-card">
              <div className="product-image-wrap">
                <img src={product.image} alt={product.name} className="product-image" />
                <button className="wishlist-button" onClick={() => toggleFavorite(product.id)} type="button">
                  {isFavorite ? '♥' : '♡'}
                </button>
              </div>
              <div className="product-body">
                <div className="product-meta-row">
                  <span className="badge">{product.badge}</span>
                  <span className="rating">★ {product.rating}</span>
                </div>
                <h3>{product.name}</h3>
                <p className="muted small-text">{product.category}</p>
                <div className="price-row">
                  <span className="price">₹{product.price.toLocaleString('en-IN')}</span>
                  <span className="old-price">₹{product.originalPrice.toLocaleString('en-IN')}</span>
                </div>
                <div className="product-actions">
                  <Link to={`/products/${product.id}`} className="btn btn-secondary">Details</Link>
                  <button className="btn btn-primary" type="button" onClick={() => addToCart(product)}>
                    Add to cart
                  </button>
                </div>
              </div>
            </article>
          );
        })}
      </div>
    </>
  );
}
