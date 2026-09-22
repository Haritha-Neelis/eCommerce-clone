import { Link } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function MainLayout({ children }) {
  const { cartCount, user } = useShop();
  const categories = ['Electronics', 'Fashion', 'Home & Kitchen', 'Beauty', 'Sports', 'Books'];

  return (
    <>
      <div className="utility-bar">
        <div className="container utility-inner">
          <span>Big savings, delivered to your door</span>
          <span>Track order <span className="utility-dot">•</span> Become a seller</span>
        </div>
      </div>
      <header className="site-header">
        <div className="container navbar navbar-main">
          <Link to="/" className="brand">
            <span className="brand-mark">C</span>
            <span>Commerce<span className="brand-accent">Flow</span></span>
          </Link>
          <label className="search-box">
            <span aria-hidden="true">⌕</span>
            <input type="search" placeholder="Search for products, brands and more" aria-label="Search products" />
          </label>
          <div className="nav-actions">
            <Link to="/account" className="header-link">♡ <span>Wishlist</span></Link>
            <Link to="/cart" className="header-link cart-link">🛒 <span>Cart</span><b>{cartCount}</b></Link>
            <Link to="/login" className="header-login">
              {user.isLoggedIn ? user.name.split(' ')[0] : 'Login'}
            </Link>
          </div>
        </div>
        <nav className="category-nav" aria-label="Product categories">
          <div className="container category-inner">
            <Link to="/products" className="category-home">All categories</Link>
            {categories.map((category) => <Link to="/products" key={category}>{category}</Link>)}
          </div>
        </nav>
      </header>

      <main className="page-shell">
        <div className="container">{children}</div>
      </main>
    </>
  );
}
