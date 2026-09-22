import { Link } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function HomePage() {
  const { products } = useShop();

  const featureCards = [
    { title: 'Fast checkout', text: 'Streamlined purchase flow for modern shoppers.' },
    { title: 'Inventory insight', text: 'Track stock, pricing, and product health in one place.' },
    { title: 'Secure orders', text: 'Account-first flows with order visibility and trust.' }
  ];

  const highlightedProducts = products.slice(0, 4);

  return (
    <>
      <section className="hero">
        <div className="hero-copy">
          <p className="eyebrow">THE EVERYDAY EDIT</p>
          <h1>Make room for better finds.</h1>
          <p>
            Curated essentials, new-season style, and prices that make the scroll worthwhile.
          </p>
          <div className="hero-actions">
            <Link to="/products" className="btn btn-primary">Explore deals <span>→</span></Link>
            <span className="hero-note">Free delivery on orders over ₹499</span>
          </div>
        </div>

        <div className="hero-panel">
          <span className="hero-discount">UP TO 60% OFF</span>
          <h2>Fresh picks.<br /><em>Bright</em> prices.</h2>
          <p>Deals worth opening your wishlist for.</p>
          <Link to="/products" className="hero-panel-link">Shop the drop ↗</Link>
        </div>
      </section>

      <section className="perks-grid">
        {featureCards.map((card) => (
          <article key={card.title} className="perk">
            <span className="perk-icon">{card.title === 'Fast checkout' ? '↯' : card.title === 'Inventory insight' ? '◈' : '✓'}</span>
            <div><h3>{card.title}</h3>
            <p className="muted">{card.text}</p>
            </div>
          </article>
        ))}
      </section>

      <section className="section-block">
        <div className="section-heading">
          <h2>Trending this week</h2>
          <Link to="/products" className="topbar-link">View all</Link>
        </div>

        <div className="product-grid compact-grid">
          {highlightedProducts.map((product) => (
            <article key={product.id} className="product-card">
              <img src={product.image} alt={product.name} className="product-image" />
              <div className="product-body">
                <div className="product-meta-row">
                  <span className="badge">{product.badge}</span>
                  <span className="rating">★ {product.rating}</span>
                </div>
                <h3>{product.name}</h3>
                <div className="price-row">
                  <span className="price">₹{product.price.toLocaleString('en-IN')}</span>
                  <span className="old-price">₹{product.originalPrice.toLocaleString('en-IN')}</span>
                </div>
                <Link to={`/products/${product.id}`} className="btn btn-secondary full-width">View details</Link>
              </div>
            </article>
          ))}
        </div>
      </section>
    </>
  );
}
