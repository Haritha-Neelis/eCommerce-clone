import { Link, useNavigate } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function AccountPage() {
  const navigate = useNavigate();
  const { user, lastOrder, setUser, favorites } = useShop();

  const handleLogout = () => {
    setUser({ isLoggedIn: false, name: 'Jane Doe', email: 'jane@example.com' });
    navigate('/');
  };

  return (
    <div className="form-card">
      <div className="page-header">
        <h1>My account</h1>
        <p className="muted">Welcome back, {user.name.split(' ')[0]}. Manage your shopping profile here.</p>
      </div>

      <div className="card-grid">
        <div className="card">
          <h3>Profile</h3>
          <p className="muted">{user.name}</p>
          <p className="muted">{user.email}</p>
        </div>
        <div className="card">
          <h3>Addresses</h3>
          <p className="muted">2 saved addresses</p>
        </div>
        <div className="card">
          <h3>Saved items</h3>
          <p className="muted">{favorites.length} items in your wishlist</p>
          <Link to="/products" className="topbar-link">Keep browsing</Link>
        </div>
      </div>

      <section className="order-panel">
        <div className="section-heading"><h2>Recent order</h2><button className="text-button" type="button" onClick={handleLogout}>Sign out</button></div>
        {lastOrder ? <div className="order-row"><div><strong>Order {lastOrder.id}</strong><p className="muted">Placed {lastOrder.date} · {lastOrder.itemCount} items</p></div><div className="order-status"><span>Confirmed</span><strong>₹{lastOrder.total.toLocaleString('en-IN')}</strong></div></div> : <p className="muted">Your recent orders will appear here after checkout.</p>}
      </section>
    </div>
  );
}
