import { Link } from 'react-router-dom';

export default function MainLayout({ children }) {
  return (
    <>
      <header className="site-header">
        <div className="container navbar">
          <Link to="/" className="brand">CommerceFlow</Link>
          <nav className="nav-links" aria-label="Main navigation">
            <Link to="/">Home</Link>
            <Link to="/products">Shop</Link>
            <Link to="/account">Account</Link>
            <Link to="/admin">Admin</Link>
          </nav>
          <div className="nav-actions">
            <Link to="/cart" className="btn btn-secondary">Cart (0)</Link>
            <Link to="/login" className="btn btn-primary">Login</Link>
          </div>
        </div>
      </header>

      <main className="page-shell">
        <div className="container">{children}</div>
      </main>
    </>
  );
}
