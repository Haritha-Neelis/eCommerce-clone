import { Link, useNavigate } from 'react-router-dom';
import { useShop } from '../context/ShopContext';

export default function RegisterPage() {
  const navigate = useNavigate();
  const { setUser } = useShop();

  const handleSubmit = (event) => {
    event.preventDefault();
    setUser({ isLoggedIn: true, name: 'Jane Doe', email: 'jane@example.com' });
    navigate('/account');
  };

  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Create account</h1>
        <p className="muted">Start shopping and manage your orders securely.</p>
      </div>

      <form className="form-grid" onSubmit={handleSubmit}>
        <div>
          <label className="label" htmlFor="name">Full name</label>
          <input className="input" id="name" type="text" placeholder="Jane Doe" defaultValue="Jane Doe" />
        </div>

        <div>
          <label className="label" htmlFor="register-email">Email</label>
          <input className="input" id="register-email" type="email" placeholder="name@example.com" defaultValue="jane@example.com" />
        </div>

        <div>
          <label className="label" htmlFor="register-password">Password</label>
          <input className="input" id="register-password" type="password" placeholder="Create password" defaultValue="password123" />
        </div>

        <button type="submit" className="btn btn-primary">Create account</button>
        <p className="muted">
          Already registered? <Link className="topbar-link" to="/login">Sign in</Link>
        </p>
      </form>
    </div>
  );
}
