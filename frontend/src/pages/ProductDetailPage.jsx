import { useParams } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function ProductDetailPage() {
  const { id } = useParams();
  const { products, addToCart, toggleFavorite, favorites } = useShop();
  const product = products.find((item) => String(item.id) === String(id));

  if (!product) {
    return <div className="form-card"><h1>Product not found</h1></div>;
  }

  const isFavorite = favorites.includes(product.id);

  return (
    <div className="product-detail">
      <div className="product-detail-image-wrap">
        <img src={product.image} alt={product.name} className="product-detail-image" />
      </div>

      <div className="product-detail-body">
        <span className="badge">{product.badge}</span>
        <h1>{product.name}</h1>
        <p className="muted">{product.category} • sold by {product.seller}</p>
        <div className="rating-row">
          <span className="rating">★ {product.rating}</span>
          <span className="muted">{product.reviewCount} reviews</span>
        </div>
        <div className="price-row detail-price-row">
          <span className="price">₹{product.price.toLocaleString('en-IN')}</span>
          <span className="old-price">₹{product.originalPrice.toLocaleString('en-IN')}</span>
        </div>
        <p>{product.description}</p>

        <ul className="spec-list">
          {product.specs.map((spec) => (
            <li key={spec}>{spec}</li>
          ))}
        </ul>

        <div className="hero-actions" style={{ marginTop: '1rem' }}>
          <button className="btn btn-primary" type="button" onClick={() => addToCart(product)}>
            Add to cart
          </button>
          <button className="btn btn-secondary" type="button" onClick={() => toggleFavorite(product.id)}>
            {isFavorite ? 'Saved' : 'Save for later'}
          </button>
        </div>
      </div>
    </div>
  );
}
