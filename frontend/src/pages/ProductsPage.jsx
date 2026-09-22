import { Link } from 'react-router-dom';
import { useState } from 'react';
import { useShop } from '../context/ShopContext';

export default function ProductsPage() {
  const { products, addToCart, toggleFavorite, favorites } = useShop();
  const [query, setQuery] = useState('');
  const [sort, setSort] = useState('featured');

  const visibleProducts = products
    .filter((product) => {
      const searchableText = `${product.name} ${product.category} ${product.brand}`.toLowerCase();
      return searchableText.includes(query.toLowerCase());
    })
    .sort((left, right) => {
      if (sort === 'price-low') return left.price - right.price;
      if (sort === 'price-high') return right.price - left.price;
      if (sort === 'rating') return right.rating - left.rating;
      return left.id - right.id;
    });

  return (
    <>
      <div className="page-header">
        <h1>Shop collection</h1>
        <p className="muted">Curated essentials for work, lifestyle, and wellness.</p>
      </div>

      <div className="toolbar">
        <label className="shop-search">
          <span>⌕</span>
          <input className="input" value={query} onChange={(event) => setQuery(event.target.value)} placeholder="Search products, brands or categories" aria-label="Search products" />
        </label>
        <select className="input select-inline" value={sort} onChange={(event) => setSort(event.target.value)} aria-label="Sort products">
          <option value="featured">Sort by featured</option>
          <option value="price-low">Price: Low to High</option>
          <option value="price-high">Price: High to Low</option>
          <option value="rating">Customer rating</option>
        </select>
      </div>

      <p className="results-count">{visibleProducts.length} products</p>

      {visibleProducts.length > 0 ? <div className="product-grid">
        {visibleProducts.map((product) => {
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
      </div> : <div className="empty-state form-card"><h2>No products found</h2><p className="muted">Try a different search term or browse the full collection.</p><button className="btn btn-secondary" type="button" onClick={() => setQuery('')}>Clear search</button></div>}
    </>
  );
}
