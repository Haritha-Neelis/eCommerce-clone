import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { useShop } from '../context/ShopContext';

export default function LoginPage() {
  const navigate = useNavigate();
  const { setUser } = useShop();
  const [form, setForm] = useState({ email: '', password: '' });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setUser({
      isLoggedIn: true,
      name: 'Jane Doe',
      email: form.email
    });
    navigate('/account');
  };

  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Welcome back</h1>
        <p className="muted">Sign in to continue to your account.</p>
      </div>

      <form className="form-grid" onSubmit={handleSubmit}>
        <div>
          <label className="label" htmlFor="email">Email</label>
          <input className="input" id="email" name="email" type="email" value={form.email} onChange={handleChange} placeholder="name@example.com" required />
        </div>

        <div>
          <label className="label" htmlFor="password">Password</label>
          <input className="input" id="password" name="password" type="password" value={form.password} onChange={handleChange} placeholder="••••••••" required />
        </div>

        <button type="submit" className="btn btn-primary">Sign in</button>
        <p className="muted">
          Need an account? <Link className="topbar-link" to="/register">Create one</Link>
        </p>
      </form>
    </div>
  );
}
