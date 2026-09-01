export default function RegisterPage() {
  return (
    <div className="form-card">
      <div className="page-header">
        <h1>Create account</h1>
        <p className="muted">Start shopping and manage your orders securely.</p>
      </div>

      <form className="form-grid">
        <div>
          <label className="label" htmlFor="name">Full name</label>
          <input className="input" id="name" type="text" placeholder="Jane Doe" />
        </div>

        <div>
          <label className="label" htmlFor="register-email">Email</label>
          <input className="input" id="register-email" type="email" placeholder="name@example.com" />
        </div>

        <div>
          <label className="label" htmlFor="register-password">Password</label>
          <input className="input" id="register-password" type="password" placeholder="Create password" />
        </div>

        <button type="submit" className="btn btn-primary">Create account</button>
        <p className="muted">
          Already registered? <a className="topbar-link" href="/login">Sign in</a>
        </p>
      </form>
    </div>
  );
}
